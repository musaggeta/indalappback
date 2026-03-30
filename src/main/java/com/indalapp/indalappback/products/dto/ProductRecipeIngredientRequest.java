package com.indalapp.indalappback.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductRecipeIngredientRequest {

    @NotNull(message = "La materia prima es obligatoria")
    private Long rawMaterialId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Double quantity;

    public ProductRecipeIngredientRequest() {
    }

    public Long getRawMaterialId() {
        return rawMaterialId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setRawMaterialId(Long rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}