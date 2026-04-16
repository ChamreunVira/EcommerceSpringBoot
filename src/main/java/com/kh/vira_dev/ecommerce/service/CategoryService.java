package com.kh.vira_dev.ecommerce.service;

import com.kh.vira_dev.ecommerce.io.request.CategoryRequest;
import com.kh.vira_dev.ecommerce.io.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

    CategoryResponse getById(Long id);

    List<CategoryResponse> getAll();
}
