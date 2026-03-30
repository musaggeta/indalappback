package com.indalapp.indalappback.products.dto;

import java.util.List;

public class ProductRecipeResponse {

    private Long id;
    private String name;
    private Double stock;
    private Double estimatedCost;
    private List<ProductRecipeIngredientResponse> ingredients;

    public ProductRecipeResponse() {
    }

    public ProductRecipeResponse(
            Long id,
            String name,
            Double stock,
            Double estimatedCost,
            List<ProductRecipeIngredientResponse> ingredients
    ) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.estimatedCost = estimatedCost;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getStock() {
        return stock;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public List<ProductRecipeIngredientResponse> getIngredients() {
        return ingredients;
    }
}