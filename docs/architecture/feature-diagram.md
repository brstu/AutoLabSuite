# Диаграмма фич AutoLabSuite

Карта ключевых возможностей системы, сгруппированных по ролям и подсистемам. Диаграмма предназначена для быстрого ориентирования по объёму фич и их связям.

```mermaid
mindmap
  root((AutoLabSuite))
    Пользователи
      Студент
        Локальный логин (email/пароль)
        Форк репозитория
        PR как отправка работы
        Статус отправки и базовые проверки
        Просмотр предварительной оценки
        Принять/Оспорить оценку
        Вопросы преподавателю
        Подготовка к защите (генерация вопросов)
      Преподаватель
        Курсы и группы
        Лабы и задания
        Рубрики (версии, критерии)
        Уведомления группам
        Обзор отправок (фильтры/поиск)
        Детали отправки: артефакты, PR, AI
        Запрос повторной проверки (regrade)
        Публикация предварительной оценки
        Запрос доработки/комментарии
        Пакет вопросов на защиту
      Администратор
        Управление ролями и правами (RBAC)
        Пользователи и интеграции
        Секреты/токены провайдеров
        Вебхуки GitHub
      Мейнтейнер
        Регистрация моделей/провайдеров
        KServe endpoints
        Ротация ключей провайдеров
        Наблюдаемость/дашборды
    Ядро оценивания
      Базовые проверки структуры
      Построение prompt
      Мульти‑модельная AI‑оценка
      Нормализация/валидация ответа
      Счёт по критериям
      Агрегация в итоговую оценку
    Интеграции
      GitHub
        Fork/PR workflow
        Webhook → Submission
        PR ↔ Отправка (трекинг)
      AI Gateway
        /health, /models
        models/<key> infer
        /grade (нормализованный вывод)
      KServe
        Локальный inference
      Notifications
        Почта/чат (абстракция)
      Secrets
        Хранилище токенов
    API
      Submissions: CRUD, regrade
      Evaluations: publish‑preliminary
      Webhooks intake
      Auth/Scopes
    Данные
      Users/Roles/Permissions
      Courses/Groups
      Collections/Labs (Assignments)
      Submissions/Evaluations/CriterionScores
      Rubrics
      Questions
      Notifications
    Security & Compliance
      Аутентификация/авторизация
      Scope‑ограничения
      PII/Privacy политика
      Аудит действий
    Операции
      Наблюдаемость/логи/трейсы
      SLO/SLA
      Бэкапы/восстановление
```

Примечание: если mindmap не рендерится в вашей среде, скажите — добавлю альтернативную версию (flowchart) в этом же файле.

## Диаграмма фич (MVP)

Минимальный набор возможностей для запуска Phase 1 (Inline Adapter).

```mermaid
mindmap
  root((AutoLabSuite MVP))
    Пользователи
      Студент
        Локальный логин (email/пароль)
        Форк репозитория
        PR как отправка
        Статус отправки
        Просмотр предварительной оценки
        Принять/Оспорить оценку
      Преподаватель
        Лабы и задания
        Обзор отправок
        Публикация предварительной оценки
        Запрос доработки/комментарии
      Администратор
        Роли и права (RBAC)
        Вебхуки GitHub
    Ядро оценивания
      Базовые проверки структуры
      Построение prompt
      Вызов LLM (inline SDK)
      Валидация JSON ответа
      Сохранение Evaluation
    Интеграции
      GitHub
        Fork/PR workflow
        Webhook → Submission
      LLM Provider
        /grade (inline)
    API
      Auth: login/register/refresh
      Submissions: create/list/get
      Evaluations: get, publish‑preliminary
    Данные
      Users/Roles
      Labs/Assignments
      Submissions/Evaluations
      Rubrics
    Security & Compliance
      JWT с exp + проверка подписи
      RBAC проверки
      Аудит входов
    Операции
      Health/логи
      Бэкапы (минимально)
```

