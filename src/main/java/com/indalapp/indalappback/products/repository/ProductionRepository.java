package com.indalapp.indalappback.products.repository;

import com.indalapp.indalappback.products.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionRepository extends JpaRepository<Production, Long> {
    List<Production> findAllByOrderByProductionDateDesc();
}