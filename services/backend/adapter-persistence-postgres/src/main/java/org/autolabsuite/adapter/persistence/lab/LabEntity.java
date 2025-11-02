package org.autolabsuite.adapter.persistence.lab;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "labs")
@Getter
@Setter
@NoArgsConstructor
public class LabEntity {
  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Column(nullable = false)
  private UUID collectionId;

  @Column(nullable = false)
  private String title;

  @Column
  private String description;

  @Column
  private Instant dueDate;

  @Column(nullable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Instant updatedAt;
}
