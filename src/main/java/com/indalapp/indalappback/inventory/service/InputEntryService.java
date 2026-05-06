package com.indalapp.indalappback.inventory.service;

import com.indalapp.indalappback.inventory.dto.InputEntryRequest;
import com.indalapp.indalappback.inventory.dto.InputEntryResponse;
import com.indalapp.indalappback.inventory.entity.InputEntry;
import com.indalapp.indalappback.inventory.entity.RawMaterial;
import com.indalapp.indalappback.inventory.repository.InputEntryRepository;
import com.indalapp.indalappback.inventory.repository.RawMaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InputEntryService {

    private static final double EPSILON = 0.000001;

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

    @Transactional
    public InputEntryResponse create(InputEntryRequest request) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(request.getRawMaterialId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Materia prima no encontrada"
                ));

        double totalCost = request.getQuantity() * request.getUnitCost();
        applyInventoryAdjustment(rawMaterial, request.getQuantity(), totalCost);
        rawMaterialRepository.save(rawMaterial);

        InputEntry entry = new InputEntry();
        entry.setRawMaterial(rawMaterial);
        entry.setSupplier(request.getSupplier());
        entry.setQuantity(request.getQuantity());
        entry.setUnitCost(request.getUnitCost());
        entry.setTotalCost(totalCost);
        entry.setEntryDate(LocalDateTime.now());

        InputEntry saved = inputEntryRepository.save(entry);

        return mapToResponse(saved);
    }

    @Transactional
    public InputEntryResponse update(Long id, InputEntryRequest request) {
        InputEntry entry = inputEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entrada no encontrada"
                ));

        RawMaterial previousRawMaterial = entry.getRawMaterial();
        RawMaterial newRawMaterial = rawMaterialRepository.findById(request.getRawMaterialId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Materia prima no encontrada"
                ));

        double previousTotalCost = entry.getTotalCost();
        double newTotalCost = request.getQuantity() * request.getUnitCost();

        if (previousRawMaterial.getId().equals(newRawMaterial.getId())) {
            applyInventoryAdjustment(
                    previousRawMaterial,
                    request.getQuantity() - entry.getQuantity(),
                    newTotalCost - previousTotalCost
            );
            rawMaterialRepository.save(previousRawMaterial);
            newRawMaterial = previousRawMaterial;
        } else {
            applyInventoryAdjustment(previousRawMaterial, -entry.getQuantity(), -previousTotalCost);
            rawMaterialRepository.save(previousRawMaterial);

            applyInventoryAdjustment(newRawMaterial, request.getQuantity(), newTotalCost);
            rawMaterialRepository.save(newRawMaterial);
        }

        entry.setRawMaterial(newRawMaterial);
        entry.setSupplier(request.getSupplier());
        entry.setQuantity(request.getQuantity());
        entry.setUnitCost(request.getUnitCost());
        entry.setTotalCost(newTotalCost);

        InputEntry updated = inputEntryRepository.save(entry);

        return mapToResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        InputEntry entry = inputEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entrada no encontrada"
                ));

        RawMaterial rawMaterial = entry.getRawMaterial();

        applyInventoryAdjustment(rawMaterial, -entry.getQuantity(), -entry.getTotalCost());
        rawMaterialRepository.save(rawMaterial);

        inputEntryRepository.delete(entry);
    }

    private void applyInventoryAdjustment(RawMaterial rawMaterial, double quantityDelta, double costDelta) {
        double newStock = rawMaterial.getStock() + quantityDelta;

        if (newStock < -EPSILON) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No hay suficiente stock de " + rawMaterial.getName() + " para ajustar la entrada"
            );
        }

        if (Math.abs(newStock) < EPSILON) {
            newStock = 0;
        }

        double newAvgCost;
        if (newStock == 0) {
            newAvgCost = 0;
        } else {
            newAvgCost = ((rawMaterial.getStock() * rawMaterial.getAvgCost()) + costDelta) / newStock;
        }

        if (newAvgCost < -EPSILON) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede ajustar el costo promedio de " + rawMaterial.getName()
            );
        }

        if (Math.abs(newAvgCost) < EPSILON) {
            newAvgCost = 0;
        }

        rawMaterial.setStock(newStock);
        rawMaterial.setAvgCost(newAvgCost);
    }

    private InputEntryResponse mapToResponse(InputEntry entry) {
        RawMaterial rawMaterial = entry.getRawMaterial();

        return new InputEntryResponse(
                entry.getId(),
                rawMaterial.getId(),
                rawMaterial.getName(),
                entry.getSupplier(),
                entry.getQuantity(),
                rawMaterial.getUnitMeasurement(),
                entry.getUnitCost(),
                entry.getTotalCost(),
                entry.getEntryDate()
        );
    }
}
