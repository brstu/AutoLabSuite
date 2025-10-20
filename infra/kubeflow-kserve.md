# Kubeflow/KServe — развертывание и эксплуатация

Этот документ описывает базовые задачи для DevOps по развертыванию Kubeflow и KServe, минимальные требования к инфраструктуре, способы экспонирования моделей и операционные практики.

## Минимальные требования (минимум для PoC)

- Kubernetes: 1 control-plane + 2 worker узла (можно управляемый кластер).
- Ресурсы (PoC без GPU):
  - Control-plane: 2 vCPU, 8 GiB RAM
  - Worker x2: 4 vCPU, 16 GiB RAM каждый
  - Диск: 100 GiB на узел (SSD)
- Для GPU сценариев:
  - 1-2 GPU узла (например, NVIDIA T4/A10): 8 vCPU, 32-64 GiB RAM, 1 x T4; драйверы NVIDIA, nvidia-container-runtime, NVIDIA Device Plugin.
- Сетевой стек: Istio (предпочтительно) или эквивалент для KServe/Knative.
- Object Storage: S3-совместимое (MinIO/S3) для артефактов моделей (опционально).

## Компоненты для установки

- Kubeflow (минимальный профиль) ИЛИ standalone KServe + Knative Serving + Istio.
- KServe CRDs и Runtimes (sklearn, triton, pytorch, tensorflow по необходимости).
- Cert-Manager (TLS), Ingress/Gateway (Istio Gateway / NGINX Ingress / Kong / Ambassador).
- Observability: Prometheus + Grafana; Loki/ELK для логов (опционально);
- Secrets: External Secrets Operator или использование K8s Secret + Vault/SOPS.

## План задач для DevOps

1) Кластер и базовая сетка
- Поднять Kubernetes кластер (managed/terraform).
- Установить Istio + Ingress Gateway (или альтернативу), включить mTLS в mesh (опционально).
- Установить Knative Serving (если используется autoscaling от Knative) и настроить DomainMapping.

1) Установка KServe
- Установить KServe CRDs и Controller (version-pinned).
- Включить нужные runtimes: sklearn, triton, pytorch (Helm/manifest).
- Проверить работу примером InferenceService (hello world / sklearn iris).

1) TLS и аутентификация
- Развернуть cert-manager и выпустить сертификаты (Let’s Encrypt / внутренний CA).
- Настроить Ingress/Gateway с HTTPS.
- Включить auth: JWT/OIDC на уровне Gateway (или mTLS между сервисами).

1) Хранилище моделей и артефактов
- Развернуть MinIO (или подключить S3) для хранения моделей/логов.
- Настроить доступ сервисным аккаунтам через IRSA/K8s SA + IAM.

1) Наблюдаемость
- Prometheus/Grafana (scrape KServe/Knative/Istio метрики).
- Логи: Loki/ELK; Трейсинг: OTEL Collector.
- Алерты по latency/error rate/scale failures.

1) CI/CD и GitOps
- Артефакты моделей в Registry/Storage; манифесты InferenceService в Git.
- Развертывание через Helm pipeline.
- Canary/Blue-Green через KServe TrafficSplit.

1) Политики и безопасность
- NetworkPolicies для namespace моделей.
- ResourceQuotas/LimitRanges.
- ImagePolicy/Admission (Notary/Cosign) и сканирование (Trivy).

## Быстрый пример InferenceService (sklearn)

```yaml
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
  name: sklearn-iris
  namespace: default
spec:
  predictor:
    sklearn:
      storageUri: "gs://kfserving-examples/models/sklearn/iris"
```

Проверка:
- kubectl apply -f inferenceservice.yaml
- kubectl get inferenceservice sklearn-iris -n default

## Экспонирование endpoint

Варианты:
- Istio Gateway + VirtualService (рекомендуется)
- Knative domain mapping
- NGINX Ingress / Kong Ingress

Путь вызова зависит от runtime:
- TF Serving: POST /v1/models/<name>:predict
- Triton: POST /v2/models/<name>/infer
- Sklearn (kserve predictor): POST /v1/models/<name>:predict
- Custom: определяете самостоятельно

## Операционные заметки

- Автомасштабирование: Knative HPA/KPA; tune minScale для холодного старта.
- Канареечные релизы: spec.predictor.canaryTrafficPercent.
- Лимиты ресурсов: cpu/mem, при необходимости GPU: nvidia.com/gpu.
- Логи: включить access logs на Gateway, структурированные логи в контейнерах.
- Backoff/Retry: выставляются на клиенте и частично на Gateway.

### Пример Helm pipeline (GitHub Actions)

Ниже — пример минимального пайплайна для деплоя манифестов KServe/Helm-чарта в кластер. Требуемые секреты:
- KUBE_CONFIG_DATA: base64 от kubeconfig (или используйте OIDC провайдер к облаку)
- REGISTRY_USERNAME / REGISTRY_PASSWORD (если вы пушите контейнеры)

Структура (вариант):
- infra/charts/models/ — Helm chart для ваших InferenceService и зависимостей
- values-staging.yaml, values-prod.yaml — окружения

Пример workflow `.github/workflows/deploy-helm.yml`:

```yaml
name: Deploy (Helm)

on:
  push:
    branches: [ main ]
    paths:
      - 'infra/charts/**'
      - 'docs/ai-models/**'
      - 'services/**'

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v4

      - name: Setup kubectl
        uses: azure/setup-kubectl@v4

      - name: Setup Helm
        uses: azure/setup-helm@v4

      - name: Configure kubeconfig
        shell: bash
        env:
          KUBE_CONFIG_DATA: ${{ secrets.KUBE_CONFIG_DATA }}
        run: |
          mkdir -p $HOME/.kube
          echo "$KUBE_CONFIG_DATA" | base64 -d > $HOME/.kube/config

      - name: Helm upgrade (staging)
        run: |
          helm upgrade --install models infra/charts/models \
            -n ml --create-namespace \
            -f infra/charts/models/values-staging.yaml \
            --wait --timeout 10m
```

Заметки:
- Для production используйте другой файл values и отдельную Джобу/среду с ручным подтверждением.
- Если разворачиваете KServe CRDs/оператор через Helm, разделите релизы на «платформенные» и «прикладные» (модели).
- Для canary используйте поля traffic в InferenceService или отдельные релизы с разных версий модели.

