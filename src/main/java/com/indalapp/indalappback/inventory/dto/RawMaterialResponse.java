package com.indalapp.indalappback.inventory.dto;

public class RawMaterialResponse {

    private Long id;
    private String name;
    private String unitMeasurement;
    private Double stock;
    private Double avgCost;
    private Double minStockLevel;

    public RawMaterialResponse() {
    }

    public RawMaterialResponse(Long id, String name, String unitMeasurement, Double stock, Double avgCost, Double minStockLevel) {
        this.id = id;
        this.name = name;
        this.unitMeasurement = unitMeasurement;
        this.stock = stock;
        this.avgCost = avgCost;
        this.minStockLevel = minStockLevel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public Double getStock() {
        return stock;
    }

    public Double getAvgCost() {
        return avgCost;
    }

    public Double getMinStockLevel() {
        return minStockLevel;
    }
}