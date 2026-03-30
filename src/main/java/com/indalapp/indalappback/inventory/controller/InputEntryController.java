package com.indalapp.indalappback.inventory.controller;

import com.indalapp.indalappback.inventory.dto.InputEntryRequest;
import com.indalapp.indalappback.inventory.dto.InputEntryResponse;
import com.indalapp.indalappback.inventory.service.InputEntryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inputs")
public class InputEntryController {

    private final InputEntryService inputEntryService;

    public InputEntryController(InputEntryService inputEntryService) {
        this.inputEntryService = inputEntryService;
    }

    @GetMapping
    public ResponseEntity<List<InputEntryResponse>> getAll() {
        return ResponseEntity.ok(inputEntryService.getAll());
    }

    @PostMapping
    public ResponseEntity<InputEntryResponse> create(@Valid @RequestBody InputEntryRequest request) {
        return ResponseEntity.ok(inputEntryService.create(request));
    }
}