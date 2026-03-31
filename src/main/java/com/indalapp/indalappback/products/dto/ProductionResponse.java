package com.indalapp.indalappback.products.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductionResponse {

    private Long id;
    private Long productRecipeId;
    private String productName;
    private Double quantityProduced;
    private Double totalCost;
    private LocalDateTime productionDate;
    private List<String> materialsUsed;

    public ProductionResponse() {
    }

    public ProductionResponse(
            Long id,
            Long productRecipeId,
            String productName,
            Double quantityProduced,
            Double totalCost,
            LocalDateTime productionDate,
            List<String> materialsUsed
    ) {
        this.id = id;
        this.productRecipeId = productRecipeId;
        this.productName = productName;
        this.quantityProduced = quantityProduced;
        this.totalCost = totalCost;
        this.productionDate = productionDate;
        this.materialsUsed = materialsUsed;
    }

    public Long getId() {
        return id;
    }

    public Long getProductRecipeId() {
        return productRecipeId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getQuantityProduced() {
        return quantityProduced;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public List<String> getMaterialsUsed() {
        return materialsUsed;
    }
}