package com.indalapp.indalappback.products.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class ProductRecipeRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Valid
    @NotEmpty(message = "La receta no puede estar vacía")
    private List<ProductRecipeIngredientRequest> ingredients;

    public ProductRecipeRequest() {
    }

    public String getName() {
        return name;
    }

    public List<ProductRecipeIngredientRequest> getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<ProductRecipeIngredientRequest> ingredients) {
        this.ingredients = ingredients;
    }
}