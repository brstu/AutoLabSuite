package org.autolabsuite.domain.ports.repository;

import org.autolabsuite.domain.model.lab.Lab;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port for Lab persistence.
 */
public interface LabRepository {
  Lab save(Lab lab);
  Optional<Lab> findById(UUID id);
  List<Lab> findByCollectionId(UUID collectionId);
  void deleteById(UUID id);
}
