package org.autolabsuite.application.labs;

import org.autolabsuite.domain.model.lab.Lab;
import org.autolabsuite.domain.ports.repository.LabRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Application service for Labs.
 */
@Service
public class LabsService {

  private final LabRepository labRepository;

  public LabsService(LabRepository labRepository) {
    this.labRepository = labRepository;
  }

  public Lab create(Lab draft) {
    return labRepository.save(draft);
  }

  public Optional<Lab> findById(UUID id) {
    return labRepository.findById(id);
  }

  public List<Lab> findByCollectionId(UUID collectionId) {
    return labRepository.findByCollectionId(collectionId);
  }

  public Optional<Lab> update(UUID id, String title, String description, java.time.Instant dueDate) {
    return labRepository.findById(id)
        .map(existing -> existing.withUpdated(title, description, dueDate))
        .map(labRepository::save);
  }

  public void delete(UUID id) {
    labRepository.deleteById(id);
  }
}
