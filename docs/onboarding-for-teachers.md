# Onboarding для преподавателей — AutoLabSuite

Добро пожаловать! Этот документ — краткое руководство для преподавателей, менторов и ревьюеров, которые будут помогать студентам в рамках проекта AutoLabSuite.

Цель: дать быстрый обзор проекта, перечислить ключевые материалы и показать, как быстро включиться в процесс поддержки студентов.

---

## Краткое описание проекта

AutoLabSuite — платформа и набор инструментов для автоматизации оценки лабораторных работ и сопровождения учебного процесса. Проект включает:

- Документацию по продукту, архитектуре и процессам (папка [docs/](docs/)).
- Компоненты фронтенда и бэкенда ([/apps/frontend](/apps/frontend), [/services/backend](/services/backend)).
- Модульные и интеграционные тесты, в том числе для AI/ML-компонентов ([ml/tests](ml/tests)).
- Шаблоны и процессы для работы с задачами и ротацией тим-лидов ([.github/](.github/), [docs/teams](docs/teams)).

Ваша роль: поддерживать студентов, проводить ревью, помогать с оценкой и улучшать материалы.

---

## С чего начать (быстрый старт)

1. Овладейте общей навигацией:
   - [/docs/README.md](/docs/README.md) — общий обзор и карта документации.
   - [docs/overview/README.md](docs/overview/README.md) — концепция и модель использования.

2. Ознакомьтесь с организацией команд и контактами:
   - [docs/teams/current-leads.md](docs/teams/current-leads.md) — кто сейчас в ротации, контакты.
   - [docs/teams/README.md](docs/teams/README.md) — структура команд и практики коммуникации.

3. Понимание оценивания:
   - [grading/rubric-model.md](grading/rubric-model.md) — модель рубрик и критерии оценивания.
   - [grading/rubric-versioning.md](grading/rubric-versioning.md) — политика версионирования рубрик.

4. ML / автооценка (если релевантно):
   - [ml/tests/AI_testing_strategy.md](ml/tests/AI_testing_strategy.md) — стратегия тестирования AI-модулей.
   - [ml/tests/README.md](ml/tests/README.md) — как запускать тесты и утилиты.

5. Кодовая база (если хотите посмотреть реализацию):
   - [services/backend/README.md](services/backend/README.md) — бэкенд: архитектура и точки входа
   - [/apps/frontend/README.md](/apps/frontend/README.md) — фронтенд: запуск и структура

6. Процессы и шаблоны:
   - [.github/ISSUE_TEMPLATE/rotation.md](.github/ISSUE_TEMPLATE/rotation.md) — шаблон для ротации тим-лидов
   - [docs/templates/meeting-notes.md](docs/templates/meeting-notes.md) и [docs/teams/templates/agenda.md](docs/teams/templates/agenda.md) — шаблоны для встреч и заметок

---

## Быстрая чек‑лист для первого дня

- [ ] Прочитать [/docs/README.md](/docs/README.md) и [docs/overview/README.md](docs/overview/README.md).
- [ ] Ознакомиться с [grading/rubric-model.md](grading/rubric-model.md) — важно для согласованного оценивания.
- [ ] Просмотреть [ml/tests/AI_testing_strategy.md](ml/tests/AI_testing_strategy.md), если будете проверять ML‑часть.

---

## Полезные ссылки в репозитории

- [/docs/README.md](/docs/README.md) — обзор
- [docs/overview/README.md](docs/overview/README.md) — концепция
- [docs/teams/current-leads.md](docs/teams/current-leads.md) — текущие лиды и контакты
- [grading/rubric-model.md](grading/rubric-model.md) — рубрики
- [ml/tests/AI_testing_strategy.md](ml/tests/AI_testing_strategy.md) — тесты AI
- [/apps/frontend/README.md](/apps/frontend/README.md) — фронтенд
- [services/backend/README.md](services/backend/README.md) — бэкенд
- [.github/ISSUE_TEMPLATE/rotation.md](.github/ISSUE_TEMPLATE/rotation.md) — шаблон ротации

Спасибо за участие — ваш вклад сильно улучшит качество поддержки студентов.
