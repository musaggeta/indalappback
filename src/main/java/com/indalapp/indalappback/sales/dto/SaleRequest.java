package com.indalapp.indalappback.sales.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SaleRequest {

    @NotNull(message = "El producto es obligatorio")
    private Long productRecipeId;

    @NotBlank(message = "El cliente es obligatorio")
    private String customerName;

    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.0001", message = "La cantidad debe ser mayor a 0")
    private Double quantitySold;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0001", message = "El precio unitario debe ser mayor a 0")
    private Double unitPrice;

    public SaleRequest() {
    }

    public Long getProductRecipeId() {
        return productRecipeId;
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

    public void setProductRecipeId(Long productRecipeId) {
        this.productRecipeId = productRecipeId;
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
}