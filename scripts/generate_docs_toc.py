#!/usr/bin/env python3
"""Generate documentation TOC (docs/README.md) scanning first-level subdirs.

Rules:
- Include known sections in predefined order mapping.
- For each directory, list selected files (README + key markdowns) if match whitelist patterns.
- Preserve manual intro at top if present (lines until '## Оглавление' or '# Документация').
"""
from __future__ import annotations
import pathlib
import re
from typing import List, Dict

ROOT = pathlib.Path(__file__).resolve().parent.parent / 'docs'

ORDER = [
    'overview', 'architecture', 'grading', 'ai-models', 'api', 'operations',
    'compliance', 'ui-ux', 'templates', 'examples'
]

SECTION_TITLES = {
    'overview': 'Обзор',
    'architecture': 'Архитектура',
    'grading': 'Оценивание',
    'ai-models': 'AI модели',
    'api': 'API',
    'operations': 'Operations',
    'compliance': 'Compliance',
    'ui-ux': 'UI / UX',
    'templates': 'Шаблоны и процессы',
    'examples': 'Примеры'
}

WHITELIST = {
    'overview': ['README.md', 'roadmap/milestones.md'],
}

def collect_section(dir_name: str) -> List[str]:
    path = ROOT / dir_name
    if not path.exists() or not path.is_dir():
        return []
    files: List[pathlib.Path] = []
    # Basic heuristic: README plus immediate .md files (no deep recursion except decision-records special)
    for p in sorted(path.glob('*.md')):
        files.append(p)
    # Some nested known paths
    if dir_name == 'architecture':
        files.extend(sorted((path / 'diagrams').glob('*.md')))
    if dir_name == 'grading':
        files.extend(sorted((path / 'rubrics').glob('*.md')))
    rels = [str(p.relative_to(ROOT)).replace('\\', '/') for p in files]
    return rels


def build_toc() -> str:
    lines: List[str] = []
    lines.append('# Документация LabAutomation')
    lines.append('')
    lines.append('Это индекс основных разделов. Файлы написаны на русском языке.')
    lines.append('')
    lines.append('## Оглавление')
    lines.append('')
    for section in ORDER:
        entries = collect_section(section)
        if not entries:
            continue
        title = SECTION_TITLES.get(section, section)
        lines.append(f'### {title}')
        lines.append('')
        for rel in entries:
            lines.append(f'- `{rel}`')
        lines.append('')
    return '\n'.join(lines).rstrip() + '\n'


def main():
    toc = build_toc()
    (ROOT / 'README.generated.md').write_text(toc, encoding='utf-8')
    print('Generated docs/README.generated.md (review and merge manually).')


if __name__ == '__main__':
    main()
