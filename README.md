# AutoLabSuite

![CI](https://github.com/brstu/AutoLabSuite/actions/workflows/ci.yml/badge.svg)

Репозиторий AutoLabSuite — система автоматизации:


## Цели

1. Сокращение времени преподавателя на рутинную проверку.
2. Повышение прозрачности критериев и истории изменений оценок.
3. Поддержка формативной обратной связи и персональных рекомендаций.
4. Масштабируемость на разные дисциплины и типы заданий.

CloudForge

| Домены | Кратко |
|--------|--------|
| Ingestion | Приём/сбор работ, метаданных, артефактов |
| Grading Engine | Правила, рубрики, тесты, статический/динамический анализ |
| AI Assist | LLM подсказки, объяснения, переформулировки, классификация ошибок |
| Feedback | Формирование отчётов студенту и преподавателю |
| Audit & Traceability | Прозрачность, цепочка артефактов, объяснимость AI |
| Progress Tracking | Учёт попыток, дедлайны, статусы, метрики |
| Integration | LMS / Git / CI / Контейнеры / Песочницы выполнения |

## Быстрый старт (документация)

### Содержание

1. [Цели](#цели)
2. [Основные домены](#основные-домены)
3. [Быстрый старт (документация)](#быстрый-старт-документация)
4. [Как читать](#как-читать)
5. [Содействие](#содействие)
6. [Лицензия](#лицензия)
7. [Состояние](#состояние)
8. [Улучшения в планах](#улучшения-в-планах)

Документация в каталоге `docs/` структурирована по best practices (см. также сводное оглавление: [docs/README.md](/docs/README.md)):

- Обзор: [docs/overview](/docs/overview/)
- Архитектура: [docs/architecture](/docs/architecture/)
- API: [docs/api](/docs/api/)
- Процессы и операции: [docs/processes](/docs/processes/), [docs/operations](/docs/operations/)
- Модели ИИ: [docs/ai-models](/docs/ai-models/)
- Оценивание: [docs/grading](/docs/grading/)
- Безопасность и соответствие: [docs/compliance](/docs/compliance/)
- ADR (решения): [docs/decision-records](/docs/decision-records/)
- Roadmap: [docs/roadmap](/docs/roadmap/)
- Шаблоны и примеры: [docs/templates](/docs/templates/), [docs/examples](/docs/examples/)
- UI / UX: [docs/ui-ux](/docs/ui-ux/)
- OpenAPI спецификация: [docs/api/openapi.yaml](/docs/api/openapi.yaml)

## Как читать

Начните с:

1. [Обзор / Модель домена](/docs/overview/README.md)
2. [Solution Architecture](/docs/architecture/solution-architecture.md)
3. [Модель рубрик](/docs/grading/rubric-model.md)
4. [Стратегия AI моделей](/docs/ai-models/model-strategy.md)
5. [Структура команд](/docs/teams/README.md) (префиксы)
6. [Ротация Iteration Lead](/docs/processes/team-rotation.md)
7. [Scrum адаптация](/docs/processes/scrum-intro.md)

Ключевые процессы и критерии:

- DoR / DoD и Quality Gates: [Критерии DoR/DoD](/docs/processes/dod-dor.md)
- Release & SemVer flow: [Процесс релизов](/docs/processes/release.md)
- Labels таксономия и автоматизация: [Таксономия labels](/docs/processes/labels.md)

## Содействие

Смотрите `CONTRIBUTING.md` и шаблоны задач в `.github/ISSUE_TEMPLATE`.

### Дополнительно

- [Структура команд](/docs/teams/README.md)
- [Ротация лидов](/docs/processes/team-rotation.md)
- [Scrum итерации](/docs/processes/scrum-intro.md)
- Владение кодом: `.github/CODEOWNERS`

#### Критерии и процессы

- DoR / DoD: [Критерии DoR/DoD](/docs/processes/dod-dor.md)
- Release: [Процесс релизов](/docs/processes/release.md)
- Labels: [Таксономия labels](/docs/processes/labels.md)

## Лицензия

Временно: лицензия ещё не выбрана. План: добавить SPDX идентификатор и текст (например, Apache-2.0 или MIT) до первой публичной версии. Пока что контент предоставляется «AS IS» без гарантий. См. файл `LICENSE` (placeholder) при его появлении.

## Состояние

Структура создаётся. Содержимое файлов — скелет.

## Улучшения в планах

- Массовый автофикс markdown (lint) оставшихся файлов
- Расширение OpenAPI (аутентификация, rubrics, webhooks)
- RBAC матрица и политика версионирования рубрик
- Диаграммы (C4, последовательности, потоки данных)
- Метрики качества AI (drift, hallucination rate) в отдельном документе
