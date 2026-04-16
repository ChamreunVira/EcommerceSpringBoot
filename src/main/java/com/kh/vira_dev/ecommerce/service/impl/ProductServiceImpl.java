package com.kh.vira_dev.ecommerce.service.impl;

import com.kh.vira_dev.ecommerce.entity.Product;
import com.kh.vira_dev.ecommerce.exception.ResourceNotFoundException;
import com.kh.vira_dev.ecommerce.io.request.ProductRequest;
import com.kh.vira_dev.ecommerce.io.response.CategoryResponse;
import com.kh.vira_dev.ecommerce.io.response.ProductResponse;
import com.kh.vira_dev.ecommerce.mapper.CategoryMapper;
import com.kh.vira_dev.ecommerce.mapper.impl.ProductMapperImpl;
import com.kh.vira_dev.ecommerce.repository.ProductRepository;
import com.kh.vira_dev.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapperImpl productMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product saved =productRepository.save(product);
        log.info("Saved product {}", saved);
        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findByOrThrow(id);
        productMapper.updateFromRequest(product , request);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Product product = findByOrThrow(id);
        log.info("Delete product with id:{}", id);
        productRepository.delete(product);
    }

    @Override
    public List<ProductResponse> getAll(Pageable pageable) {
        Page<Product> pages = productRepository.findAll(pageable);
        log.info("Retrieved {} products", pages.getTotalElements());
        return pages.stream()
                .map(productMapper::toResponse)
                .toList();

    }
    @Override
    public ProductResponse getById(Long id) {
        Product product = findByOrThrow(id);
        log.info("Retrieved product with id:{}", id);
        return productMapper
                .toResponse(product);
    }

    @Deprecated(since = "2026-04-16")
    @Override
    public CategoryResponse getByCategoryId(Long id) {
        return null;
    }

    private Product findByOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }
}
