# Основные ресурсы API (черновик V1)

Ниже приведён обзор REST ресурсов и операций. Детали схем — в openapi.yaml и models.md. Роли и доступы соответствуют permissions-matrix.md.

## Auth

- POST /auth/token — получение JWT
- GET /auth/me — текущий пользователь

## Users / Groups / Courses

- GET/POST/PATCH/DELETE /users
- GET/POST/PATCH/DELETE /groups
- GET/POST/PATCH/DELETE /courses
- POST /courses/:courseId/groups/:groupId — привязать группу к курсу

## Labs / Assignments

- GET/POST /courses/:courseId/labs
- GET/PATCH/DELETE /labs/:labId
- POST /labs/:labId/assignments — создать задание (выбор рубрики/шаблона)
- GET /labs/:labId/assignments

## Rubrics

- GET /rubrics (published)
- POST /rubrics (draft)
- GET/PATCH /rubrics/:id (draft)
- POST /rubrics/:id/publish

## Submissions

- POST /submissions — создать отправку (в т.ч. из PR)
- GET /submissions?studentId&labId — список
- GET /submissions/:id — подробности
- POST /submissions/:id/regrade — запрос на переоценку

## Evaluations

- GET /evaluations?submissionId — список
- GET /evaluations/:id — детально
- POST /evaluations/:id/publish-preliminary — сделать видимой студенту

## Questions (защита)

- POST /submissions/:id/questions/generate — запуск генерации
- GET /submissions/:id/questions — чтение пакета вопросов (учёт видимости)
- PATCH /questions/:id — правки преподавателя

## Integrations / GitHub

- POST /integrations/github/token — сохранить токен (скоуп: пользователь/курс)
- POST /integrations/github/repo/bootstrap — создать/синхронизировать структуру
- POST /integrations/github/webhooks — установить вебхук
- GET /integrations/github/check — проверка связности (репо/вебхук/прав)

## Webhooks (Inbound)

- POST /webhooks/github — универсальный вход для событий GitHub (PR opened/synchronize)
- POST /webhooks/evaluation-completed — уведомление о завершении оценки (уже в openapi)

## Models (AI)

- GET /models — список доступных моделей
- POST /models/test — пробный вызов
- POST /ai/evaluate — служебный запуск оценки submission (для перезапуска)

## Notifications

- GET /notifications — канал уведомлений пользователя
- POST /notifications/broadcast — от преподавателя группе/курсу

## Пагинация и ошибки

- Общие параметры page, pageSize
- Единая схема errors.Error

## Безопасность

- bearerAuth (JWT)
- Доп. проверка прав через RBAC и scope (course/user)
