# AutoLabSuite Backend

Папка для backend-сервисов (ядро оценки, API, интеграции).

## Предполагаемый стек

- Python / FastAPI (основной API слой)
- Async (uvicorn, httpx)
- Очереди / фоновые задачи: Celery / Dramatiq / RQ + Redis
- Хранилища: PostgreSQL, MinIO/S3 (артефакты), Elasticsearch (поиск), Redis (кеш)
- Контейнеризация: Docker + Compose / K8s манифесты в `infra/`

## Модули (ориентировочно)

- Submission Ingestion
- Grading Engine (правила, тестовые раннеры, рубрики)
- AI Assist Proxy (обращения к LLM/моделям)
- Feedback Generator (формирование отчётов)
- Audit & Trace (лог событий, объяснимость)

## Тестирование

- Unit: pytest
- Contract / API: schemathesis / newman (опционально)
- Integration: docker-compose profile

## Ответственность команды

Текущие владельцы — команда BE (см. [docs/teams/README.md](/docs/teams/README.md)).
