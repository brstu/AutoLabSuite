# Последовательность оценивания (Sequence)

```mermaid
sequenceDiagram
  participant G as Git
  participant I as Ingestion
  participant Q as Queue
  participant R as Runner
  participant GC as GradingCore
  participant AI as AIService
  participant ST as Storage

  G->>I: Webhook push
  I->>ST: Сохранение артефактов
  I->>Q: Публикация задачи
  Q->>R: Забор задачи
  R->>ST: Логи и результаты тестов
  R->>GC: Сводка метрик
  GC->>AI: Запрос объяснений / классификации
  AI-->>GC: Ответ с объяснением
  GC->>ST: Запись Evaluation
  GC-->>I: Событие завершения
  ST-->>(Webhook): EvaluationCompleted
```

## Примечания

- Возможна параллелизация отдельных проверок
- AI вызовы могут быть батчированы
