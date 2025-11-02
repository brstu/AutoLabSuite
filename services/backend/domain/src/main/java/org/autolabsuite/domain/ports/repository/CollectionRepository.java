package org.autolabsuite.domain.ports.repository;

import org.autolabsuite.domain.model.collection.Collection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port for Collection persistence (hexagonal architecture).
 */
public interface CollectionRepository {
  Collection save(Collection collection);
  Optional<Collection> findById(UUID id);
  List<Collection> findAll();
  List<Collection> findAllByCourseId(String courseId);
  void deleteById(UUID id);
}
