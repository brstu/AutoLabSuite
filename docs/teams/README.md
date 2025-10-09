# Команды AutoLabSuite

Ниже описана организационная структура из 4 кросс-функциональных команд по 3 человека.
Каждая команда работает в 1-недельных итерациях (спринтах).
Каждый участник исполняет роль тим-лида (Iteration Lead) по 2 итерации подряд в течение ротационного цикла.

## Список команд

| Команда | Code Prefix | Чат / Канал | Название (ENG) | Зона ответственности (высокоуровнево) |
|---------|-------------|-------------|----------------|----------------------------------------|
| NeonFox | FE | #team-neonfox | Neon Fox Squad | Frontend: UI, UX, accessibility, интеграция с API |
| ByteForge | BE | #team-byteforge | Byte Forge Guild | Backend ядро: API, бизнес-логика, рубрики, тест раннеры |
| CloudForge | DEVOPS | #team-cloudforge | Cloud Forge Crew | Инфраструктура: CI/CD, IaC, observability, security |
| VectorPulse | ML | #team-vectorpulse | Vector Pulse Lab | ML & AI: модели эмбеддингов, классификаторы, генерация фидбека |
| CoreOrbit | CORE | #team-coreorbit | Core Orbit Council | Сквозное руководство: приоритизация, продуктовый |
| Mobile | MOB | #team-mobile | Mobile Squad | Мобильное приложение: мобильный UI, нативные интеграции |
|           |      |                |                    | бэклог, риски, согласование архитектуры |

### Состав команд (реестр)

Ниже — удобная таблица для быстрого поиска участников и их контактов в GitHub.

| Команда | Группа | Курс | ФИО | GitHub аккаунт | Discord аккаунт | GitHub team |
|---------|--------|------|-----|----------------|------------------|-------------|
| NeonFox | FE | 4 курс | Горкавчук Никита | @Exage | flo0ty | github-AutoLabSuite-NeonFox |
| NeonFox | FE | 4 курс | Смердина Анастасия | @KotyaLapka | kotiajepka | github-AutoLabSuite-NeonFox |
| ByteForge | BE | 4 курс | Карпеш Никита | @Frosyka | frosenkal | github-AutoLabSuite-ByteForge |
| ByteForge | BE | 4 курс | Булавский Андрей | @andrei1910bl | nazariy_adamovich | github-AutoLabSuite-ByteForge |
| ByteForge | BE | 4 курс | Игнаткевич Кирилл | @pyrokekw | iamsxul | github-AutoLabSuite-ByteForge |
| ByteForge | BE | 4 курс | Кухарчук Илья | @IlyaKukharchuk | chipsoed_ | github-AutoLabSuite-ByteForge |
| CloudForge | DEVOPS | 2 курс | Сухобай Дмитрий Александрович   | @DERT1SE  | dertise | github-AutoLabSuite-CloudForge |
| CloudForge | DEVOPS | 2 курс |  Хотько Иван Александрович   |  @empty513   | empty513  | github-AutoLabSuite-CloudForge |
| CloudForge | DEVOPS | 2 курс |  Строчук Дмитрий Александрович     | @DmitryUnix    | yda4ya_   | github-AutoLabSuite-CloudForge |
| VectorPulse | ML | 4 курс | Романюк Алексей | @Gomziakoff | gomziakov | github-AutoLabSuite-VectorPulse |
| VectorPulse | ML | 4 курс | Вышинский Артем | @arciomwww | arciomwww | github-AutoLabSuite-VectorPulse |
| VectorPulse | ML | 4 курс | Бусень Артем | @hudshiy1 | artemkadaun | github-AutoLabSuite-VectorPulse |
| Mobile | MOB | |  |  |  | github-AutoLabSuite-Mobile |
| CoreOrbit | CORE |  |  | |  | github-AutoLabSuite-CoreOrbit |

Если вы хотите добавить или обновить запись — пожалуйста, откройте pull request с правкой этой таблицы.

## Обоснование нейминга

## Структура ролей

## Использование префиксов

Префиксы применяются в:
- Названия веток: `fe/feature-auth-ui`, `be/fix-grading-rule`, `devops/terraform-s3`, `ml/embedding-exp-v1`, `mobile/feature-login`
- Pull Request заголовках: `[FE] Add rubric editor layout`
- Issue заголовках: `[ML] Эксперимент с классификатором ошибок`
- Коммитах (опционально): `FE: init table component`

Рекомендуется использовать верхний регистр в PR/Issue для визуального сканирования.

- Iteration Lead (IL) — планирование спринта, модерация стендапов, отслеживание блокеров, handoff заметки.
- Domain Owner (DO) — глубинная экспертиза по определённой подсистеме; не обязательно совпадает с IL.
- Reviewer — обязательный код-ревьюер на критичных участках (см. CODEOWNERS).
- Core Orbit Council — группа руководителей (product + tech) участвующая во всех ключевых церемониях;
  гарантирует целостность roadmap, контроль рисков, согласование стандартов качества и безопасности.

## Пример цикла ротации (по неделям)

| Неделя | NeonFox (FE) | ByteForge (BE) | CloudForge (DevOps) | VectorPulse (ML) |
|--------|--------------|----------------|--------------------|------------------|
| 1      | A (IL)       | D (IL)         | G (IL)             | J (IL)           |
| 2      | B (IL)       | E (IL)         | H (IL)             | K (IL)           |
| 3      | C (IL)       | F (IL)         | I (IL)             | L (IL)           |
| 4      | A (IL)       | D (IL)         | G (IL)             | J (IL)           |
| 5      | B (IL)       | E (IL)         | H (IL)             | K (IL)           |
| 6      | C (IL)       | F (IL)         | I (IL)             | L (IL)           |

(Каждый участник дважды проходит роль IL за 6-недельный цикл.)

## Мини-гайд по чатам

- Общий инженерный канал: `#eng-general`
- Экстренные инциденты (SEV): `#incident-warroom`
- Async design review: `#design-review`
- ML эксперименты лог: `#ml-lab-log`

## Коммуникация в Discord

Для коммуникации по проекту используется Discord. Ниже — рекомендуемая структура каналов, роли и практики общения.

Рекомендуемые каналы (пример):

- Категория: Team channels
  - `#team-neonfox` — канал команды NeonFox (FE)
  - `#team-byteforge` — канал команды ByteForge (BE)
  - `#team-cloudforge` — канал команды CloudForge (DevOps)
  - `#team-vectorpulse` — канал команды VectorPulse (ML)
  - `#team-mobile` — канал команды Mobile (MOB)
  - `#team-coreorbit` — канал Core Orbit (coordination)
- Категории: Cross-team / Project
  - `#eng-general` — общие инженерные обсуждения
  - `#design-review` — асинхронные обзоры дизайна и архитектуры
  - `#ml-lab-log` — лог экспериментов ML
  - `#incident-warroom` — экстренные инциденты (SEV)
  - `#announcements` — объявления от Core Orbit / Product

Роли в Discord (рекомендуется использовать роли сервера / упоминания):

- `@IterationLead` — модератор текущей итерации, фасилитатор стендапов и планирования
- `@DomainOwner` — эксперт по области, принимает технические решения
- `@Reviewer` — ответственный за код-ревью на критичных PR
- `@CoreOrbit` — продукт/тех лиды для принятия ключевых решений
- `@Mobile` — участники мобильной команды (MOB); используйте для оповещений и обсуждений, связанных с мобильным приложением
- `@MobileLead` — модератор мобильной команды, фасилитатор стендапов и релизов

Практики и правила:

- Язык коммуникации: русский в повседневном общении, английский — для публичных артефактов и внешних репозиториев.
- Thread-first: при длительном обсуждении создавайте треды или отдельные каналы, чтобы не загромождать общий поток.
- Мнения и решения: ключевые решения фиксируйте в issue/PR и в `#announcements`.
- Эскалация: для инцидентов используйте `#incident-warroom`, и помечайте `@CoreOrbit`/on-call команду.
- Уважение и профессионализм: избегайте личных нападок, сарказма и публичного унижения.
- Чувствительная информация (PII, секреты) — не публикуется в чатах; используйте защищённые хранилища.

## Правила общения (кодекс поведения в чатах)

1. Будьте вежливы и конструктивны. Критика — по делу, без перехода на личности.
2. Ответственность за канал: каждая команда следит за своим каналом и поддерживает порядок.
3. Не соревнуйтесь в уведомлениях: упоминайте @here/@everyone только по важным причинам.
4. Используйте реакции для быстрых подтверждений (✅, 👀, 👍).
5. Для обсуждений, требующих решения, создавайте issue/PR с ссылкой на тред.

## Формат митингов

Ниже — рекомендуемые типы митингов, частота и шаблоны повестки/протокола. Шаблоны хранятся в [docs/teams/templates/](/docs/teams/templates/).

- Ежедневный стендап (Stand-up)
  - Частота: ежедневно, 10–15 минут
  - Формат: быстрые обновления (что сделано, что планируется, блокеры)
  - Модерация: `@IterationLead`

- Планирование спринта (Sprint Planning)
  - Частота: в начале каждой итерации (1 неделя)
  - Длительность: 45–60 минут
  - Цель: сформировать цель спринта и распределить задачи
  - Шаблон повестки: [docs/teams/templates/agenda.md](/docs/teams/templates/agenda.md)

- Демонстрация/Review (Sprint Review / Demo)
  - Частота: в конце итерации
  - Длительность: 30–45 минут
  - Цель: показать результат, собрать фидбэк от product и стейкхолдеров

- Ретроспектива (Retrospective)
  - Частота: в конце итерации
  - Длительность: 30–45 минут
  - Цель: обсудить что прошло хорошо, что улучшить, согласовать action items

- Design Review
  - Частота: по мере необходимости / bi-weekly для крупных тем
  - Формат: заранее рассылается контекст; решение фиксируется в PR или ADR

- Handoff / On-call Handover
  - Частота: по сменам on-call или при смене IL
  - Формат: краткий статус инцидентов, открытые action items, запущенные эксперименты

Для каждого митинга используйте шаблон повестки и заполняйте протокол (meeting notes) во время/после встречи.

## Шаблоны

- [docs/teams/templates/agenda.md](/docs/teams/templates/agenda.md) — шаблон повестки встреч
- [docs/teams/templates/meeting-notes.md](/docs/teams/templates/meeting-notes.md) — шаблон протокола встречи
- [docs/teams/templates/action-item-template.md](/docs/teams/templates/action-item-template.md) — таблица для action items

## Где хранить результаты

- Протокол встреч и action items: привязывайте к соответствующим issue или храните ссылку в странице встречи.
- Важные решения: записывайте в ADRs ([docs/decision-records/](/docs/decision-records/)), архитектурные выводы — в [docs/architecture/](/docs/architecture/).

## Ссылки

См. документы:

- `../processes/team-rotation.md`
- `../processes/scrum-intro.md`
- `../../.github/CODEOWNERS` (будет добавлен)

## Nomenclature в задачах

- Epic: `[EPIC][FE] Редактор рубрик`
- Story: `[FE] CRUD рубрики (v1)`
- Task: `[BE] Endpoint /rubrics/{id}/versions`
- Spike: `[ML][SPIKE] Оценка качества эмбеддингов`
- Bug: `[DEVOPS][BUG] Ломается apply terraform backend-s3`

## Дополнительно

См. документы:

- `../processes/team-rotation.md`
- `../processes/scrum-intro.md`
- `../../.github/CODEOWNERS` (будет добавлен)
