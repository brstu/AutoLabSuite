package org.autolabsuite.domain.model.user;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record User(
    UUID id,
    String email,
    String passwordHash,
    Set<Role> roles,
    boolean active,
    Instant createdAt,
    Instant updatedAt
) {}
