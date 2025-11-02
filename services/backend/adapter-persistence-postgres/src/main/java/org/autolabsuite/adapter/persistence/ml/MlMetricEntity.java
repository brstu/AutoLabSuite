package org.autolabsuite.adapter.persistence.ml;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ml_metrics")
@Getter
@Setter
@NoArgsConstructor
public class MlMetricEntity {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Column(nullable = false)
  private String provider;

  @Column(nullable = false)
  private String model;

  private Integer promptTokens;
  private Integer completionTokens;
  private Integer totalTokens;
  private Long latencyMs;

  @Column(nullable = false)
  private boolean success;

  private String errorCode;

  @Column(nullable = false)
  private Instant createdAt;
}
