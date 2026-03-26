package com.indalapp.indalappback.inventory.service;

import com.indalapp.indalappback.inventory.dto.RawMaterialRequest;
import com.indalapp.indalappback.inventory.dto.RawMaterialResponse;
import com.indalapp.indalappback.inventory.entity.RawMaterial;
import com.indalapp.indalappback.inventory.repository.RawMaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    public RawMaterialService(RawMaterialRepository rawMaterialRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<RawMaterialResponse> getAll() {
        return rawMaterialRepository.findAll()
                .stream()
                .map(rm -> new RawMaterialResponse(
                        rm.getId(),
                        rm.getName(),
                        rm.getUnitMeasurement(),
                        rm.getStock(),
                        rm.getAvgCost(),
                        rm.getMinStockLevel()
                ))
                .toList();
    }

    public RawMaterialResponse create(RawMaterialRequest request) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setName(request.getName());
        rawMaterial.setUnitMeasurement(request.getUnitMeasurement());
        rawMaterial.setStock(0.0);
        rawMaterial.setAvgCost(0.0);
        rawMaterial.setMinStockLevel(request.getMinStockLevel());

        RawMaterial saved = rawMaterialRepository.save(rawMaterial);

        return new RawMaterialResponse(
                saved.getId(),
                saved.getName(),
                saved.getUnitMeasurement(),
                saved.getStock(),
                saved.getAvgCost(),
                saved.getMinStockLevel()
        );
    }

    public RawMaterialResponse update(Long id, RawMaterialRequest request) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Materia prima no encontrada"));

        rawMaterial.setName(request.getName());
        rawMaterial.setUnitMeasurement(request.getUnitMeasurement());
        rawMaterial.setMinStockLevel(request.getMinStockLevel());

        RawMaterial updated = rawMaterialRepository.save(rawMaterial);

        return new RawMaterialResponse(
                updated.getId(),
                updated.getName(),
                updated.getUnitMeasurement(),
                updated.getStock(),
                updated.getAvgCost(),
                updated.getMinStockLevel()
        );
    }

    public void delete(Long id) {
        if (!rawMaterialRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Materia prima no encontrada");
        }

        rawMaterialRepository.deleteById(id);
    }
}