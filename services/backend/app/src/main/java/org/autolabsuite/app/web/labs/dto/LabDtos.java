package org.autolabsuite.app.web.labs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

/** DTOs for Lab resource. */
public class LabDtos {

  public record LabDto(
      UUID id,
      UUID collectionId,
      String title,
      String description,
      Instant dueDate,
      Instant createdAt,
      Instant updatedAt
  ) {}

  public record LabCreateRequest(
      @NotNull UUID collectionId,
      @NotBlank String title,
      String description,
      Instant dueDate
  ) {}

  public record LabUpdateRequest(
      @NotBlank String title,
      String description,
      Instant dueDate
  ) {}
}
