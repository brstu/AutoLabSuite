# AI Modules testing helpers

This folder contains example scripts and tests for sending assignments/tasks to AI grading/assessment models.

Modes supported:
- mock — uses a local mock server or `pytest` fixtures to simulate model responses
- integration — sends real HTTP requests to a model endpoint (requires credentials and enabling)

Files:
- `send_task.py` — small CLI to send a task payload to a model endpoint (mock or real)
- `test_model_integration.py` — `pytest` tests demonstrating unit (mock) and optional integration tests
- `sample_payloads.json` — example payloads for tests

Usage:
1. Create a Python virtualenv and install dependencies:

```powershell
python -m venv .venv
pip install -r ml/tests/requirements.txt
```

1. Run unit tests (mock):

```powershell
pytest -q ml/tests
```

1. Run integration tests (optional):

```powershell
$env:MODEL_ENDPOINT = "https://example.com/endpoint"
$env:MODEL_API_KEY = "<your-key>"
pytest -m integration
```

Notes

- Do not commit real API keys; use environment variables or a secret manager.
- Use `pytest -k "not integration"` in CI to run only fast unit tests on pull requests.

Security

Avoid committing real API keys; use environment variables or secret managers.
