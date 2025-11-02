package org.autolabsuite.adapter.persistence.lab;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LabJpaRepository extends JpaRepository<LabEntity, UUID> {
  List<LabEntity> findByCollectionId(UUID collectionId);
}
