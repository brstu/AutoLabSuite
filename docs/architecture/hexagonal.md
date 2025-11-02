# Гексагональная архитектура (Ports & Adapters)

Автономные доменные правила отделены от инфраструктуры. Все внешние системы подключаются через порты/адаптеры, что упрощает тестирование, замену провайдеров и эволюцию системы.

## Слои

- Домен
  - Сущности: Course, Group, Student, Enrollment, Lab, Assignment, Submission, Evaluation, Rubric, Criterion, QuestionPack, AiInvocation, GithubRepo, TokenSecret
  - Агрегаты: Course (owns Labs), Group (owns Memberships), Submission (owns Artifacts, Evaluation refs)
  - Доменные сервисы: GradingService, RubricService, QuestionGenerationService
- Приложение (Application Services)
  - Команды/кейсы: CreateCourse, AddLab, EnrollGroup, CreateSubmissionFromPR, RunEvaluation, PublishPreliminaryGrade, AcceptOrDispute, GenerateDefenseQuestions
  - Оркестрация workflow: реакция на вебхуки GitHub, очереди оценки, нотификации
- Адаптеры (внешние)
  - Inbound (Driving): REST API, Webhook (GitHub), UI actions
  - Outbound (Driven): GitHub API, KServe InferenceService, Object Storage (S3), DB, Notifications (email/gh issues), Secrets (Vault)

## Порты

- GitHostingPort
  - getRepo(repoId), createRepo(template?), createIssue, listPullRequests, getPullRequest, getCommit, createWebhook, addCollaborators
- SubmissionStoragePort
  - storeArtifact, fetchArtifact, listArtifacts
- EvaluationPort
  - enqueueEvaluation(submissionId), getEvaluationStatus, persistEvaluation
- ModelServingPort
  - infer(modelKey, payload, options?){ provider: external|kserve, traceId }
- NotificationPort
  - notify(userId|groupId, channel, payload)
- SecretsPort
  - putToken(scope, key), getToken(scope, key), rotateToken

## Адаптеры

- GitHubAdapter (GitHostingPort)
  - Использует персональный/орг‑токен преподавателя. Создание минимальной структуры репозитория курса/лабы, подписка на PR вебхуки.
- KServeAdapter (ModelServingPort)
  - Вызов локальных моделей через Kubeflow/KServe (REST/gRPC). Поддержка нескольких моделей и агрегации ответов.
- OpenAI/ExternalLLMAdapter (ModelServingPort)
  - Вызов внешних API с политиками троттлинга/квот.
- S3Adapter (SubmissionStoragePort)
- PostgresAdapter (репозитории доменных сущностей)
- Email/GHNotificationsAdapter (NotificationPort)
- Vault/KeyVaultAdapter (SecretsPort)

## Основные сценарии через порты

1. Преподаватель создаёт курс и сохраняет GitHub токен
- UI → API → SecretsPort.putToken(userScope, github)
- API → GitHostingPort.createRepo(template) для курса/лабы → createWebhook

1. Студент сдаёт лабу через fork + PR
- GitHub Webhook (PR opened/synchronized) → Inbound Adapter → Application.useCase(CreateSubmissionFromPR)
- GitHostingPort.getPullRequest → архив артефактов → SubmissionStoragePort.storeArtifact
- EvaluationPort.enqueueEvaluation

1. Workflow «Проверка на директорию»
- GradingService в песочнице проверяет наличие нужной директории/файлов (пример WT-AC-2025)
- После минимальных проверок формируется prompt (см. prepare_AI_prompt.py) и передаётся нескольким ModelServingPort адаптерам
- Результаты сохраняются (только преподавателю), далее PublishPreliminaryGrade делает видимым превью

1. Принятие/оспаривание
- Студент принимает оценку → статус «готов к защите»
- При несогласии — новая итерация PR или комментарий, повторная отправка

1. Генерация вопросов на защиту
- QuestionGenerationService → ModelServingPort с контекстом: тема, рубрика, фрагменты кода студента → QuestionPack

## Контракты (эскиз)

- infer(modelKey, payload) → { output, usage?, score?, reasons?, attachments? }
- enqueueEvaluation(submissionId) → jobId
- notify(channel, payload) → accepted|failed

## Связанные документы

- solution-architecture.md
- ../ai-models/ml-tasks-kserve.md
- ../ai-models/model-strategy.md
- ../api/openapi.yaml
