package com.indalapp.indalappback.inventory.controller;

import com.indalapp.indalappback.inventory.dto.RawMaterialRequest;
import com.indalapp.indalappback.inventory.dto.RawMaterialResponse;
import com.indalapp.indalappback.inventory.service.RawMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @GetMapping
    public ResponseEntity<List<RawMaterialResponse>> getAll() {
        return ResponseEntity.ok(rawMaterialService.getAll());
    }

    @PostMapping
    public ResponseEntity<RawMaterialResponse> create(@Valid @RequestBody RawMaterialRequest request) {
        return ResponseEntity.ok(rawMaterialService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RawMaterialRequest request
    ) {
        return ResponseEntity.ok(rawMaterialService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rawMaterialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}