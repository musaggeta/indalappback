package com.indalapp.indalappback.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InputEntryRequest {

    @NotNull(message = "La materia prima es obligatoria")
    private Long rawMaterialId;

    @NotBlank(message = "El proveedor es obligatorio")
    private String supplier;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Double quantity;

    @NotNull(message = "El costo unitario es obligatorio")
    @Positive(message = "El costo unitario debe ser mayor a 0")
    private Double unitCost;

    public InputEntryRequest() {
    }

    public Long getRawMaterialId() {
        return rawMaterialId;
    }

    public String getSupplier() {
        return supplier;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setRawMaterialId(Long rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }
}