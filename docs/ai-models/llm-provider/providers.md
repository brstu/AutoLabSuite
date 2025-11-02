# Провайдеры и конфигурация

Поддерживаемые адаптеры и их настройки.

## OpenAI

- env: OPENAI_API_KEY
- модель: gpt-4o / gpt-4o-mini
- лимиты: tokens/min, rpm — конфиг в RateLimiter
- базовые параметры: temperature, top_p, max_tokens

## Anthropic

- env: ANTHROPIC_API_KEY
- модель: claude-3.5-sonnet / haiku
- особенности: max output tokens обязательно

## KServe (локальные модели)

- endpoint: http(s)://<host>/v1/models/{model}:predict
- аутентификация: Istio JWT/mtls (см. infra/kubeflow-kserve.md)
- формат: V1 Inference Protocol (JSON), или custom — привести к общему контракту

## Конфигурационные ключи (пример)

```yaml
llm:
  default_provider: openai
  timeout_ms: 120000
  retry:
    max_attempts: 3
    backoff_ms: 500
    jitter: true
  rate_limits:
    openai:
      rpm: 300
      tpm: 600000
    anthropic:
      rpm: 200
      tpm: 400000
```

## Квоты и стоимость

- Храните usage (prompt/response tokens) и стоимость на Evaluation/AiInvocation
- Дашборды: cost_per_evaluation, avg_tokens, retry_rate
