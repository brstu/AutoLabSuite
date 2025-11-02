package org.autolabsuite.adapter.persistence.ml;

import org.autolabsuite.domain.model.ml.MlMetric;
import org.autolabsuite.domain.ports.repository.MlMetricRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MlMetricRepositoryJpa implements MlMetricRepository {

  private final MlMetricJpaRepository jpaRepository;

  public MlMetricRepositoryJpa(MlMetricJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public MlMetric save(MlMetric metric) {
    MlMetricEntity saved = jpaRepository.save(MlMetricMapper.toEntity(metric));
    return MlMetricMapper.toDomain(saved);
  }
}
