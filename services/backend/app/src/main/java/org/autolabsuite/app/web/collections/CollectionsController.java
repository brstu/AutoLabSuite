package org.autolabsuite.app.web.collections;

import jakarta.validation.Valid;
import org.autolabsuite.app.web.collections.dto.CollectionDtos.CollectionCreateRequest;
import org.autolabsuite.app.web.collections.dto.CollectionDtos.CollectionDto;
import org.autolabsuite.app.web.collections.dto.CollectionDtos.CollectionUpdateRequest;
import org.autolabsuite.app.web.errors.NotFoundException;
import org.autolabsuite.application.collections.CollectionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for Collections resource.
 * Align with docs/api/openapi.yaml (paths, DTO shapes, status codes) incrementally.
 */
@RestController
@RequestMapping("/api/v1/collections")
public class CollectionsController {

  private final CollectionsService collectionsService;

  public CollectionsController(CollectionsService collectionsService) {
    this.collectionsService = collectionsService;
  }

  @GetMapping
  public ResponseEntity<List<CollectionDto>> list(@RequestParam(required = false) String courseId) {
    var list = collectionsService.findAll(courseId).stream().map(CollectionMapper::toDto).toList();
    return ResponseEntity.ok(list);
  }

  @PostMapping
  public ResponseEntity<CollectionDto> create(@Valid @RequestBody CollectionCreateRequest req) {
    var created = collectionsService.create(CollectionMapper.toDomain(req));
    return ResponseEntity.status(HttpStatus.CREATED).body(CollectionMapper.toDto(created));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CollectionDto> get(@PathVariable UUID id) {
    return collectionsService.findById(id)
        .map(CollectionMapper::toDto)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new NotFoundException("Collection not found: " + id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CollectionDto> update(@PathVariable UUID id, @Valid @RequestBody CollectionUpdateRequest req) {
    return collectionsService.update(id, req.name(), req.description())
        .map(CollectionMapper::toDto)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new NotFoundException("Collection not found: " + id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    collectionsService.delete(id);
  }
}
