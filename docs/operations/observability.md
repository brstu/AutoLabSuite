# Наблюдаемость

## Компоненты
- Tracing: OpenTelemetry → Collector → Jaeger/Grafana Tempo
- Metrics: Prometheus (экспортеры для Python, Queue)
- Logs: Loki (json формат)

## Корреляция
- trace_id прокидывается через все слои
- submission_id добавляется в span attributes

## Ключевые метрики (предварительно)
| Метрика | Назначение |
|---------|-----------|
| grading_duration_seconds | Время полного цикла оценки |
| ai_latency_seconds | Латентность ответов LLM |
| queue_depth | Нагрузка очереди задач |
| sandbox_timeouts_total | Счётчик таймаутов песочницы |

## Алерты (эскиз)
- grading_duration_seconds p95 > 180s (5m)
- ai_latency_seconds p95 > 8s (10m)
- sandbox_timeouts_total > 3 за 10m
