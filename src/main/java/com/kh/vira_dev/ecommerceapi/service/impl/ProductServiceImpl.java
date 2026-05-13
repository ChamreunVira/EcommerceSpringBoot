package com.kh.vira_dev.ecommerceapi.service.impl;

import com.kh.vira_dev.ecommerceapi.dto.request.ProductRequest;
import com.kh.vira_dev.ecommerceapi.dto.response.ProductResponse;
import com.kh.vira_dev.ecommerceapi.entity.Product;
import com.kh.vira_dev.ecommerceapi.exception.ResourceNotFoundException;
import com.kh.vira_dev.ecommerceapi.mapper.ProductMapper;
import com.kh.vira_dev.ecommerceapi.repository.ProductRepository;
import com.kh.vira_dev.ecommerceapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductResponseImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse create(ProductRequest request) {

        Product product = productMapper.toEntity(request);
        Product saved = productRepository.save(product);
        log.info("Product created: {}", saved);
        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponse update(int id, ProductRequest request) {
        Product product = findByOrThrow(id);
        productMapper.applyToProductFields(product , request);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    public void delete(int id) {
        Product product = findByOrThrow(id);
        productRepository.delete(product);
    }

    @Override
    public ProductResponse getById(int id) {
        Product product = findByOrThrow(id);
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        log.info("Product retrieved: {}", products);
        return productMapper.toResponseList((products));
    }

    private Product findByOrThrow(int id) {
        log.info("Finding product by id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product"));
    }
}
