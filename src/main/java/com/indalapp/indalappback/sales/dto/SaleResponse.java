package com.indalapp.indalappback.sales.dto;

import java.time.LocalDateTime;

public class SaleResponse {

    private Long id;
    private Long productRecipeId;
    private String productName;
    private String customerName;
    private Double quantitySold;
    private Double unitPrice;
    private Double totalPrice;
    private LocalDateTime saleDate;

    public SaleResponse() {
    }

    public SaleResponse(
            Long id,
            Long productRecipeId,
            String productName,
            String customerName,
            Double quantitySold,
            Double unitPrice,
            Double totalPrice,
            LocalDateTime saleDate
    ) {
        this.id = id;
        this.productRecipeId = productRecipeId;
        this.productName = productName;
        this.customerName = customerName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
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
}