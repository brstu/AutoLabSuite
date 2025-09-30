# Документация LabAutomation

Это индекс основных разделов. Файлы написаны на русском языке.

## Оглавление

### Обзор

- `overview/README.md` — цели и доменная модель
- `roadmap/milestones.md` — фазы и метрики

### Архитектура

- `architecture/solution-architecture.md` — компоненты и потоки
- `architecture/glossary.md` — глоссарий терминов
- `decision-records/README.md` — ADR индекс
- `architecture/diagrams/c4-context.md` — C4 контекст
- `architecture/diagrams/c4-container.md` — C4 контейнеры
- `architecture/diagrams/sequence-grading.md` — последовательность оценивания

### Оценивание

- `grading/rubric-model.md` — модель рубрик
- `grading/rubric-versioning.md` — версионирование рубрик
- `templates/rubric-template.md` — шаблон рубрики
- `examples/sample-lab-report.md` — пример отчёта

### AI модели

- `ai-models/model-strategy.md` — стратегия использования
- `ai-models/metrics.md` — метрики качества AI
- `templates/model-card-template.md` — карточка модели
- `templates/dataset-card-template.md` — карточка датасета

### API

- `api/README.md` — обзор
- `api/api-design-principles.md` — принципы проектирования
- `api/versioning.md` — версионирование
- `api/error-model.md` — модель ошибок
- `api/authentication.md` — аутентификация
- `api/openapi.yaml` — спецификация (минимальный черновик)

### Operations

- `operations/README.md` — эксплуатация
- `operations/observability.md` — наблюдаемость
- `operations/slo.md` — SLO/SLI
- `operations/backup-restore.md` — резервное копирование
- `operations/runbooks/runbook-grading-stuck.md` — пример runbook

### Compliance

- `compliance/README.md` — обзор
- `compliance/data-classification.md` — классификация данных
- `compliance/privacy-and-pii.md` — приватность и PII
- `compliance/security-controls.md` — контроли безопасности
- `compliance/threat-model.md` — модель угроз

### Шаблоны и процессы

- `templates/issue-template.md`
- `templates/pr-template.md`

### Дополнительно

### UI / UX

- `ui-ux/README.md` — обзор и IA
- `ui-ux/user-roles.md` — роли и персоны
- `ui-ux/wireframes.md` — wireframes placeholder

## Навигация

Рекомендуемый путь чтения:

1. Обзор
2. Архитектура
3. Rubric модель
4. AI стратегия
5. API принципы
6. Наблюдаемость / SLO
7. Compliance основы

## Обновление оглавления

На данном этапе поддерживается вручную. Сгенерированная версия: `README.generated.md` (использовать скрипт `scripts/generate_docs_toc.py`).
