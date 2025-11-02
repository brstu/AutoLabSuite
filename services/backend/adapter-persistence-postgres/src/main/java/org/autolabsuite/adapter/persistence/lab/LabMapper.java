package org.autolabsuite.adapter.persistence.lab;

import org.autolabsuite.domain.model.lab.Lab;

public final class LabMapper {
  private LabMapper() {}

  public static LabEntity toEntity(Lab d) {
    var e = new LabEntity();
    e.setId(d.id());
    e.setCollectionId(d.collectionId());
    e.setTitle(d.title());
    e.setDescription(d.description());
    e.setDueDate(d.dueDate());
    e.setCreatedAt(d.createdAt());
    e.setUpdatedAt(d.updatedAt());
    return e;
  }

  public static Lab toDomain(LabEntity e) {
    return new Lab(
        e.getId(),
        e.getCollectionId(),
        e.getTitle(),
        e.getDescription(),
        e.getDueDate(),
        e.getCreatedAt(),
        e.getUpdatedAt()
    );
  }
}
