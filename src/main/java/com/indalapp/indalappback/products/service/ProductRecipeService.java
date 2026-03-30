package com.indalapp.indalappback.products.service;

import com.indalapp.indalappback.products.dto.*;
import com.indalapp.indalappback.products.entity.ProductRecipe;
import com.indalapp.indalappback.products.entity.ProductRecipeIngredient;
import com.indalapp.indalappback.inventory.entity.RawMaterial;
import com.indalapp.indalappback.products.repository.ProductRecipeRepository;
import com.indalapp.indalappback.inventory.repository.RawMaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRecipeService {

    private final ProductRecipeRepository productRecipeRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductRecipeService(
            ProductRecipeRepository ProductRecipeRepository,
            RawMaterialRepository rawMaterialRepository
    ) {
        this.productRecipeRepository = ProductRecipeRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<ProductRecipeResponse> getAll() {
        return productRecipeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductRecipeResponse create(ProductRecipeRequest request) {
        ProductRecipe product = new ProductRecipe();
        product.setName(request.getName());
        product.setStock(0.0);

        List<ProductRecipeIngredient> ingredients = new ArrayList<>();
        double estimatedCost = 0.0;

        for (ProductRecipeIngredientRequest item : request.getIngredients()) {
            RawMaterial rawMaterial = rawMaterialRepository.findById(item.getRawMaterialId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Materia prima no encontrada"
                    ));

            ProductRecipeIngredient ingredient = new ProductRecipeIngredient();
            ingredient.setProductRecipe(product);
            ingredient.setRawMaterial(rawMaterial);
            ingredient.setQuantity(item.getQuantity());

            ingredients.add(ingredient);

            estimatedCost += item.getQuantity() * rawMaterial.getAvgCost();
        }

        product.setEstimatedCost(estimatedCost);
        product.setIngredients(ingredients);

        ProductRecipe saved = productRecipeRepository.save(product);

        return mapToResponse(saved);
    }

    public ProductRecipeResponse update(Long id, ProductRecipeRequest request) {
        ProductRecipe product = productRecipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto terminado no encontrado"
                ));

        product.setName(request.getName());
        product.getIngredients().clear();

        double estimatedCost = 0.0;

        for (ProductRecipeIngredientRequest item : request.getIngredients()) {
            RawMaterial rawMaterial = rawMaterialRepository.findById(item.getRawMaterialId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Materia prima no encontrada"
                    ));

            ProductRecipeIngredient ingredient = new ProductRecipeIngredient();
            ingredient.setProductRecipe(product);
            ingredient.setRawMaterial(rawMaterial);
            ingredient.setQuantity(item.getQuantity());

            product.getIngredients().add(ingredient);

            estimatedCost += item.getQuantity() * rawMaterial.getAvgCost();
        }

        product.setEstimatedCost(estimatedCost);

        ProductRecipe updated = productRecipeRepository.save(product);

        return mapToResponse(updated);
    }

    public void delete(Long id) {
        if (!productRecipeRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto terminado no encontrado"
            );
        }

        productRecipeRepository.deleteById(id);
    }

    private ProductRecipeResponse mapToResponse(ProductRecipe product) {
        List<ProductRecipeIngredientResponse> ingredients = product.getIngredients()
                .stream()
                .map(item -> new ProductRecipeIngredientResponse(
                        item.getRawMaterial().getId(),
                        item.getRawMaterial().getName(),
                        item.getRawMaterial().getUnitMeasurement(),
                        item.getQuantity()
                ))
                .toList();

        return new ProductRecipeResponse(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getEstimatedCost(),
                ingredients
        );
    }
}