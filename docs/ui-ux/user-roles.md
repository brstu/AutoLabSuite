# Пользовательские роли (Draft)

## Цели

Обозначить ключевые персоны (personas) и их задачи для приоритезации фич.

## Персоны

### Преподаватель

- Просмотр статуса всех групп и студентов
- Быстрая верификация спорных автопроверок
- Актуализация рубрик
- Запуск переоценивания батчем

### Студент

- Отправка / повторная отправка лабораторных
- Получение прозрачной обратной связи
- Сравнение попыток и улучшений
- Понимание дедлайнов и штрафов

### Maintainer / DevOps

- Наблюдаемость: очереди, задержки, ошибки
- Управление версиями моделей и рубрик
- Настройка лимитов и квот
- Диагностика отказов (trace / логи)

### Экзаменатор / Проверяющий внешней аттестации

- Доступ только к итоговым оценкам и артефактам
- Аудит логов критичных изменений

## Mapping Ролей к Доменным Областям

| Область | Преподаватель | Студент | Maintainer | Экзаменатор |
|---------|---------------|---------|-----------|-------------|
| Submissions | R/W | Create/View | View | View |
| Rubrics | Manage | View | Manage versions | View |
| AI Models | View metrics | - | Deploy/Config | - |
| Analytics | View | View own | Manage pipelines | View summaries |
| Settings | Scoped | - | Full | - |

## Следующие шаги

- Добавить матрицу разрешений (RBAC) на основе CRUD действий.
- Связать с моделью данных (таблицы users, roles, permissions).

## RBAC Матрица (Черновик)

| Ресурс / Действие | Студент | Преподаватель | Maintainer | Экзаменатор |
|-------------------|---------|---------------|-----------|-------------|
| Submission: Create | ✔ | ✔ (от имени) | ✖ | ✖ |
| Submission: Read Own | ✔ | ✔ | ✔ | ✔ (итог) |
| Submission: Read Any | ✖ | ✔ | ✔ | ✔ (итог) |
| Submission: Update (метаданные) | ✖ | Limited (метки) | ✔ | ✖ |
| Submission: Regrade Request | ✔ (ограничение по частоте) | ✔ (массово) | ✔ | ✖ |
| Rubric: Create | ✖ | ✔ | ✔ | ✖ |
| Rubric: Update | ✖ | ✔ (draft) | ✔ | ✖ |
| Rubric: Publish | ✖ | ✔ | ✔ | ✖ |
| Rubric: View Draft | ✖ | ✔ | ✔ | ✖ |
| Rubric: View Published | ✔ | ✔ | ✔ | ✔ |
| Evaluation: Read Own | ✔ | ✔ | ✔ | ✔ |
| Evaluation: Read Any | ✖ | ✔ | ✔ | ✔ |
| AI Model Config: Deploy | ✖ | ✖ | ✔ | ✖ |
| AI Model Metrics: View | ✖ | ✔ (агрегаты) | ✔ (детально) | ✖ |
| Settings: System | ✖ | ✖ | ✔ | ✖ |
| Settings: Course Scope | ✖ | ✔ | ✔ | ✖ |
| Audit Log: View | ✖ | Limited (по курсу) | ✔ | ✔ (read-only) |

Легенда: ✔ — разрешено, ✖ — запрещено, Limited — ограничено контекстом (курс, собственные разделы).

## Модель разрешений (План)

Переход от ролевой матрицы к атомарным разрешениям:

```text
permission keys (примеры):
  submissions.create
  submissions.read.any
  submissions.read.own
  submissions.regrade.request
  rubrics.publish
  models.deploy
```

Mapping roles → set(permission_keys). Хранение в таблицах:

- `roles (id, name, description)`
- `permissions (id, key, description)`
- `role_permissions (role_id, permission_id)`
- `user_roles (user_id, role_id, scope)` где scope (global|course_id)

Open question: поддержка временных (expiring) разрешений для аудита.
