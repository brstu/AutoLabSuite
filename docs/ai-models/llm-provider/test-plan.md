# План тестирования LLM Provider

## Функциональные тесты

- Infer (echo) — возврат строки/JSON
- Grade — соблюдение схемы, корректная агрегация
- Ошибки — провоцирование 4xx/5xx, корректные коды

## Нагрузочные тесты

- RPS профили: 1, 5, 10, 20 на 1–5 мин
- Пики и всплески (burst)
- Метрики: p50/p95 latency, error rate, retry rate

## Кросс‑провайдерные тесты

- OpenAI/Anthropic/KServe — одинаковые входы, сравнение структуры выхода
- Отчёт о несовместимостях (diff JSON Schema)

## Стоимость

- Замер usage токенов по выборке задач
- Расчёт cost_per_evaluation и сравнение провайдеров

## Мониторинг

- Дашборды: latency, errors, usage, cost
- Алерты: RATE_LIMIT, PROVIDER_UNAVAILABLE, TIMEOUT
