package com.indalapp.indalappback.sales.entity;

import com.indalapp.indalappback.products.entity.ProductRecipe;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_recipe_id")
    private ProductRecipe productRecipe;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Double quantitySold;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public ProductRecipe getProductRecipe() {
        return productRecipe;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getQuantitySold() {
        return quantitySold;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductRecipe(ProductRecipe productRecipe) {
        this.productRecipe = productRecipe;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setQuantitySold(Double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
}