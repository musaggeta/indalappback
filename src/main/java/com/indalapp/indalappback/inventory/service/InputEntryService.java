package com.indalapp.indalappback.inventory.service;

import com.indalapp.indalappback.inventory.dto.InputEntryRequest;
import com.indalapp.indalappback.inventory.dto.InputEntryResponse;
import com.indalapp.indalappback.inventory.entity.InputEntry;
import com.indalapp.indalappback.inventory.entity.RawMaterial;
import com.indalapp.indalappback.inventory.repository.InputEntryRepository;
import com.indalapp.indalappback.inventory.repository.RawMaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InputEntryService {

    private final InputEntryRepository inputEntryRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public InputEntryService(
            InputEntryRepository inputEntryRepository,
            RawMaterialRepository rawMaterialRepository
    ) {
        this.inputEntryRepository = inputEntryRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<InputEntryResponse> getAll() {
        return inputEntryRepository.findAllByOrderByEntryDateDesc()
                .stream()
                .map(entry -> new InputEntryResponse(
                        entry.getId(),
                        entry.getRawMaterial().getId(),
                        entry.getRawMaterial().getName(),
                        entry.getSupplier(),
                        entry.getQuantity(),
                        entry.getRawMaterial().getUnitMeasurement(),
                        entry.getUnitCost(),
                        entry.getTotalCost(),
                        entry.getEntryDate()
                ))
                .toList();
    }

    public InputEntryResponse create(InputEntryRequest request) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(request.getRawMaterialId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Materia prima no encontrada"
                ));

        double totalCost = request.getQuantity() * request.getUnitCost();

        double currentStock = rawMaterial.getStock();
        double currentAvgCost = rawMaterial.getAvgCost();

        double newStock = currentStock + request.getQuantity();

        double newAvgCost;
        if (newStock == 0) {
            newAvgCost = 0;
        } else {
            newAvgCost = ((currentStock * currentAvgCost) + totalCost) / newStock;
        }

        rawMaterial.setStock(newStock);
        rawMaterial.setAvgCost(newAvgCost);
        rawMaterialRepository.save(rawMaterial);

        InputEntry entry = new InputEntry();
        entry.setRawMaterial(rawMaterial);
        entry.setSupplier(request.getSupplier());
        entry.setQuantity(request.getQuantity());
        entry.setUnitCost(request.getUnitCost());
        entry.setTotalCost(totalCost);
        entry.setEntryDate(LocalDateTime.now());

        InputEntry saved = inputEntryRepository.save(entry);

        return new InputEntryResponse(
                saved.getId(),
                rawMaterial.getId(),
                rawMaterial.getName(),
                saved.getSupplier(),
                saved.getQuantity(),
                rawMaterial.getUnitMeasurement(),
                saved.getUnitCost(),
                saved.getTotalCost(),
                saved.getEntryDate()
        );
    }
}