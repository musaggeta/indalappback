package com.indalapp.indalappback.inventory.dto;

import java.time.LocalDateTime;

public class InputEntryResponse {

    private Long id;
    private Long rawMaterialId;
    private String rawMaterialName;
    private String supplier;
    private Double quantity;
    private String unitMeasurement;
    private Double unitCost;
    private Double totalCost;
    private LocalDateTime entryDate;

    public InputEntryResponse() {
    }

    public InputEntryResponse(
            Long id,
            Long rawMaterialId,
            String rawMaterialName,
            String supplier,
            Double quantity,
            String unitMeasurement,
            Double unitCost,
            Double totalCost,
            LocalDateTime entryDate
    ) {
        this.id = id;
        this.rawMaterialId = rawMaterialId;
        this.rawMaterialName = rawMaterialName;
        this.supplier = supplier;
        this.quantity = quantity;
        this.unitMeasurement = unitMeasurement;
        this.unitCost = unitCost;
        this.totalCost = totalCost;
        this.entryDate = entryDate;
    }

    public Long getId() {
        return id;
    }

    public Long getRawMaterialId() {
        return rawMaterialId;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public String getSupplier() {
        return supplier;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }
}