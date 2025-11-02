package org.autolabsuite.domain.ports.repository;

import org.autolabsuite.domain.model.ml.MlMetric;

public interface MlMetricRepository {
  MlMetric save(MlMetric metric);
}
