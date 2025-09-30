AI Modules testing helpers

This folder contains example scripts and tests for sending assignments/tasks to AI grading/assessment models.

Modes supported:
- mock: uses a local mock server or pytest fixtures to simulate model responses
- integration: sends real HTTP requests to a model endpoint (requires credentials and enabling)

Files:
- `send_task.py` - small CLI to send a task payload to a model endpoint (mock or real)
- `test_model_integration.py` - pytest tests demonstrating unit (mock) and optional integration tests
- `sample_payloads.json` - example payloads for tests

Usage:
1. Create a Python virtualenv and install dependencies: `pip install -r requirements.txt`
2. Run unit tests (mock) with: `pytest -q ml/tests`
3. To run integration tests, set environment variable `MODEL_ENDPOINT` and optionally `MODEL_API_KEY` and run pytest with `-m integration` toggle.

Security: avoid committing real API keys - use environment variables or secret managers.
