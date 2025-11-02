package org.autolabsuite.adapter.persistence.ml;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MlMetricJpaRepository extends JpaRepository<MlMetricEntity, UUID> {
}
