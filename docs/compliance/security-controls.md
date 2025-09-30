# Контроли безопасности

## Слои
- Сетевой (firewall, ограничение egress)
- Приложение (валидация входа, rate limit)
- Данные (шифрование, маскирование)

## Контроли (черновик)
| Контроль | Тип | Статус |
|----------|-----|--------|
| RBAC | Access | Planned |
| Audit Trail | Logging | Draft |
| Encryption at Rest | Data | Planned |
| Rate Limiting | App | Planned |
| Secrets Vault | Secrets | Planned |
| Sandbox Isolation | Runtime | Draft |

## Политика секретов
- Хранение вне git
- Ротация ключей по расписанию

## Управление уязвимостями
- Периодический SCA (Dependabot / pip-audit)
- Оценка критичности CVSS
