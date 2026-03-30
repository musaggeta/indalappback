package com.indalapp.indalappback.products.entity;

import com.indalapp.indalappback.inventory.entity.RawMaterial;

import jakarta.persistence.*;

@Entity
@Table(name = "product_recipe_ingredients")
public class ProductRecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_recipe_id")
    private ProductRecipe productRecipe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "raw_material_id")
    private RawMaterial rawMaterial;

    @Column(nullable = false)
    private Double quantity;

    public ProductRecipeIngredient() {
    }

    public Long getId() {
        return id;
    }

    public ProductRecipe getProductRecipe() {
        return productRecipe;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductRecipe(ProductRecipe productRecipe) {
        this.productRecipe = productRecipe;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}