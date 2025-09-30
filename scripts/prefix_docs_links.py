#!/usr/bin/env python3
"""
Make links to docs root-relative by replacing '](docs/' with '](/docs/' in Markdown files.
Creates .bak copies before modifying.
"""
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent

def fix_file(path: Path) -> int:
    text = path.read_text(encoding='utf-8')
    if '](docs/' not in text:
        return 0
    new = text.replace('](docs/', '](/docs/')
    bak = path.with_suffix(path.suffix + '.bak')
    bak.write_text(text, encoding='utf-8')
    path.write_text(new, encoding='utf-8')
    print(f'Updated {path}')
    return 1

def main():
    count = 0
    for md in ROOT.rglob('*.md'):
        count += fix_file(md)
    print(f'Done. Files updated: {count}')

if __name__ == '__main__':
    main()
