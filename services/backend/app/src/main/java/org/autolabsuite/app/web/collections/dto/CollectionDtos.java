package org.autolabsuite.app.web.collections.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;

/**
 * API layer DTOs for Collections. Decoupled from domain to keep transport concerns separate.
 */
public class CollectionDtos {

  public record CollectionDto(
      UUID id,
      String name,
      String courseId,
      String description,
      Instant createdAt,
      Instant updatedAt
  ) {}

  public record CollectionCreateRequest(
      @NotBlank String name,
      @NotBlank String courseId,
      String description
  ) {}

  public record CollectionUpdateRequest(
      @NotBlank String name,
      String description
  ) {}
}
