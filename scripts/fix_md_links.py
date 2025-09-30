#!/usr/bin/env python3
"""
Simple script to convert backticked relative paths in Markdown files
like `docs/overview/` or `docs/overview/README.md` into proper
Markdown links: [docs/overview/](docs/overview/)

It modifies files in-place and creates a .bak copy for safety.
"""
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent

PATTERN = re.compile(r"`(docs/[\w\-/.]+)`")


def fix_file(path: Path) -> int:
    text = path.read_text(encoding="utf-8")
    new_text, n = PATTERN.subn(r"[\1](\1)", text)
    if n:
        bak = path.with_suffix(path.suffix + ".bak")
        bak.write_text(text, encoding="utf-8")
        path.write_text(new_text, encoding="utf-8")
        print(f"Updated {path} ({n} replacements)")
    return n


def main():
    md_files = list(ROOT.rglob("*.md"))
    total = 0
    for f in md_files:
        total += fix_file(f)
    print(f"Done. Total replacements: {total}")


if __name__ == "__main__":
    main()
