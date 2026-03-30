package com.indalapp.indalappback.products.controller;

import com.indalapp.indalappback.products.dto.ProductRecipeRequest;
import com.indalapp.indalappback.products.dto.ProductRecipeResponse;
import com.indalapp.indalappback.products.service.ProductRecipeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-recipe")
public class ProductRecipeController {

    private final ProductRecipeService productRecipeService;

    public ProductRecipeController(ProductRecipeService productRecipeService) {
        this.productRecipeService = productRecipeService;
    }

    @GetMapping
    public ResponseEntity<List<ProductRecipeResponse>> getAll() {
        return ResponseEntity.ok(productRecipeService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProductRecipeResponse> create(
            @Valid @RequestBody ProductRecipeRequest request
    ) {
        return ResponseEntity.ok(productRecipeService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRecipeResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRecipeRequest request
    ) {
        return ResponseEntity.ok(productRecipeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productRecipeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}