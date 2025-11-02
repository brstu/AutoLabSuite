package org.autolabsuite.app.web.collections;

import org.autolabsuite.app.web.collections.dto.CollectionDtos.CollectionCreateRequest;
import org.autolabsuite.app.web.collections.dto.CollectionDtos.CollectionDto;
import org.autolabsuite.domain.model.collection.Collection;

/** Mapper between API DTOs and domain model for Collections. */
final class CollectionMapper {
  private CollectionMapper() {}

  static CollectionDto toDto(Collection d) {
    return new CollectionDto(d.id(), d.name(), d.courseId(), d.description(), d.createdAt(), d.updatedAt());
  }

  static Collection toDomain(CollectionCreateRequest r) {
    return Collection.newAggregate(r.name(), r.courseId(), r.description());
  }
}
