package org.autolabsuite.domain.model.collection;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Domain aggregate for a Collection of labs.
 * Immutable record to keep domain state clear. Creation and updates go through
 * factory/helper methods that enforce simple invariants.
 */
public record Collection(
    UUID id,
    String name,
    String courseId,
    String description,
    Instant createdAt,
    Instant updatedAt
) {
  public static Collection newAggregate(String name, String courseId, String description) {
    // Simple invariants. Deeper rules can live in application services.
    if (name == null || name.isBlank()) throw new IllegalArgumentException("name must not be blank");
    if (courseId == null || courseId.isBlank()) throw new IllegalArgumentException("courseId must not be blank");
    var now = Instant.now();
    return new Collection(UUID.randomUUID(), name.trim(), courseId.trim(), description, now, now);
  }

  public Collection withUpdated(String name, String description) {
    if (name == null || name.isBlank()) throw new IllegalArgumentException("name must not be blank");
    return new Collection(this.id, name.trim(), this.courseId, description, this.createdAt, Instant.now());
  }

  public boolean sameIdentity(Collection other) {
    return other != null && Objects.equals(this.id, other.id);
  }
}
