# Release Process & Versioning

Документ описывает стратегию версионирования, подготовку и выпуск релизов AutoLabSuite. Применимо к backend сервисам,
frontend приложению, инфраструктурным манифестам и ML артефактам (версии моделей / датасетов).

## Цели

1. Предсказуемость и прослеживаемость изменений.
2. Быстрый rollback при инцидентах.
3. Минимизация ручных шагов (автоматизация pipeline).
4. Разделение частоты релизов по компонентам (backend чаще, инфраструктура осторожнее).

---

## Семантическое версионирование (SemVer)

`MAJOR.MINOR.PATCH` с расширениями для моделей.

| Тип | Триггер | Пример | Ноты |
|-----|---------|--------|------|
| MAJOR | Ломающие изменения API / протокола / контракта данных | 2.0.0 | Требует миграционный гайд |
| MINOR | Добавление обратносуместимых возможностей | 1.3.0 | Может включать новые эндпоинты |
| PATCH | Исправление дефектов без изменения контрактов | 1.3.4 | Не меняет схемы |
| BUILD META | Доп. метки CI (optional) | 1.3.4+build.17 | Не влияет на сравнение |

### ML Артефакты

Для моделей: `MODEL_MAJOR.MODEL_MINOR.MODEL_PATCH` независимы от версии сервиса, но сервис фиксирует используемую модель в
конфиге/таблице. Dataset version — хеш или инкремент + дата (например: `2025.09.29-r1`).

---

## Ветвление

| Ветка | Назначение | Правила merge |
|-------|------------|---------------|
| main | Production-ready состояние | Только через PR, зелёный CI |
| develop (опционально) | Интеграция фич (если поток > 5 параллельных веток) | Синх с main после релиза |
| feature/* | Фича / задача | Ребейз/merge сверху main/develop |
| hotfix/* | Срочные исправления prod | Отводятся от main, вливаются в main + develop |
| release/* | Подготовка релиза (заморозка) | Создаётся по необходимости для стабилизации |

Если поток изменений невысокий — можно исключить `develop`, работая через короткоживущие feature-ветки прямо от `main`.

---

## Теги

- Формат: `vMAJOR.MINOR.PATCH` (например `v1.4.2`).
- Генерируются автоматически pipeline после merge релизного PR.
- Для моделей: `model-<name>-vX.Y.Z` (например `model-grader-v1.2.0`).
- Для датасетов: `dataset-<domain>-2025-09-29-r1`.

---

## CHANGELOG

Файл `CHANGELOG.md` в корне, формат Keep a Changelog.

Секции:

- Added
- Changed
- Deprecated
- Removed
- Fixed
- Security

Черновые изменения аккумулируются в разделе `## [Unreleased]`.

Автоматизация: скрипт/Action:

1. Парсит PR labels / заголовки.
2. Обновляет разделы.
3. Создаёт PR с bump версией.

---

## Процесс выпуска (Flow)

1. Подготовка: убедиться что `CHANGELOG` отражает изменения (Unreleased заполнен).
2. Определить целевой уровень (patch/minor/major) — по содержанию PR/metainfo.
3. Запуск релизного workflow (manual dispatch или автоматический label `release-candidate`).
4. Pipeline шаги:
   - Тесты / линтеры / сборка
   - Генерация артефактов (образы, пакеты, модельные веса)
   - Версионирование: обновление версий в package.json / helm chart / pyproject / модельном registry
   - Генерация/обновление CHANGELOG (перенос Unreleased в новую секцию)
   - Подпись (поддерживаемая часть, если применимо) и создание git tag
   - Публикация артефактов (Container Registry, модельный стор, static build)
5. Авто-комментарий в релизный PR с итогами (версии, ссылки на артефакты).
6. Merge → tag пушится → оповещение (Slack/Webhook).

### Post-release

- Мониторинг: проверка ключевых SLO (ошибки, latency, cost spikes)
- Если инцидент: hotfix ветка → быстрый patch release
- Планирование переоценок ML моделей (если drift обнаружен)

---

## Hotfixes

| Шаг | Действие |
|-----|----------|
| 1 | Создать ветку `hotfix/<short>` от `main` |
| 2 | Исправление + тесты |
| 3 | PR → ускоренный review (1 апрув достаточен) |
| 4 | Merge в `main`, запуск сокращённого релиз workflow (skip lengthy build steps если кешируемо) |
| 5 | Cherry-pick в `develop` (если используется) |

Hotfix повышает PATCH версию (если не затрагивает контракты). Если затрагивает — MINOR/MAJOR и отдельная коммуникация.

---

## Автоматизация (GitHub Actions пример блоков)

| Блок | Назначение |
|------|-----------|
| build-test | Сборка, тесты, линт |
| semantic-version | Определение bump (analyze commits/labels) |
| changelog-update | Генерация секций по метаданным PR |
| artifact-publish | Публикация docker/image/npm/pypi |
| model-publish | Загрузка модели в registry + тег |
| notify | Slack/Webhook оповещения |

Параметризация через inputs: bump_override, dry_run.

---

## Семантика labels для релизов

| Label | Категория CHANGELOG | Версия bump hint |
|-------|---------------------|------------------|
| feature | Added | minor |
| enhancement | Changed | minor |
| bug | Fixed | patch |
| security | Security | patch/major (case-by-case) |
| breaking | Changed/Removed | major |
| deprecated | Deprecated | minor |
| removed | Removed | major |

Если присутствует `breaking` — приоритетен major независимо от других.

---

## Реестр версий (пример таблицы)

| Компонент | Текущая версия | Источник правды |
|-----------|----------------|-----------------|
| backend-api | v1.2.3 | package.json / tag |
| frontend-ui | v1.4.0 | package.json / tag |
| ai-gateway | v0.3.1 | pyproject/poetry.lock |
| grading-model (LLM prompt) | 2025.09.20-prompt-v3 | prompt repo hash |
| grader-ml-model | model-grader-v1.2.0 | model registry |
| dataset-core | dataset-core-2025-09-15-r2 | dataset tags |

---

## Rollback

| Тип | Подход |
|-----|--------|
| Backend/Frontend | Re-deploy предыдущего image tag |
| Infrastructure | Helm rollback / terraform state version pin |
| Model | Переключить model_version в конфиге/feature flag |
| Dataset | Отметить предыдущий snapshot как active |
| Prompt | Указать предыдущий prompt_version hash |

Checklist при rollback:

- Подтверждение проблемы (ошибка SLO / баг / регрессия)
- Выбор целевой стабильной версии
- Коммуникация (канал #release-ops)
- Выполнение отката и верификация health checks
- Post-mortem (если инцидент severity ≥ medium)

---

## Связанные документы

- `dod-dor.md`
- `../architecture/grading-module.md`
- `../ai-models/model-strategy.md`
- `../grading/rubric-versioning.md`

---

## ToDo / Улучшения

- Авто semantic version расчет (commit conventional, labels fallback)
- Генерация SBOM + лицензии в артефакте
- Интеграция с модели registry (авто-тег при метриках > threshold)
- SLA на время выхода hotfix (< 30 мин от merge до deploy)
