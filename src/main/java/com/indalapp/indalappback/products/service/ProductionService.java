package com.indalapp.indalappback.products.service;

import com.indalapp.indalappback.inventory.entity.RawMaterial;
import com.indalapp.indalappback.inventory.repository.RawMaterialRepository;
import com.indalapp.indalappback.products.dto.ProductionRequest;
import com.indalapp.indalappback.products.dto.ProductionResponse;
import com.indalapp.indalappback.products.entity.ProductRecipe;
import com.indalapp.indalappback.products.entity.ProductRecipeIngredient;
import com.indalapp.indalappback.products.entity.Production;
import com.indalapp.indalappback.products.repository.ProductRecipeRepository;
import com.indalapp.indalappback.products.repository.ProductionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionService {

    private final ProductionRepository productionRepository;
    private final ProductRecipeRepository productRecipeRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductionService(
            ProductionRepository productionRepository,
            ProductRecipeRepository productRecipeRepository,
            RawMaterialRepository rawMaterialRepository
    ) {
        this.productionRepository = productionRepository;
        this.productRecipeRepository = productRecipeRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<ProductionResponse> getAll() {
        return productionRepository.findAllByOrderByProductionDateDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductionResponse create(ProductionRequest request) {
        ProductRecipe recipe = productRecipeRepository.findById(request.getProductRecipeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Receta de producto no encontrada"
                ));

        double totalCost = 0.0;

        for (ProductRecipeIngredient ingredient : recipe.getIngredients()) {
            RawMaterial rawMaterial = ingredient.getRawMaterial();
            double requiredQuantity = ingredient.getQuantity() * request.getQuantityProduced();

            if (rawMaterial.getStock() < requiredQuantity) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "No hay suficiente stock de " + rawMaterial.getName()
                );
            }
        }

        for (ProductRecipeIngredient ingredient : recipe.getIngredients()) {
            RawMaterial rawMaterial = ingredient.getRawMaterial();
            double requiredQuantity = ingredient.getQuantity() * request.getQuantityProduced();

            rawMaterial.setStock(rawMaterial.getStock() - requiredQuantity);
            rawMaterialRepository.save(rawMaterial);

            totalCost += requiredQuantity * rawMaterial.getAvgCost();
        }

        recipe.setStock(recipe.getStock() + request.getQuantityProduced());
        productRecipeRepository.save(recipe);

        Production production = new Production();
        production.setProductRecipe(recipe);
        production.setQuantityProduced(request.getQuantityProduced());
        production.setTotalCost(totalCost);
        production.setProductionDate(LocalDateTime.now());

        Production saved = productionRepository.save(production);

        return mapToResponse(saved);
    }

    private ProductionResponse mapToResponse(Production production) {
        ProductRecipe recipe = production.getProductRecipe();

        List<String> materialsUsed = recipe.getIngredients()
                .stream()
                .map(ingredient -> {
                    double used = ingredient.getQuantity() * production.getQuantityProduced();
                    return ingredient.getRawMaterial().getName()
                            + " (" + String.format("%.2f", used) + " "
                            + ingredient.getRawMaterial().getUnitMeasurement() + ")";
                })
                .toList();

        return new ProductionResponse(
                production.getId(),
                recipe.getId(),
                recipe.getName(),
                production.getQuantityProduced(),
                production.getTotalCost(),
                production.getProductionDate(),
                materialsUsed
        );
    }
}