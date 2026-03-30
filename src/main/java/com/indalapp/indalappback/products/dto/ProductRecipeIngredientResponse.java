package com.indalapp.indalappback.products.dto;

public class ProductRecipeIngredientResponse {

    private Long rawMaterialId;
    private String rawMaterialName;
    private String unitMeasurement;
    private Double quantity;

    public ProductRecipeIngredientResponse() {
    }

    public ProductRecipeIngredientResponse(
            Long rawMaterialId,
            String rawMaterialName,
            String unitMeasurement,
            Double quantity
    ) {
        this.rawMaterialId = rawMaterialId;
        this.rawMaterialName = rawMaterialName;
        this.unitMeasurement = unitMeasurement;
        this.quantity = quantity;
    }

    public Long getRawMaterialId() {
        return rawMaterialId;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public Double getQuantity() {
        return quantity;
    }
}