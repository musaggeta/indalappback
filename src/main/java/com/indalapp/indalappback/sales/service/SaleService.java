package com.indalapp.indalappback.sales.service;

import com.indalapp.indalappback.products.entity.ProductRecipe;
import com.indalapp.indalappback.products.repository.ProductRecipeRepository;
import com.indalapp.indalappback.sales.dto.SaleRequest;
import com.indalapp.indalappback.sales.dto.SaleResponse;
import com.indalapp.indalappback.sales.entity.Sale;
import com.indalapp.indalappback.sales.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRecipeRepository productRecipeRepository;

    public SaleService(
            SaleRepository saleRepository,
            ProductRecipeRepository productRecipeRepository
    ) {
        this.saleRepository = saleRepository;
        this.productRecipeRepository = productRecipeRepository;
    }

    public List<SaleResponse> getAll() {
        return saleRepository.findAllByOrderBySaleDateDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SaleResponse create(SaleRequest request) {
        ProductRecipe product = productRecipeRepository.findById(request.getProductRecipeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto no encontrado"
                ));

        if (product.getStock() < request.getQuantitySold()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No hay suficiente stock de " + product.getName()
            );
        }

        product.setStock(product.getStock() - request.getQuantitySold());
        productRecipeRepository.save(product);

        double totalPrice = request.getQuantitySold() * request.getUnitPrice();

        Sale sale = new Sale();
        sale.setProductRecipe(product);
        sale.setCustomerName(request.getCustomerName());
        sale.setQuantitySold(request.getQuantitySold());
        sale.setUnitPrice(request.getUnitPrice());
        sale.setTotalPrice(totalPrice);
        sale.setSaleDate(LocalDateTime.now());

        Sale saved = saleRepository.save(sale);

        return mapToResponse(saved);
    }

    private SaleResponse mapToResponse(Sale sale) {
        return new SaleResponse(
                sale.getId(),
                sale.getProductRecipe().getId(),
                sale.getProductRecipe().getName(),
                sale.getCustomerName(),
                sale.getQuantitySold(),
                sale.getUnitPrice(),
                sale.getTotalPrice(),
                sale.getSaleDate()
        );
    }
}