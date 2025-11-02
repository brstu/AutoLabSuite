package org.autolabsuite.domain.model.ml;

import java.time.Instant;
import java.util.UUID;

public record MlMetric(
    UUID id,
    String provider,
    String model,
    Integer promptTokens,
    Integer completionTokens,
    Integer totalTokens,
    Long latencyMs,
    boolean success,
    String errorCode,
    Instant createdAt
) {
  public static MlMetric success(String provider, String model, int promptTokens, int completionTokens, long latencyMs) {
    return new MlMetric(
        UUID.randomUUID(),
        provider,
        model,
        promptTokens,
        completionTokens,
        promptTokens + completionTokens,
        latencyMs,
        true,
        null,
        Instant.now()
    );
  }

  public static MlMetric failure(String provider, String model, String errorCode, long latencyMs) {
    return new MlMetric(
        UUID.randomUUID(),
        provider,
        model,
        null,
        null,
        null,
        latencyMs,
        false,
        errorCode,
        Instant.now()
    );
  }
}
