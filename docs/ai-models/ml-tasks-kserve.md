# Задачи для ML: тестирование и настройка моделей в KServe

Этот документ описывает задачи для команды ML по интеграции, тестированию и эксплуатации моделей через KServe в рамках AutoLabSuite.

## Контракты API и схемы

- Определить JSON Schema для payload/response по типам задач (код/текст/классификация), согласовать с Grading Core.
- Добавить валидаторы в тесты (jsonschema/pydantic) — «жёсткий» контракт.
- Продумать версионирование контрактов и совместимость (v1, v1.1…).

## Контейнеризация и рантаймы

- Выбрать runtime по модели:
  - LLM: vLLM в качестве сервера; FastAPI adapter при необходимости преобразования формата.
  - Эмбеддинги/классификаторы: ONNX/TorchScript на Triton или лёгкий FastAPI сервис.
  - Custom sklearn/xgboost: KServe sklearn/xgb runtime либо свой контейнер.
- Стандартизовать HEALTH endpoints (/-/healthz) и /metrics (Prometheus).

## Тесты (ссылка на `ml/tests`)

- Unit: мок моделей/HTTP (pytest + requests-mock).
- Contract: валидация схемы входа/выхода на примерах.
- Integration: метки `@pytest.mark.integration`, переменные `MODEL_ENDPOINT`, `AUTH_TOKEN`.
- Property-based: Hypothesis для edge-cases (большой текст, пустые поля, неверный JSON).
- Smoke на деплой: тест на живость InferenceService после выпуска.

## Нагрузочное и устойчивость

- Locust/k6 профиль на пиковую нагрузку; метрики p50/p95, error rate.
- Тесты таймаутов и ретраев.
- Тесты на холодный старт (minScale=0/1).

## Данные и безопасность

- Маскирование PII перед отправкой в LLM.
- Ограничение размеров артефактов; стратегия ссылок (presigned URL) для больших файлов.
- Контроль версий моделей и артефактов (model card, change log).

## Definition of Done для модели

- InferenceService YAML (production и staging) в репозитории.
- Набор sample payloads + ожидаемые ответы (golden set).
- Метрики и алерты в Grafana (dashboard id/ссылка).
- Документация: как вызывать endpoint, версия модели, ограничения.

## Пример базового InferenceService (custom FastAPI)


```yaml
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
  name: grader-llm
  namespace: ml
spec:
  predictor:
    containers:
      - image: ghcr.io/org/grader-llm:0.1.0
        name: app
        ports:
          - containerPort: 8080
        env:
          - name: MODEL_NAME
            value: grader-llm
        resources:
          requests:
            cpu: "1"
            memory: "2Gi"
          limits:
            cpu: "2"
            memory: "4Gi"
```

## Пример запроса (PowerShell)


```powershell
$payload = @{ submission = "print('Hello')"; language = "python"; metadata = @{ max_score = 10 } } | ConvertTo-Json -Depth 5
Invoke-RestMethod -Method Post -Uri "https://api.example.edu/v1/models/grader-llm:predict" -Headers @{ Authorization = "Bearer $env:AUTH_TOKEN" } -ContentType "application/json" -Body $payload
```

## Next Steps

- Подготовить PoC для 2-х моделей (LLM объяснения + эмбеддинги-классификатор) в KServe.
- Добавить CI job, запускающий smoke + contract tests после деплоя InferenceService.
- Совместно с DevOps согласовать SLO (p95 latency, error rate) и алерты.
