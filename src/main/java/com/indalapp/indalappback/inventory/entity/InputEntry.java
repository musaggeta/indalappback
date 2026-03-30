package com.indalapp.indalappback.inventory.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "input_entries")
public class InputEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "raw_material_id")
    private RawMaterial rawMaterial;

    @Column(nullable = false)
    private String supplier;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double unitCost;

    @Column(nullable = false)
    private Double totalCost;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    public InputEntry() {
    }

    public Long getId() {
        return id;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
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

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }
}