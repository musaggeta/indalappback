package com.indalapp.indalappback.products.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class ProductionRequest {

    @NotNull(message = "La receta de producto es obligatoria")
    private Long productRecipeId;

    @NotNull(message = "La cantidad a producir es obligatoria")
    @DecimalMin(value = "0.0001", message = "La cantidad a producir debe ser mayor a 0")
    private Double quantityProduced;

    public ProductionRequest() {
    }

    public Long getProductRecipeId() {
        return productRecipeId;
    }

    public Double getQuantityProduced() {
        return quantityProduced;
    }

    public void setProductRecipeId(Long productRecipeId) {
        this.productRecipeId = productRecipeId;
    }

    public void setQuantityProduced(Double quantityProduced) {
        this.quantityProduced = quantityProduced;
    }
}