# Контракты: порт и форматы

Этот документ фиксирует контракт между Grading Core (или иными потребителями) и AI Gateway.

## Порт (абстракция)

```text
interface ModelServingPort {
  infer(modelKey: string, payload: InferRequest, options?): InferResponse
  grade(payload: GradeRequest): GradeResponse
}
```

## REST контракты (высокоуровнево)

- POST /models/{modelKey}:infer — прямой инференс
- POST /grade — нормализованная оценка работы (rubric + контекст)

## Схемы запросов/ответов (эскиз)

### InferRequest

```json
{
  "input": "string | array | object",
  "parameters": {
    "max_tokens": 512,
    "temperature": 0.2,
    "top_p": 0.95
  },
  "attachments": [
    { "name": "file1.txt", "content": "...", "mime": "text/plain" }
  ]
}
```

### InferResponse

```json
{
  "output": "string | object",
  "usage": { "prompt_tokens": 123, "completion_tokens": 456 },
  "provider": "openai|anthropic|kserve",
  "model": "gpt-4o|...",
  "latency_ms": 2100,
  "trace_id": "..."
}
```

### GradeRequest

```json
{
  "rubric": { "version": "1.0.0", "criteria": [ { "id": "c1", "weight": 0.4 }, { "id": "c2", "weight": 0.6 } ] },
  "context": {
    "files": [ { "path": "required_dir/index.html", "content": "..." } ],
    "metadata": { "assignmentId": "...", "studentId": "..." }
  },
  "prompt_template": "...",
  "output_schema": "criterion[]/overall JSON schema"
}
```

### GradeResponse

```json
{
  "overall": 8.5,
  "criteria": [
    { "id": "c1", "value": 8, "max": 10, "comments": "...", "aiAssisted": true },
    { "id": "c2", "value": 9, "max": 10 }
  ],
  "model_snapshot": "gpt-4o@2025-10-01",
  "audit": { "prompt_hash": "...", "raw": { "prompt": "...", "response": "..." } }
}
```

## Безопасность

- Ограничить входные размеры (переменные лимиты на провайдера)
- Обфускция/удаление PII до пересылки в внешние API
- Подпись/шифрование секретов, хранение токенов вне лога
