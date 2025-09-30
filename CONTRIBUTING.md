# Вклад в LabAutomation

Спасибо за интерес к проекту! Этот документ описывает, как эффективно внести вклад.

## Роли

- Maintainer: управление архитектурой, ревью ключевых изменений
- Reviewer: код-ревью, проверка документации
- Contributor: добавление фич, фиксы, улучшения
- Domain Expert: предметные критерии оценивания

## Быстрый старт для контрибьютора

1. Ознакомьтесь с `README.md` и [docs/overview/README.md](/docs/overview/README.md)
2. Найдите или создайте задачу (Issue)
3. Форк / ветка от `main` (`feature/<slug>`)
4. Добавьте/обновите документацию при изменении поведения
5. Обновите CHANGELOG (секция Unreleased)
6. Откройте Pull Request с шаблоном
7. Сопоставьте изменения с критериями DoD / Quality (см. [docs/processes/dod-dor.md](/docs/processes/dod-dor.md))

Полезные документы:

- Релизы и SemVer: [docs/processes/release.md](/docs/processes/release.md)
- Система labels: [docs/processes/labels.md](/docs/processes/labels.md)
- DoR / DoD: [docs/processes/dod-dor.md](/docs/processes/dod-dor.md)

## Стандарты

### Git

- Conventional Commits (`feat:`, `fix:`, `docs:`, `chore:`, `refactor:`)
- Минимальные PR: одна логическая единица
- Rebase перед merge

### Документация

- Русский язык, чёткие определения
- Диаграммы: PlantUML / Mermaid ([docs/architecture/diagrams/](/docs/architecture/diagrams/))
- ADR: [docs/decision-records/](/docs/decision-records/) (см. шаблон)

#### Линтинг документации

- Автоматический запуск: GitHub Action `super-lint`.
- Локально: установите markdownlint-cli (`npm install -g markdownlint-cli`) и выполните:  
  `markdownlint "**/*.md" -c .markdownlint.yaml`
- Правила описаны в `.markdownlint.yaml` (комментарии поясняют отключения).
- Стиль маркеров списков: используем `-` (см. правило MD004). При правке существующих файлов не смешивайте `*` и `-`.
- Длинные строки (таблицы, ссылки) допустимы — правило длины отключено.

### Код (когда появится)

- Статический анализ и форматирование (определим позже)
- Тесты обязательны для ядра оценивания

## Процесс ревью

| Шаг | Описание |
|-----|----------|
| Self-check | Прогон локальных проверок, обновление docs |
| Reviewer assignment | Maintainer назначает минимум 1 reviewer |
| Review | Комментарии, запросы правок |
| Approval | Минимум 1 approve (+ security при изменении моделей) |
| Merge | Fast-forward / squash по ситуации |

### Waived Criteria

Если в рамках PR временно снимается пункт DoD / Quality (коды D*/Q*/L* из `dod-dor.md`), это фиксируется в секции *Waived Criteria* PR шаблона:

1. Укажите код(ы) (например: D5, Q2)
2. Кратко причину (например: "нет тестовых данных", "временный технический долг")
3. Ссылку на follow-up issue (если требуется)
4. Оценку срока погашения (release / sprint)

## Шаблоны и процессы

| Тип | Файл / Локация | Назначение |
|-----|-----------------|------------|
| Pull Request Template | `.github/PULL_REQUEST_TEMPLATE.md` | Единообразное описание изменений, DoD чеклист |
| Rotation Issue | `.github/ISSUE_TEMPLATE/rotation.md` | Смена Iteration Lead, handoff чеклист |
| PR (расширенный пример) | [docs/templates/pr-template.md](/docs/templates/pr-template.md) | Альтернативный минималистичный вариант |
| ADR Template | [docs/decision-records/adr-000-template.md](/docs/decision-records/adr-000-template.md) | Фиксация архитектурных решений |
| DoR / DoD | [docs/processes/dod-dor.md](/docs/processes/dod-dor.md) | Единый источник критериев |
| Release Process | [docs/processes/release.md](/docs/processes/release.md) | Версионирование и flow релизов |
| Labels | [docs/processes/labels.md](/docs/processes/labels.md) | Таксономия меток и автоматизация |

См. также:

- Ротация: [docs/processes/team-rotation.md](/docs/processes/team-rotation.md)
-- Scrum адаптация: [docs/processes/scrum-intro.md](/docs/processes/scrum-intro.md)
- Release & Versioning: [docs/processes/release.md](/docs/processes/release.md)
- DoR/DoD: [docs/processes/dod-dor.md](/docs/processes/dod-dor.md)
- Labels: [docs/processes/labels.md](/docs/processes/labels.md)
- Команды и префиксы: [docs/teams/README.md](/docs/teams/README.md)

## Безопасность

Уязвимости сообщайте приватно (см. SECURITY.md когда появится).

## Лицензия вклада

Отправляя изменения, вы подтверждаете согласие с лицензией репозитория.
