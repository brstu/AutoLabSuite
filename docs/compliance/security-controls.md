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

## Аутентификация

- Локальный вход по email/паролю, пароли хэшируются (Argon2id предпочтительно)
- JWT Bearer для API: обязательные клеймы (sub, exp, iat, jti), проверка подписи и exp
- Короткий TTL access_token (например, 15 минут); refresh_token с ротацией при каждом обновлении
- Отзыв refresh_token при logout и смене пароля
- 2FA не используется

## Политика секретов

- Хранение вне git
- Ротация ключей по расписанию

## Управление уязвимостями

- Периодический SCA (Dependabot / pip-audit)
- Оценка критичности CVSS
