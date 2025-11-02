package org.autolabsuite.adapter.persistence.lab;

import org.autolabsuite.domain.model.lab.Lab;
import org.autolabsuite.domain.ports.repository.LabRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LabRepositoryJpa implements LabRepository {

  private final LabJpaRepository jpaRepository;

  public LabRepositoryJpa(LabJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public Lab save(Lab lab) {
    return LabMapper.toDomain(jpaRepository.save(LabMapper.toEntity(lab)));
  }

  @Override
  public Optional<Lab> findById(UUID id) {
    return jpaRepository.findById(id).map(LabMapper::toDomain);
  }

  @Override
  public List<Lab> findByCollectionId(UUID collectionId) {
    return jpaRepository.findByCollectionId(collectionId).stream().map(LabMapper::toDomain).toList();
  }

  @Override
  public void deleteById(UUID id) {
    jpaRepository.deleteById(id);
  }
}
