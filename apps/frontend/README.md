# AutoLabSuite Frontend

Папка для клиентского приложения (Web UI).

## Предполагаемый стек

- TypeScript / React (Next.js или Vite)
- UI библиотека (MUI / Mantine / Tailwind)
- State Management: Zustand / Redux Toolkit
- Auth: OIDC/OAuth2 (через backend gateway)

## Основные зоны

- Интерфейс преподавателя (курсы, задания, оценки)
- Интерфейс студента (прогресс, фидбек, попытки)
- Панель модератора/ревьюера
- Настройки рубрик и версий

## Стандарты

- Архитектура папок будет описана позже: [docs/architecture/](/docs/architecture/) (Frontend раздел)
- Линтинг и форматирование (ESLint + Prettier) — TBD
- Тестирование: Vitest / Jest + Playwright (e2e)

## Ответственность команды

Текущие владельцы — команда FE (см. [docs/teams/README.md](/docs/teams/README.md)).
