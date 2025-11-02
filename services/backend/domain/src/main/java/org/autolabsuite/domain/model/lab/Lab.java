package org.autolabsuite.domain.model.lab;

import java.time.Instant;
import java.util.UUID;

/**
 * Domain entity for a Lab item that belongs to a Collection.
 */
public record Lab(
    UUID id,
    UUID collectionId,
    String title,
    String description,
    Instant dueDate,
    Instant createdAt,
    Instant updatedAt
) {
  public static Lab newEntity(UUID collectionId, String title, String description, Instant dueDate) {
    if (collectionId == null) throw new IllegalArgumentException("collectionId must not be null");
    if (title == null || title.isBlank()) throw new IllegalArgumentException("title must not be blank");
    var now = Instant.now();
    return new Lab(UUID.randomUUID(), collectionId, title.trim(), description, dueDate, now, now);
  }

  public Lab withUpdated(String title, String description, Instant dueDate) {
    if (title == null || title.isBlank()) throw new IllegalArgumentException("title must not be blank");
    return new Lab(this.id, this.collectionId, title.trim(), description, dueDate, this.createdAt, Instant.now());
  }
}
