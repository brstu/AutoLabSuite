package org.autolabsuite.adapter.persistence.collection;

import org.autolabsuite.domain.model.collection.Collection;

public final class CollectionMapper {
  private CollectionMapper() {}

  public static CollectionEntity toEntity(Collection d) {
    var e = new CollectionEntity();
    e.setId(d.id());
    e.setName(d.name());
    e.setCourseId(d.courseId());
    e.setDescription(d.description());
    e.setCreatedAt(d.createdAt());
    e.setUpdatedAt(d.updatedAt());
    return e;
  }

  public static Collection toDomain(CollectionEntity e) {
    return new Collection(
        e.getId(),
        e.getName(),
        e.getCourseId(),
        e.getDescription(),
        e.getCreatedAt(),
        e.getUpdatedAt()
    );
  }
}
