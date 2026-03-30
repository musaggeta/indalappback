package com.indalapp.indalappback.products.repository;

import com.indalapp.indalappback.products.entity.ProductRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRecipeRepository extends JpaRepository<ProductRecipe, Long> {
}