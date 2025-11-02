package org.autolabsuite.adapter.persistence.ml;

import org.autolabsuite.domain.model.ml.MlMetric;

public final class MlMetricMapper {
  private MlMetricMapper() {}

  public static MlMetricEntity toEntity(MlMetric metric) {
    MlMetricEntity e = new MlMetricEntity();
    e.setId(metric.id());
    e.setProvider(metric.provider());
    e.setModel(metric.model());
    e.setPromptTokens(metric.promptTokens());
    e.setCompletionTokens(metric.completionTokens());
    e.setTotalTokens(metric.totalTokens());
    e.setLatencyMs(metric.latencyMs());
    e.setSuccess(metric.success());
    e.setErrorCode(metric.errorCode());
    e.setCreatedAt(metric.createdAt());
    return e;
  }

  public static MlMetric toDomain(MlMetricEntity e) {
    return new MlMetric(
        e.getId(),
        e.getProvider(),
        e.getModel(),
        e.getPromptTokens(),
        e.getCompletionTokens(),
        e.getTotalTokens(),
        e.getLatencyMs(),
        e.isSuccess(),
        e.getErrorCode(),
        e.getCreatedAt()
    );
  }
}
