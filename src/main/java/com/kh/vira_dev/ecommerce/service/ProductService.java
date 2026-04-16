package com.kh.vira_dev.ecommerce.service;

import com.kh.vira_dev.ecommerce.io.request.ProductRequest;
import com.kh.vira_dev.ecommerce.io.response.CategoryResponse;
import com.kh.vira_dev.ecommerce.io.response.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id , ProductRequest request);

    void delete(Long id);

    List<ProductResponse> getAll(Pageable pageable);

    ProductResponse getById(Long id);

    CategoryResponse getByCategoryId(Long id);

}
