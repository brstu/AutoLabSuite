# Матрица прав (RBAC)

Этот документ конкретизирует атомарные разрешения, роли и области (scopes). Основано на user-roles.md.

## Области действия

- global — системные операции (администрирование, модели, интеграции)
- course:{id} — курс и его ресурсы (лабы, группы, отправки)
- group:{id} — группа и её участники
- user:{id} — персональные данные/ресурсы (собственные отправки)

## Атомарные разрешения (permission keys)

- users.create, users.read, users.update, users.delete
- groups.create, groups.read, groups.update, groups.delete
- courses.create, courses.read, courses.update, courses.delete
- enrollments.manage
- labs.create, labs.read, labs.update, labs.delete, labs.publish
- rubrics.create, rubrics.read, rubrics.update, rubrics.publish
- submissions.create, submissions.read.own, submissions.read.any, submissions.update.meta, submissions.regrade.request
- evaluations.read.own, evaluations.read.any, evaluations.publish.preliminary
- questions.generate, questions.read, questions.update
- models.deploy, models.configure, models.metrics.read
- integrations.github.token.write, integrations.github.webhook.manage, integrations.github.repo.bootstrap
- notifications.send, notifications.read
- settings.system.read, settings.system.write
- audit.read

## Роли → Разрешения

- Админ (global)
  - users.*, groups.*, courses.*, enrollments.manage
  - labs.*, rubrics.*
  - submissions.read.any, submissions.update.meta, submissions.regrade.request
  - evaluations.read.any, evaluations.publish.preliminary
  - models.*, integrations.github.*, settings.system.*, audit.read
  - notifications.send
- Преподаватель (course scope)
  - courses.read, labs.*, rubrics.*
  - groups.read, enrollments.manage
  - submissions.create (от имени), submissions.read.any, submissions.update.meta, submissions.regrade.request
  - evaluations.read.any, evaluations.publish.preliminary
  - questions.generate, questions.read, questions.update
  - integrations.github.webhook.manage, integrations.github.repo.bootstrap, integrations.github.token.write (свой)
  - models.metrics.read
  - notifications.send
- Студент (user scope + course scope)
  - submissions.create, submissions.read.own
  - evaluations.read.own
  - questions.read
  - submissions.regrade.request (rate-limited)
- Maintainer/DevOps (global)
  - models.*, settings.system.*, integrations.github.*, audit.read
- Экзаменатор (course scope)
  - submissions.read.any (итоги), evaluations.read.any, rubrics.read

## Примечания

- Ограничение частоты (rate‑limit) на submissions.regrade.request
- Разрешения привязываются к ролям через таблицу role_permissions, а к пользователям — через user_roles со scope
- Временные разрешения для аудита (expiring) — опционально
