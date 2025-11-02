# Backend Service — Java 21+/Spring Boot 3 (Hexagonal)

Предлагаемый технологический стек:

- Java 21+ (target 25 later), Spring Boot 3.x, Maven
- PostgreSQL, Spring Data JPA, Hibernate
- Lombok для снижения шаблонного кода
- Тестирование: JUnit 5, Mockito, AssertJ, Testcontainers (Postgres)

Архитектурный стиль: Hexagonal (Ports & Adapters) с разделением API, Service, Domain, Persistence.

## Проектная структура (Maven multi-module)

```
services/backend/
  pom.xml                       # parent/aggregator
  domain/                       # чистая модель + порты (интерфейсы)
    pom.xml
  application/                  # use-cases (сервисы оркестрации)
    pom.xml
  adapter-persistence-postgres/ # адаптер хранилища (JPA, Postgres)
    pom.xml
  adapter-github/               # адаптер GitHub (VCS)
    pom.xml
  adapter-ml/                   # адаптер ML-провайдеров (лог метрик)
    pom.xml
  app/                          # Spring Boot приложение (web/actuator)
    pom.xml
  tests/                        # срезанные тесты (WebMvcTest и т.п.)
    pom.xml
```

Альтернатива: single-module с пакетной изоляцией, если multi-module избыточен на старте.

## Слой Domain

- Entities: User, Role, Course, Collection, Lab, Submission, Evaluation, Rubric.
- Ports (interfaces):
  - SubmissionPort, CollectionPort, LabPort, EvaluationPort, RubricPort, UserPort
- Бизнес‑правила: инварианты Submission, публикации предварительной оценки и т.п.

## Слой Application (Use Cases)

- Сервисные кейсы: CreateCollection, AddLabToCollection, CreateSubmissionFromWebhook, PublishPreliminaryEvaluation, RequestRegrade
- Транзакционные границы и ретраи там, где нужно.

## Слой API (в модуле app)

- Spring MVC controllers, DTO mappers (MapStruct или вручную)
- Контракты соответствуют `docs/api/openapi.yaml` (Collections, Labs, Submissions, Evaluations, Rubrics, Auth)

## Слой Persistence (adapter-persistence-postgres)

- JPA сущности и Spring Data репозитории
- Адаптеры реализуют порты домена, маппинг из/в доменные модели
- Flyway/Liquibase для миграций БД (план)

## Тестирование (сводка)

- Unit: JUnit 5 + Mockito + AssertJ
- Интеграционные: Testcontainers (PostgreSQL)
- Контроллеры: WebMvcTest для быстрых тестов API

## Следующие шаги

1. Завершить доменную модель: Collection/Lab, Submission, Evaluation, Rubric + порты
2. REST: Collections/Labs endpoints по OpenAPI (контроллеры в app)
3. Persistence: добавить миграции Flyway и репозитории для доменных агрегатов
4. Интеграция с Postgres через Testcontainers в CI
5. Security: JWT Bearer фильтр и конфигурация Spring Security
6. ML: расширить адаптер-ml до вызовов провайдеров; хранение и экспорт метрик

## Системные требования и сборка

- Требуется JDK 21+ и Maven 3.9+
- Если у вас настроен JDK 25, проект совместим, но по умолчанию компилируется под Java 21 (см. `pom.xml` в корне модуля backend). Это можно изменить, обновив свойство `java.version`.
- Переменная окружения `JAVA_HOME` должна указывать на JDK (не JRE).

Быстрая проверка (локально):

- Запуск сборки: parent pom в `services/backend/pom.xml` (или используйте панели Maven в VS Code)
  - package (с пропуском тестов на старте)
  - модуль `app` предоставляет эндпоинт `GET /health` (WebMvcTest покрытие в модуле `tests`).


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
