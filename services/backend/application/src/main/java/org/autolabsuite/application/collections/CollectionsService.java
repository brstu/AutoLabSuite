package org.autolabsuite.application.collections;

import org.autolabsuite.domain.model.collection.Collection;
import org.autolabsuite.domain.ports.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Application service (use-cases) for Collections.
 * Contains orchestration logic and transactional boundaries (to be added later).
 */
@Service
public class CollectionsService {

  private final CollectionRepository collectionRepository;

  public CollectionsService(CollectionRepository collectionRepository) {
    this.collectionRepository = collectionRepository;
  }

  public Collection create(Collection draft) {
    // Place to check uniqueness or other business rules
    return collectionRepository.save(draft);
  }

  public Optional<Collection> findById(UUID id) {
    return collectionRepository.findById(id);
  }

  public List<Collection> findAll(String courseId) {
    if (courseId == null || courseId.isBlank()) return collectionRepository.findAll();
    return collectionRepository.findAllByCourseId(courseId);
  }

  public Optional<Collection> update(UUID id, String name, String description) {
    return collectionRepository.findById(id)
        .map(existing -> existing.withUpdated(name, description))
        .map(collectionRepository::save);
  }

  public void delete(UUID id) {
    collectionRepository.deleteById(id);
  }
}
