package org.autolabsuite.adapter.ml.impl;

import org.autolabsuite.domain.model.ml.MlMetric;
import org.autolabsuite.domain.ports.ml.MlProviderPort;
import org.autolabsuite.domain.ports.repository.MlMetricRepository;
import org.springframework.stereotype.Component;

@Component
public class MlAdapter implements MlProviderPort {

  private final MlMetricRepository mlMetricRepository;

  public MlAdapter(MlMetricRepository mlMetricRepository) {
    this.mlMetricRepository = mlMetricRepository;
  }

  @Override
  public void logMetric(MlMetric metric) {
    mlMetricRepository.save(metric);
  }
}
