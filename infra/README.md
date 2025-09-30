# AutoLabSuite Infrastructure

Инфраструктурные артефакты: IaC, CI/CD, безопасность, наблюдаемость.

## Содержимое (план)

- `iac/` Terraform / Pulumi (кластеры, БД, Object Storage)
- `k8s/` Манифесты / Helm charts
- `pipelines/` CI/CD (GitHub Actions / альтернативы)
- `observability/` Grafana / Prometheus / Loki / Tempo dsn
- `security/` Политики, Trivy/Grype отчёты, SAST/DAST конфиги

## DevOps Практики

- GitOps (ArgoCD / Flux) — TBD
- Secrets management (Vault / SOPS) — TBD
- Policy as Code (OPA / Conftest) — TBD

## Ответственность команды

Текущие владельцы — команда DevOps (см. [docs/teams/README.md](/docs/teams/README.md)).
