# ERD — модель данных платформы

Диаграмма охватывает курсы, группы, студентов, задания, отправки (PR), оценки, интеграции GitHub и артефакты AI.

```mermaid
erDiagram
  User ||--o{ UserRole : has
  Role ||--o{ UserRole : has
  Role ||--o{ RolePermission : grants
  Permission ||--o{ RolePermission : contains

  Course ||--o{ Lab : contains
  Course ||--o{ CourseGroup : has
  Group ||--o{ CourseGroup : participates
  Group ||--o{ Membership : has
  User ||--o{ Membership : joins

  Lab ||--o{ Assignment : defines
  Assignment ||--o{ Enrollment : targets
  User ||--o{ Enrollment : enrolls

  Assignment ||--o{ Submission : receives
  Submission ||--o{ Artifact : stores
  Submission ||--o{ Evaluation : produces
  Evaluation ||--o{ CriterionScore : has
  Evaluation ||--o{ QuestionPack : yields

  GithubRepo ||--|| Course : owns
  GithubRepo ||--o{ Webhook : has
  GithubRepo ||--o{ PullRequest : receives
  PullRequest ||--|| Submission : mapsTo

  Evaluation ||--o{ AiInvocation : invokes

  User ||--o{ TokenSecret : has

  User ||--o{ Notification : receives
  Group ||--o{ Notification : receives
```

## Ключевые сущности (кратко)

- User(id, name, email)
- Group(id, name)
- Membership(userId, groupId, roleInGroup)
- Course(id, title, semester)
- CourseGroup(courseId, groupId)
- Lab(id, courseId, title, requirementsUrl, deadline)
- Assignment(id, labId, rubricVersionId, repoTemplate)
- Enrollment(userId, assignmentId, status)
- Submission(id, assignmentId, studentId, prUrl, commit, status, score)
- Artifact(id, submissionId, path, url, kind)
- Evaluation(id, submissionId, rubricVersion, completedAt, modelSnapshot, summary)
- CriterionScore(id, evaluationId, criterionId, weight, value, max, comments, aiAssisted)
- QuestionPack(id, evaluationId, questionsJson, visibleToStudent)
- GithubRepo(id, courseId, org, name, url)
- Webhook(id, repoId, event, targetUrl)
- PullRequest(id, repoId, number, head, base, url, author)
- AiInvocation(id, evaluationId, modelKey, provider, inputHash, outputRef, latencyMs, cost)
- TokenSecret(id, ownerUserId, kind, scope, createdAt, expiresAt)
- Notification(id, targetUserId?, targetGroupId?, channel, payload, status)

## Замечания по нормализации

- Рубрики и версии рубрик — отдельные таблицы (см. grading/rubric-model.md)
- PR ↔ Submission — 1:1 для простоты, при повторной отправке создаётся новый Submission
- QuestionPack хранит вопросы в JSON (также истории версий по желанию)
