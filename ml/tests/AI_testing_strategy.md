# Стратегия тестирования AI-модулей (резюме и варианты реализации)

## Цель

Обеспечить набор тестов и утилит для верификации поведения AI-модулей (оценивание, аннотация, классификация), которые можно запускать локально, в CI и как часть интеграций.

## Контракты и форматы

- Вход (payload): JSON-объект, содержащий минимум:
  - `student_id`: string
  - `submission`: string | structured (code, text, artifacts)
  - `language`: string (например, `python`, `en`)
  - `metadata`: { `max_score`: int, ... }

- Ответ (expected output): JSON-объект, содержащий:
  - `score`: number (0..max_score)
  - `comments`: string | array
  - `details`: optional structured diagnostics (line-level for code)
  - `version`: model identifier / model hash

- Ошибки / режимы отказа:
  - `validation_error`: вход не соответствует схеме — возвращать 4xx
  - `server_error`: модель упала / таймаут — возвращать 5xx

## Варианты реализации тестирования

1. Unit (mock) tests — быстрые, детерминированные

  - Tools: `pytest` с `mock`/`requests-mock`.
  - Notes: `pytest` fixtures для подстановки payloads; цели — проверка сериализации, вызовов HTTP, обработка ошибок.

1. Integration tests — реальный endpoint

  - Tools: маркировать `@pytest.mark.integration`.
  - Notes: требуют переменных окружения `MODEL_ENDPOINT`, `MODEL_API_KEY`. Запускать по запросу или в выделенной CI job.

1. End-to-end / Contract tests

  - Tools: WireMock / VCR / Betamax для записи/воспроизведения HTTP.
  - Notes: проверять соответствие контракту (JSON Schema).

1. Load / Stress tests

  - Tools: `locust` или `k6` для нагрузочного тестирования модели/враппера.
  - Notes: проверять стабильность latency и ошибки при пиковых нагрузках.

1. Fuzzing и adversarial tests

  - Tools: генерация шумовых и граничных входов (длинные тексты, бинарные вложения).
  - Notes: использовать `Hypothesis` для property-based testing.

1. CI Integration

  - Guidance: разделять на быстрые unit tests (on PR) и медленные integration/load (nightly). Секреты передавать через CI secrets (не хранить в коде).

## Практические артефакты (примеры)

- `ml/tests/send_task.py` — утилита CLI для отправки payload на endpoint (mock/real)
- `ml/tests/test_model_integration.py` — `pytest` примеры (mocked и `@pytest.mark.integration`)
- `ml/tests/sample_payloads.json` — набор тестовых payloads
- `ml/tests/requirements.txt` — зависимости

## Контрольные случаи (edge cases)

- пустая или отсутствующая `submission`
- превышение `max_score` в ответе
- длительный ответ / таймаут
- модели возвращают неожиданные поля
- неверный JSON (парсинг на стороне сервера)

## Как запускать локально (рекомендации)

1. Создать virtualenv:

```powershell
python -m venv .venv
```

1. Активировать (PowerShell):

```powershell
.venv\Scripts\Activate.ps1
```

1. Установить зависимости:

```powershell
pip install -r ml/tests/requirements.txt
```

1. Запустить тесты (mock):

```powershell
pytest -q ml/tests -k "not integration"
```

1. Для интеграционных тестов: задать `MODEL_ENDPOINT` и запустить:

```powershell
pytest -m integration
```

## CI pipeline suggestions

- job: `test:fast` — runs unit tests
- job: `test:integration` — runs only if secrets are available or on manual trigger
- job: `load` — nightly schedule

## Next steps

1. Добавить JSON Schema для payload/response и валидаторы в тесты.
2. Добавить Hypothesis-based property tests для генерации граничных payloads.
3. Подключить contract testing (WireMock/VCR) для стабильного E2E тестирования.
4. Встроить тесты в CI с разделением по тегам (`unit`, `integration`, `load`).
# Стратегия тестирования AI-модулей (резюме и варианты реализации)

## Цель

Обеспечить набор тестов и утилит для верификации поведения AI-модулей (оценивание, аннотация, классификация), которые можно запускать локально, в CI и как часть интеграций.

## Контракты и форматы

- Вход (payload): JSON-объект, содержащий минимум:
  - `student_id`: string
  - `submission`: string | structured (code, text, artifacts)
  - `language`: string (например, `python`, `en`)
  - `metadata`: { `max_score`: int, ... }

- Ответ (expected output): JSON-объект, содержащий:
  - `score`: number (0..max_score)
  - `comments`: string | array
  - `details`: optional structured diagnostics (line-level for code)
  - `version`: model identifier / model hash

- Ошибки / режимы отказа:
  - `validation_error`: вход не соответствует схеме — возвращать 4xx
  - `server_error`: модель упала / таймаут — возвращать 5xx

## Варианты реализации тестирования

1. Unit (mock) tests — быстрые, детерминированные

Tools: `pytest` с `mock`/`requests-mock`.

Notes: `pytest` fixtures для подстановки payloads; цели — проверка сериализации, вызовов HTTP, обработка ошибок.

1. Integration tests — реальный endpoint

Tools: маркировать `@pytest.mark.integration`.

Notes: требуют переменных окружения `MODEL_ENDPOINT`, `MODEL_API_KEY`. Запускать по запросу или в выделенной CI job.

1. End-to-end / Contract tests

Tools: WireMock / VCR / Betamax для записи/воспроизведения HTTP.

Notes: проверять соответствие контракту (JSON Schema).

1. Load / Stress tests

Tools: `locust` или `k6` для нагрузочного тестирования модели/враппера.

Notes: проверять стабильность latency и ошибки при пиковых нагрузках.

1. Fuzzing и adversarial tests

Tools: генерация шумовых и граничных входов (длинные тексты, бинарные вложения).

Notes: использовать `Hypothesis` для property-based testing.

1. CI Integration

Guidance: разделять на быстрые unit tests (on PR) и медленные integration/load (nightly). Секреты передавать через CI secrets (не хранить в коде).

## Практические артефакты (примеры)

- `ml/tests/send_task.py` — утилита CLI для отправки payload на endpoint (mock/real)
- `ml/tests/test_model_integration.py` — `pytest` примеры (mocked и `@pytest.mark.integration`)
- `ml/tests/sample_payloads.json` — набор тестовых payloads
- `ml/tests/requirements.txt` — зависимости

## Контрольные случаи (edge cases)

- пустая или отсутствующая `submission`
- превышение `max_score` в ответе
- длительный ответ / таймаут
- модели возвращают неожиданные поля
- неверный JSON (парсинг на стороне сервера)

## Как запускать локально (рекомендации)

1. Создать virtualenv:

```powershell
python -m venv .venv
```

1. Активировать (PowerShell):

```powershell
.venv\Scripts\Activate.ps1
```

1. Установить зависимости:

```powershell
pip install -r ml/tests/requirements.txt
```

1. Запустить тесты (mock):

```powershell
pytest -q ml/tests -k "not integration"
```

1. Для интеграционных тестов: задать `MODEL_ENDPOINT` и запустить:

```powershell
pytest -m integration
```

## CI pipeline suggestions

- job: `test:fast` — runs unit tests
- job: `test:integration` — runs only if secrets are available or on manual trigger
- job: `load` — nightly schedule

## Next steps

1. Добавить JSON Schema для payload/response и валидаторы в тесты.
2. Добавить Hypothesis-based property tests для генерации граничных payloads.
3. Подключить contract testing (WireMock/VCR) для стабильного E2E тестирования.
4. Встроить тесты в CI с разделением по тегам (`unit`, `integration`, `load`).
# Стратегия тестирования AI-модулей (резюме и варианты реализации)

## Цель

Обеспечить набор тестов и утилит для верификации поведения AI-модулей (оценивание, аннотация, классификация), которые можно запускать локально, в CI и как часть интеграций.

## Контракты и форматы

- Вход (payload): JSON-объект, содержащий минимум:
  - `student_id`: string
  - `submission`: string | structured (code, text, artifacts)
  - `language`: string (например, `python`, `en`)
  - `metadata`: { `max_score`: int, ... }

- Ответ (expected output): JSON-объект, содержащий:
  - `score`: number (0..max_score)
  - `comments`: string | array
  - `details`: optional structured diagnostics (line-level for code)
  - `version`: model identifier / model hash

- Ошибки / режимы отказа:
  - `validation_error`: вход не соответствует схеме — возвращать 4xx
  - `server_error`: модель упала / таймаут — возвращать 5xx
  - `partial_result`: модель вернула частичный ответ с флагом `partial: true`

## Варианты реализации тестирования

1. Unit (mock) tests — быстрые, детерминированные

Tools: `pytest` с `mock`/`requests-mock`.

Notes: `pytest` fixtures для подстановки payloads; цели — проверка сериализации, вызовов HTTP, обработка ошибок.

1. Integration tests — реальный endpoint

Tools: маркировать `@pytest.mark.integration`.

Notes: требуют переменных окружения `MODEL_ENDPOINT`, `MODEL_API_KEY`. Запускать по запросу или в выделенной CI job.

1. End-to-end / Contract tests

Tools: WireMock / VCR / Betamax для записи/воспроизведения HTTP.

Notes: проверять соответствие контракту (JSON Schema).

1. Load / Stress tests

Tools: `locust` или `k6` для нагрузочного тестирования модели/враппера.

Notes: проверять стабильность latency и ошибки при пиковых нагрузках.

1. Fuzzing и adversarial tests

Tools: генерация шумовых и граничных входов (длинные тексты, бинарные вложения).

Notes: использовать `Hypothesis` для property-based testing.

1. CI Integration

Guidance: разделять на быстрые unit tests (on PR) и медленные integration/load (nightly). Секреты передавать через CI secrets (не хранить в коде).

## Практические артефакты (примеры)

- `ml/tests/send_task.py` — утилита CLI для отправки payload на endpoint (mock/real)
- `ml/tests/test_model_integration.py` — `pytest` примеры (mocked и `@pytest.mark.integration`)
- `ml/tests/sample_payloads.json` — набор тестовых payloads
- `ml/tests/requirements.txt` — зависимости

## Контрольные случаи (edge cases)

- пустая или отсутствующая `submission`
- превышение `max_score` в ответе
- длительный ответ / таймаут
- модели возвращают неожиданные поля
- неверный JSON (парсинг на стороне сервера)

## Как запускать локально (рекомендации)

1. Создать virtualenv:

```powershell
python -m venv .venv
```

1. Активировать (PowerShell):

```powershell
.venv\Scripts\Activate.ps1
```

1. Установить зависимости:

```powershell
pip install -r ml/tests/requirements.txt
```

1. Запустить тесты (mock):

```powershell
pytest -q ml/tests -k "not integration"
```

1. Для интеграционных тестов: задать `MODEL_ENDPOINT` и запустить:

```powershell
pytest -m integration
```

## CI pipeline suggestions

- job: `test:fast` — runs unit tests
- job: `test:integration` — runs only if secrets are available or on manual trigger
- job: `load` — nightly schedule

## Next steps

1. Добавить JSON Schema для payload/response и валидаторы в тесты.
2. Добавить Hypothesis-based property tests для генерации граничных payloads.
3. Подключить contract testing (WireMock/VCR) для стабильного E2E тестирования.
4. Встроить тесты в CI с разделением по тегам (`unit`, `integration`, `load`).
- модели возвращают неожиданные поля
- неверный JSON (парсинг на стороне сервера)

## Как запускать локально (рекомендации)

1. Создать virtualenv:

```powershell
python -m venv .venv
```

1. Активировать (PowerShell):

```powershell
.venv\Scripts\Activate.ps1
```

1. Установить зависимости:

```powershell
pip install -r ml/tests/requirements.txt
```

1. Запустить тесты (mock):

```powershell
pytest -q ml/tests -k "not integration"
```

1. Для интеграционных тестов: задать `MODEL_ENDPOINT` и запустить:

```powershell
pytest -m integration
```

## CI pipeline suggestions

- job: `test:fast` — runs unit tests
- job: `test:integration` — runs only if secrets are available or on manual trigger
- job: `load` — nightly schedule

## Next steps

1. Добавить JSON Schema для payload/response и валидаторы в тесты.
2. Добавить Hypothesis-based property tests для генерации граничных payloads.
3. Подключить contract testing (WireMock/VCR) для стабильного E2E тестирования.
4. Встроить тесты в CI с разделением по тегам (`unit`, `integration`, `load`).

                            ## Контракты и форматы

                            - Вход (payload): JSON-объект, содержащий минимум:
                              - `student_id`: string
                              - `submission`: string | structured (code, text, artifacts)
                              - `language`: string (например, `python`, `en`)
                              - `metadata`: { `max_score`: int, ... }

                            - Ответ (expected output): JSON-объект, содержащий:
                              - `score`: number (0..max_score)
                              - `comments`: string | array
                              - `details`: optional structured diagnostics (line-level for code)
                              - `version`: model identifier / model hash

                            - Ошибки / режимы отказа:
                              - `validation_error`: вход не соответствует схеме — возвращать 4xx
                              - `server_error`: модель упала / таймаут — возвращать 5xx
                              - `partial_result`: модель вернула частичный ответ с флагом `partial: true`

                            ## Варианты реализации тестирования

                            1. Unit (mock) tests — быстрые, детерминированные
                                - `pytest` с `mock`/`requests-mock`
                                - `pytest` fixtures для подстановки payloads
                                - цели: проверка сериализации, вызовов HTTP, обработка ошибок

                            1. Integration tests — реальный endpoint
                                - маркировать `@pytest.mark.integration`
                                - требуют переменных окружения: `MODEL_ENDPOINT`, `MODEL_API_KEY`
                                - запускать по запросу или в выделенной CI job

                            1. End-to-end / Contract tests
                                - использовать WireMock / VCR / Betamax для записи/воспроизведения HTTP
                                - проверять соответствие контракту (JSON Schema)

                            1. Load / Stress tests
                                - `locust` или `k6` для нагрузочного тестирования модели/враппера
                                - проверять стабильность latency, ошибки при пиковых нагрузках

                            1. Fuzzing и adversarial tests
                                - генерировать шумовые и граничные входы (длинные тексты, бинарные вложения)
                                - использовать `Hypothesis` для property-based testing

                            1. CI Integration
                                - Разделять на быстрые unit tests (on PR) и медленные integration/load (nightly)
                                - Секреты передавать через CI secrets (не хранить в коде)

                            ## Практические артефакты (примеры)

                            - `ml/tests/send_task.py` — утилита CLI для отправки payload на endpoint (mock/real)
                            - `ml/tests/test_model_integration.py` — `pytest` примеры (mocked и `@pytest.mark.integration`)
                            - `ml/tests/sample_payloads.json` — набор тестовых payloads
                            - `ml/tests/requirements.txt` — зависимости

                            ## Контрольные случаи (edge cases)

                            - пустая или отсутствующая `submission`
                            - превышение `max_score` в ответе
                            - длительный ответ / таймаут
                            - модели возвращают неожиданные поля
                            - неверный JSON (парсинг на стороне сервера)

                            ## Как запускать локально (рекомендации)

                            1. Создать virtualenv:

                            ```powershell
                            python -m venv .venv
                            ```

                            1. Активировать (PowerShell):

                            ```powershell
                            .venv\Scripts\Activate.ps1
                            ```

                            1. Установить зависимости:

                            ```powershell
                            pip install -r ml/tests/requirements.txt
                            ```

                            1. Запустить тесты (mock):

                            ```powershell
                            pytest -q ml/tests -k "not integration"
                            ```

                            1. Для интеграционных тестов: задать `MODEL_ENDPOINT` и запустить:

                            ```powershell
                            pytest -m integration
                            ```

                            ## CI pipeline suggestions

                            - job: `test:fast` — runs unit tests
                            - job: `test:integration` — runs only if secrets are available or on manual trigger
                            - job: `load` — nightly schedule

                            ## Next steps

                            1. Добавить JSON Schema для payload/response и валидаторы в тесты.
                            2. Добавить Hypothesis-based property tests для генерации граничных payloads.
                            3. Подключить contract testing (WireMock/VCR) для стабильного E2E тестирования.
                            4. Встроить тесты в CI с разделением по тегам (`unit`, `integration`, `load`).
