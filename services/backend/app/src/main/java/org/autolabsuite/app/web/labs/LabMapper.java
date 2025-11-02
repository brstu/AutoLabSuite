package org.autolabsuite.app.web.labs;

import org.autolabsuite.app.web.labs.dto.LabDtos.LabCreateRequest;
import org.autolabsuite.app.web.labs.dto.LabDtos.LabDto;
import org.autolabsuite.domain.model.lab.Lab;

final class LabMapper {
  private LabMapper() {}

  static LabDto toDto(Lab d) {
    return new LabDto(d.id(), d.collectionId(), d.title(), d.description(), d.dueDate(), d.createdAt(), d.updatedAt());
  }

  static Lab toDomain(LabCreateRequest r) {
    return Lab.newEntity(r.collectionId(), r.title(), r.description(), r.dueDate());
  }
}
