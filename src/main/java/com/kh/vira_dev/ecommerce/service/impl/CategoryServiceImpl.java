package com.kh.vira_dev.ecommerce.service.impl;

import com.kh.vira_dev.ecommerce.entity.Category;
import com.kh.vira_dev.ecommerce.exception.ResourceNotFoundException;
import com.kh.vira_dev.ecommerce.io.request.CategoryRequest;
import com.kh.vira_dev.ecommerce.io.response.CategoryResponse;
import com.kh.vira_dev.ecommerce.mapper.CategoryMapper;
import com.kh.vira_dev.ecommerce.repository.CategoryRepository;
import com.kh.vira_dev.ecommerce.service.CategoryService;
import com.kh.vira_dev.ecommerce.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final FileUploadService fileUploadService;

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = findByOrThrow(id);
        categoryMapper.updateFromRequest(category, request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = findByOrThrow(id);
        category.getProducts().forEach(product -> fileUploadService.delete(product.getPicture()));
        log.info("Delete category: {}", category);
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = findByOrThrow(id);
        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toList(categories);
    }

    private Category findByOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
    }
}
