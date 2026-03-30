package com.indalapp.indalappback.products.repository;

import com.indalapp.indalappback.products.entity.ProductRecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRecipeIngredientRepository extends JpaRepository<ProductRecipeIngredient, Long> {
}