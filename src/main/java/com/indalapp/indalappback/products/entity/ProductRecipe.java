package com.indalapp.indalappback.products.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_recipe")
public class ProductRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double stock;

    @Column(nullable = false)
    private Double estimatedCost;

    @OneToMany(mappedBy = "productRecipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRecipeIngredient> ingredients = new ArrayList<>();

    public ProductRecipe() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getStock() {
        return stock;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public List<ProductRecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public void setIngredients(List<ProductRecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}