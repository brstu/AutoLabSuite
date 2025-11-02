package org.autolabsuite.domain.model.core;

import java.util.UUID;

public record Identifiers(UUID id) {
  public static Identifiers newId() {
    return new Identifiers(UUID.randomUUID());
  }
}
