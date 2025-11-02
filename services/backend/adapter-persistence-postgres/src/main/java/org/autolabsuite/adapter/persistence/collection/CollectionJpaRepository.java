package org.autolabsuite.adapter.persistence.collection;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CollectionJpaRepository extends JpaRepository<CollectionEntity, UUID> {
  List<CollectionEntity> findAllByCourseId(String courseId);
}
