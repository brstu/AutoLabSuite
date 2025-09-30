"""
send_task.py

Usage:
  python send_task.py --payload-file sample_payloads.json --key assignment_1 --endpoint http://localhost:8000/evaluate

This small script POSTs a payload to a model endpoint and prints the response.
It supports reading endpoint/API key from environment variables `MODEL_ENDPOINT` and `MODEL_API_KEY`.
"""

import argparse
import json
import os
import sys
from typing import Any, Dict

import requests


def load_payload(path: str, key: str) -> Dict[str, Any]:
    with open(path, "r", encoding="utf-8") as f:
        data = json.load(f)
    if key not in data:
        raise KeyError(f"payload key '{key}' not found in {path}")
    return data[key]


def send(payload: Dict[str, Any], endpoint: str, api_key: str = None) -> Dict[str, Any]:
    headers = {"Content-Type": "application/json"}
    if api_key:
        headers["Authorization"] = f"Bearer {api_key}"
    resp = requests.post(endpoint, json=payload, headers=headers, timeout=30)
    resp.raise_for_status()
    return resp.json()


def main(argv=None):
    parser = argparse.ArgumentParser()
    parser.add_argument("--payload-file", default="ml/tests/sample_payloads.json")
    parser.add_argument("--key", required=True)
    parser.add_argument("--endpoint", default=os.getenv("MODEL_ENDPOINT"))
    parser.add_argument("--api-key", default=os.getenv("MODEL_API_KEY"))
    args = parser.parse_args(argv)

    if not args.endpoint:
        print("No endpoint specified via --endpoint or MODEL_ENDPOINT env var", file=sys.stderr)
        sys.exit(2)

    payload = load_payload(args.payload_file, args.key)
    try:
        result = send(payload, args.endpoint, args.api_key)
    except Exception as e:
        print(f"Request failed: {e}", file=sys.stderr)
        sys.exit(3)

    print(json.dumps(result, indent=2, ensure_ascii=False))


if __name__ == "__main__":
    main()
