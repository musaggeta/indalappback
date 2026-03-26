package com.indalapp.indalappback.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RawMaterialRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "La unidad de medida es obligatoria")
    private String unitMeasurement;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Double minStockLevel;

    public RawMaterialRequest() {
    }

    public String getName() {
        return name;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public Double getMinStockLevel() {
        return minStockLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public void setMinStockLevel(Double minStockLevel) {
        this.minStockLevel = minStockLevel;
    }
}