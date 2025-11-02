package org.autolabsuite.app.web.labs;

import jakarta.validation.Valid;
import org.autolabsuite.app.web.errors.NotFoundException;
import org.autolabsuite.app.web.labs.dto.LabDtos.LabCreateRequest;
import org.autolabsuite.app.web.labs.dto.LabDtos.LabDto;
import org.autolabsuite.app.web.labs.dto.LabDtos.LabUpdateRequest;
import org.autolabsuite.application.labs.LabsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/** REST controller for Labs under collections. */
@RestController
@RequestMapping("/api/v1/labs")
public class LabsController {

  private final LabsService labsService;

  public LabsController(LabsService labsService) {
    this.labsService = labsService;
  }

  @GetMapping
  public ResponseEntity<List<LabDto>> list(@RequestParam UUID collectionId) {
    var list = labsService.findByCollectionId(collectionId).stream().map(LabMapper::toDto).toList();
    return ResponseEntity.ok(list);
  }

  @PostMapping
  public ResponseEntity<LabDto> create(@Valid @RequestBody LabCreateRequest req) {
    var created = labsService.create(LabMapper.toDomain(req));
    return ResponseEntity.status(HttpStatus.CREATED).body(LabMapper.toDto(created));
  }

  @GetMapping("/{id}")
  public ResponseEntity<LabDto> get(@PathVariable UUID id) {
    return labsService.findById(id)
        .map(LabMapper::toDto)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new NotFoundException("Lab not found: " + id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<LabDto> update(@PathVariable UUID id, @Valid @RequestBody LabUpdateRequest req) {
    return labsService.update(id, req.title(), req.description(), req.dueDate())
        .map(LabMapper::toDto)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new NotFoundException("Lab not found: " + id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    labsService.delete(id);
  }
}
