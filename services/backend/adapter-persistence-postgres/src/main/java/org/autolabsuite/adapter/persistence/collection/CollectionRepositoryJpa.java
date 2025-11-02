package org.autolabsuite.adapter.persistence.collection;

import org.autolabsuite.domain.model.collection.Collection;
import org.autolabsuite.domain.ports.repository.CollectionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CollectionRepositoryJpa implements CollectionRepository {

  private final CollectionJpaRepository jpaRepository;

  public CollectionRepositoryJpa(CollectionJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public Collection save(Collection collection) {
    return CollectionMapper.toDomain(jpaRepository.save(CollectionMapper.toEntity(collection)));
  }

  @Override
  public Optional<Collection> findById(UUID id) {
    return jpaRepository.findById(id).map(CollectionMapper::toDomain);
  }

  @Override
  public List<Collection> findAll() {
    return jpaRepository.findAll().stream().map(CollectionMapper::toDomain).toList();
  }

  @Override
  public List<Collection> findAllByCourseId(String courseId) {
    return jpaRepository.findAllByCourseId(courseId).stream().map(CollectionMapper::toDomain).toList();
  }

  @Override
  public void deleteById(UUID id) {
    jpaRepository.deleteById(id);
  }
}
