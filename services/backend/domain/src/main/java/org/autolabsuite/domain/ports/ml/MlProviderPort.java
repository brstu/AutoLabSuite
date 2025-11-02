package org.autolabsuite.domain.ports.ml;

import org.autolabsuite.domain.model.ml.MlMetric;

public interface MlProviderPort {
  // In future: method(s) to call models for evaluation
  void logMetric(MlMetric metric);
}
