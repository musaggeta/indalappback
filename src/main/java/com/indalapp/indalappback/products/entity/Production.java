package com.indalapp.indalappback.products.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "productions")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_recipe_id")
    private ProductRecipe productRecipe;

    @Column(nullable = false)
    private Double quantityProduced;

    @Column(nullable = false)
    private Double totalCost;

    @Column(nullable = false)
    private LocalDateTime productionDate;

    public Production() {
    }

    public Long getId() {
        return id;
    }

    public ProductRecipe getProductRecipe() {
        return productRecipe;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductRecipe(ProductRecipe productRecipe) {
        this.productRecipe = productRecipe;
    }

    public void setQuantityProduced(Double quantityProduced) {
        this.quantityProduced = quantityProduced;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void setProductionDate(LocalDateTime productionDate) {
        this.productionDate = productionDate;
    }
}