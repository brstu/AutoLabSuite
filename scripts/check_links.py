#!/usr/bin/env python3
"""
Check links in Markdown files: external URLs (HEAD/GET) and internal relative paths (file exists).
Prints a simple report of failures.
"""
import re
from pathlib import Path
import requests
import sys

ROOT = Path(__file__).resolve().parent.parent
MD_FILES = list(ROOT.rglob("*.md"))

LINK_RE = re.compile(r"\[([^\]]+)\]\(([^)]+)\)")


def check_url(url: str) -> bool:
    try:
        resp = requests.head(url, allow_redirects=True, timeout=5)
        if resp.status_code >= 400:
            resp = requests.get(url, allow_redirects=True, timeout=5)
        return resp.status_code < 400
    except Exception:
        return False


def main():
    failures = []
    for md in MD_FILES:
        text = md.read_text(encoding="utf-8")
        for m in LINK_RE.finditer(text):
            target = m.group(2).strip()
            if target.startswith("http://") or target.startswith("https://"):
                ok = check_url(target)
                if not ok:
                    failures.append((md, target, 'http'))
            else:
                # strip optional anchors and query
                rel = target.split('#', 1)[0].split('?', 1)[0]
                # If link starts with a leading slash, resolve relative to repo root
                if rel.startswith('/'):
                    candidate = (Path(__file__).resolve().parent.parent / rel.lstrip('/')).resolve()
                else:
                    candidate = (md.parent / rel).resolve()
                if candidate.exists():
                    # If it's a directory, accept it if it contains README.md or index.md
                    if candidate.is_dir():
                        if not any((candidate / n).exists() for n in ('README.md', 'index.md')):
                            failures.append((md, target, 'file-dir-no-index'))
                    # otherwise file exists -> ok
                else:
                    # if path looks like a directory (ends with /), try resolving relative to repo root
                    if target.endswith('/'):
                        root_candidate = (Path(__file__).resolve().parent.parent / rel.lstrip('/')).resolve()
                        if root_candidate.exists() and any((root_candidate / n).exists() for n in ('README.md', 'index.md')):
                            continue
                    failures.append((md, target, 'file'))

    if not failures:
        print("All checked links passed.")
        return 0
    print("Broken links found:")
    for md, target, kind in failures:
        print(f"{md}: {target} ({kind})")
    return 2


if __name__ == '__main__':
    sys.exit(main())
