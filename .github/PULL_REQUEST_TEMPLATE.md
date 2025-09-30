## Context

Связанные задачи / ссылки: (Fixes #ID, Closes #ID, Ref #ID)

## Changes

- ...

## Type

- [ ] feat
- [ ] fix
- [ ] docs
- [ ] refactor
- [ ] chore
- [ ] test
- [ ] security
- [ ] perf

## Readiness / Done Checklist (см. [docs/processes/dod-dor.md](/docs/processes/dod-dor.md))

Отмечайте только применимое. Для невыполнимых / снятых пунктов добавьте блок Waived Criteria ниже.

### DoD Core (D*)

- [ ] D1 Acceptance criteria закрыты
- [ ] D2 Code Review (минимум 2 / либо пометка low-risk)
- [ ] D3 CI зелёный
- [ ] D5 Тесты добавлены / обновлены
- [ ] D8 Docs / схемы / OpenAPI обновлены (если нужно)
- [ ] D12 Нет неоформленных TODO
- [ ] D14 Ручное тестирование критичных путей (если UI / критичное изменение)

### Quality Gates (Q*)

- [ ] Q1 Unit/Component tests pass
- [ ] Q2 Lint чист
- [ ] Q3 SCA / зависимости без критичных уязвимостей
- [ ] Q4 SAST (Semgrep/CodeQL) без новых high/critical

### Release / Ops (при необходимости)

- [ ] L1 CHANGELOG обновлён (если пользовательская функциональность)
- [ ] L2 SemVer bump определён (если релиз-кандидат)
- [ ] Flags/migrations описаны

### Waived Criteria

Если какие-то коды D*/Q*/L* временно пропущены, перечислите их здесь с обоснованием.

```text
Waived: (пример) D14 — нет UI воздействия
```

## Risks / Rollback

- Риски: ...
- План отката: revert / отключить feature flag / ...

## Screenshots / Logs (если UI или ошибка)

(при необходимости)

## Review Checklist (для ревьюеров)

- [ ] Архитектурные границы соблюдены
- [ ] Нейминг и читаемость адекватны
- [ ] Изменения логически связаны (нет "грязного" scope)
- [ ] Обработка ошибок корректна
- [ ] Нет утечек секретов/PII
- [ ] Потенциальные узкие места производительности рассмотрены
- [ ] Documentation / OpenAPI действительно обновлены (если отмечено)

## Post-merge

- [ ] Follow-up issue создан (если есть ужатый scope)
- [ ] Demo/окружение обновлено (если требуется)
- [ ] Наблюдаемость/метрики добавлены (если новая функциональность)
