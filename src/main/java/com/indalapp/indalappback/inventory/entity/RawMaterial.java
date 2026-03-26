package com.indalapp.indalappback.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "raw_materials")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unitMeasurement;

    @Column(nullable = false)
    private Double stock;

    @Column(nullable = false)
    private Double avgCost;

    @Column(nullable = false)
    private Double minStockLevel;

    public RawMaterial() {
    }

    public RawMaterial(String name, String unitMeasurement, Double stock, Double avgCost, Double minStockLevel) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public void setAvgCost(Double avgCost) {
        this.avgCost = avgCost;
    }

    public void setMinStockLevel(Double minStockLevel) {
        this.minStockLevel = minStockLevel;
    }
}