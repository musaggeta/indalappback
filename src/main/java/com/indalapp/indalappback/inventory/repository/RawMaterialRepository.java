package com.indalapp.indalappback.inventory.repository;

import com.indalapp.indalappback.inventory.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}