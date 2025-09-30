# Модель ошибок

## Формат ответа ошибки

```json
{
  "error": {
    "code": "submission.not_found",
    "message": "Submission not found",
    "details": {"submission_id": "subm-123"},
    "trace_id": "abc-123",
    "timestamp": "2025-09-24T10:10:00Z"
  }
}
```

## Поля

| Поле | Назначение |
|------|------------|
| code | Структурный код (namespace + slug) |
| message | Локализуемое человекочитаемое сообщение |
| details | Доп. контекст для клиента |
| trace_id | Корреляция для трассировки |
| timestamp | Время генерации |

## Коды ошибок (примеры)

- submission.not_found
- submission.invalid_state
- rubric.invalid
- auth.unauthorized
- auth.forbidden
- rate.limit_exceeded

## Логика HTTP статус → code

| HTTP | Категория |
|------|-----------|
| 400 | validation.* |
| 401 | auth.unauthorized |
| 403 | auth.forbidden |
| 404 | *.not_found |
| 409 | *.conflict |
| 422 | validation.unprocessable |
| 429 | rate.limit_exceeded |
| 500 | internal.error |

## Идемпотентность и ошибки

- Клиент может повторить 5xx / network timeout
- Не повторять 4xx без изменения запроса
