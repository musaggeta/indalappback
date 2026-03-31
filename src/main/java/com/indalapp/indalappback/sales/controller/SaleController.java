package com.indalapp.indalappback.sales.controller;

import com.indalapp.indalappback.sales.dto.SaleRequest;
import com.indalapp.indalappback.sales.dto.SaleResponse;
import com.indalapp.indalappback.sales.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAll() {
        return ResponseEntity.ok(saleService.getAll());
    }

    @PostMapping
    public ResponseEntity<SaleResponse> create(@Valid @RequestBody SaleRequest request) {
        return ResponseEntity.ok(saleService.create(request));
    }
}