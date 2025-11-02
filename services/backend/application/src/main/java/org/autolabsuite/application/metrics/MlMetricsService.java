package org.autolabsuite.application.metrics;

import org.autolabsuite.domain.model.ml.MlMetric;
import org.autolabsuite.domain.ports.ml.MlProviderPort;

public class MlMetricsService {
  private final MlProviderPort mlProviderPort;

  public MlMetricsService(MlProviderPort mlProviderPort) {
    this.mlProviderPort = mlProviderPort;
  }

  public void logSuccess(String provider, String model, int promptTokens, int completionTokens, long latencyMs) {
    MlMetric metric = MlMetric.success(provider, model, promptTokens, completionTokens, latencyMs);
    mlProviderPort.logMetric(metric);
  }

  public void logFailure(String provider, String model, String errorCode, long latencyMs) {
    MlMetric metric = MlMetric.failure(provider, model, errorCode, latencyMs);
    mlProviderPort.logMetric(metric);
  }
}
