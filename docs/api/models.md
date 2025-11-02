# Модели данных (основные сущности)

Это текстовое описание ключевых моделей, соответствующее ERD. Полные схемы и валидации отражаются в OpenAPI/ORM.

## User

- id: string (UUID)
- name: string
- email: string
- roles: [Role] (через user_roles с scope)

## Group

- id, name
- members: [Membership]

## Course

- id, title, semester, ownerId
- groups: [Group]
- labs: [Lab]
- githubRepo: GithubRepo

## Lab

- id, courseId, title, requirementsUrl, deadline
- assignments: [Assignment]

## Assignment

- id, labId
- rubricVersionId
- repoTemplate (branch/tag/path)

## Enrollment

- id, userId, assignmentId, status (active|dropped)

## Submission

- id, assignmentId, studentId
- prUrl, commit, createdAt, status (queued|running|completed|failed)
- score (nullable), breakdown: [CriterionScore]

## Artifact

- id, submissionId
- path (logical), url (object storage), kind (archive|report|trace)

## Evaluation

- id, submissionId, rubricVersion, completedAt
- summary, modelSnapshot (строка с версиями моделей)

## CriterionScore

- id, evaluationId, criterionId
- weight, value?, max, comments?, aiAssisted

## Rubric / RubricVersion / Criterion

- См. docs/grading/rubric-model.md

## QuestionPack

- id, evaluationId
- questions: JSON ( [{question, topic, difficulty}] )
- visibleToStudent: bool

## GithubRepo / PullRequest / Webhook

- repo(org, name, url), webhook(event, targetUrl), pr(number, head, base, url, author)

## AiInvocation

- id, evaluationId, modelKey, provider(external|kserve)
- inputHash, outputRef, latencyMs, cost

## TokenSecret

- id, ownerUserId, kind(github|smtp|kserve), scope(user|course|system), createdAt, expiresAt

## Notification

- id, targetUserId?, targetGroupId?, channel(app|email|github), payload(JSON), status
