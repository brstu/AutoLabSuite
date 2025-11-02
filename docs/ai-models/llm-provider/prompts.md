# Промпты и схемы вывода

## Правила формирования промптов

- Шаблон: краткая инструкция → критерии → формат вывода (JSON Schema) → пример
- Короткий, детерминированный стиль; избегайте лишнего контекста
- Очистка кода от секретов/PII

## JSON Schema (эскиз результата)

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "required": ["overall", "criteria"],
  "properties": {
    "overall": { "type": "number" },
    "criteria": {
      "type": "array",
      "items": {
        "type": "object",
        "required": ["id", "value", "max"],
        "properties": {
          "id": { "type": "string" },
          "value": { "type": "number" },
          "max": { "type": "number" },
          "comments": { "type": "string" },
          "aiAssisted": { "type": "boolean" }
        }
      }
    }
  }
}
```

## Пример промпта (WT-AC-2025)

- Объясните критерии и попросите строго следовать формату.
- Добавьте короткий пример валидного JSON.

## Валидация и auto‑repair

- Схема валидируется; некорректный JSON пытаемся поправить (простой repair)
- Лимит на глубину/размер; при неуспехе — метим ошибку PARSE_ERROR
