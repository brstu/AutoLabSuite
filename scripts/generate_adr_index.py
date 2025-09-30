#!/usr/bin/env python3
"""Генерация индекса ADR из файлов в docs/decision-records.

Usage:
  python scripts/generate_adr_index.py > docs/decision-records/README.md
(Пока простой парсер заголовка и статуса.)
"""
from pathlib import Path
import re
from datetime import datetime

ADR_DIR = Path(__file__).parent.parent / 'docs' / 'decision-records'

HEADER = """# Архитектурные решения (ADR)

Этот каталог содержит архитектурные решения. Формат: ADR-XXX-slug. Каждое решение фиксирует важный выбор и его последствия.

## Индекс

| ID | Заголовок | Статус | Дата | Файл |
|----|-----------|--------|------|------|
"""

ADR_PATTERN = re.compile(r"^#\s+ADR-(\d{3}):\s+(.*)")
STATUS_PATTERN = re.compile(r"^Статус:\s*(.*)$", re.IGNORECASE)
DATE_PATTERN = re.compile(r"^Дата:\s*(.*)$", re.IGNORECASE)

rows = []
for md in sorted(ADR_DIR.glob('adr-*.md')):
    text = md.read_text(encoding='utf-8').splitlines()
    adr_id = None
    title = None
    status = 'Unknown'
    date = ''
    for line in text:
        if adr_id is None:
            m = ADR_PATTERN.match(line.strip())
            if m:
                adr_id, title = m.group(1), m.group(2).strip()
        m2 = STATUS_PATTERN.match(line.strip())
        if m2:
            status = m2.group(1).strip()
        m3 = DATE_PATTERN.match(line.strip())
        if m3:
            date = m3.group(1).strip()
        if adr_id and title and status and date:
            break
    if adr_id and title:
        rows.append(f"| {adr_id} | {title} | {status} | {date} | [{md.name}]({md.name}) |")

STATUS_SECTION = """
## Статусы

- Proposed — предложено, ждёт решения
- Accepted — принято и активно
- Rejected — отклонено (историческое)
- Superseded — заменено новым ADR
"""

PROCESS_SECTION = """
## Процесс добавления

1. Скопируйте шаблон `adr-000-template.md`
2. Присвойте следующий номер (трёхзначный)
3. Заполните разделы Контекст / Решение / Последствия
4. Создайте PR с пометкой `adr`
5. После обсуждения обновите статус
"""

NAV_SECTION = """
## Навигация

См. также `../architecture/solution-architecture.md` и связанные ADR.
"""

content = HEADER + "\n".join(rows) + "\n\n" + STATUS_SECTION + "\n" + PROCESS_SECTION + "\n" + NAV_SECTION
print(content)
