# Документация LabAutomation

Это индекс основных разделов. Файлы написаны на русском языке.

## Оглавление

### Обзор

- `overview/README.md` — цели и доменная модель
- `roadmap/milestones.md` — фазы и метрики

### Архитектура

- `architecture/solution-architecture.md` — компоненты и потоки
- `architecture/hexagonal.md` — гексагональная архитектура (порты/адаптеры)
- `architecture/glossary.md` — глоссарий терминов
- `architecture/erd.md` — ERD и модель данных
- `architecture/feature-diagram.md` — диаграмма фич
- `architecture/permissions-matrix.md` — матрица прав (RBAC)
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
- `ai-models/llm-provider/README.md` — AI Gateway (LLM provider) обзор
- `ai-models/llm-provider/contracts.md` — контракты порта и форматы
- `ai-models/llm-provider/providers.md` — провайдеры и конфигурации
- `ai-models/llm-provider/prompts.md` — промпты и схемы вывода
- `ai-models/llm-provider/retry-and-errors.md` — ретраи и ошибки
- `ai-models/llm-provider/test-plan.md` — план тестирования
- `ai-models/llm-provider/openapi.yaml` — OpenAPI AI Gateway
- `templates/model-card-template.md` — карточка модели
- `templates/dataset-card-template.md` — карточка датасета

### API

- `api/README.md` — обзор
- `api/api-design-principles.md` — принципы проектирования
- `api/versioning.md` — версионирование
- `api/error-model.md` — модель ошибок
- `api/authentication.md` — аутентификация
- `api/openapi.yaml` — спецификация (минимальный черновик)
- `api/models.md` — основные модели данных
- `api/resources.md` — обзор ресурсов и эндпоинтов

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
- `ui-ux/routes-and-scenarios.md` — страницы/маршруты и сценарии

### Литература

- Краткий обзор литературы: [literature_review_short_ru.md](/docs/literature_review_short_ru.md)
- Расширенный обзор литературы: [literature_review_extended_ru.md](/docs/literature_review_extended_ru.md)
- Библиография (экспорт): [references.bib](/docs/references.bib), [references.ris](/docs/references.ris)

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
