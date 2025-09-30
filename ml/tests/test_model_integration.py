import json
import os
import pytest
from unittest import mock

from ml.tests.send_task import send, load_payload


@pytest.fixture
def sample_payload(tmp_path):
    p = tmp_path / "payloads.json"
    p.write_text(json.dumps({
        "a": {"student_id": "s1", "submission": "x", "metadata": {"max_score": 5}}
    }))
    return str(p)


def test_load_payload(sample_payload):
    payload = load_payload(sample_payload, "a")
    assert payload["student_id"] == "s1"


@mock.patch("ml.tests.send_task.requests.post")
def test_send_success(mock_post):
    class Dummy:
        status_code = 200

        def raise_for_status(self):
            return None

        def json(self):
            return {"score": 4, "comments": "Good job"}

    mock_post.return_value = Dummy()
    payload = {"student_id": "s1", "submission": "x"}
    res = send(payload, "http://mocked", api_key="key")
    assert res["score"] == 4


@pytest.mark.skipif(not os.getenv("MODEL_ENDPOINT"), reason="no MODEL_ENDPOINT")
def test_integration_real_endpoint():
    # This test runs against a real model endpoint. Requires MODEL_ENDPOINT and optional MODEL_API_KEY in env.
    endpoint = os.environ["MODEL_ENDPOINT"]
    api_key = os.environ.get("MODEL_API_KEY")
    payload = {"student_id": "integration", "submission": "print(1)", "metadata": {"max_score": 1}}
    res = send(payload, endpoint, api_key=api_key)
    assert isinstance(res, dict)
