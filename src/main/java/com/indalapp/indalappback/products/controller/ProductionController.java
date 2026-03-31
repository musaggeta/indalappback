package com.indalapp.indalappback.products.controller;

import com.indalapp.indalappback.products.dto.ProductionRequest;
import com.indalapp.indalappback.products.dto.ProductionResponse;
import com.indalapp.indalappback.products.service.ProductionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productions")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public ResponseEntity<List<ProductionResponse>> getAll() {
        return ResponseEntity.ok(productionService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProductionResponse> create(
            @Valid @RequestBody ProductionRequest request
    ) {
        return ResponseEntity.ok(productionService.create(request));
    }
}