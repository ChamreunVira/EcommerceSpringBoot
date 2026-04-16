package com.kh.vira_dev.ecommerce.mapper.impl;

import com.kh.vira_dev.ecommerce.entity.Category;
import com.kh.vira_dev.ecommerce.io.request.CategoryRequest;
import com.kh.vira_dev.ecommerce.io.response.CategoryResponse;
import com.kh.vira_dev.ecommerce.mapper.CategoryMapper;
import com.kh.vira_dev.ecommerce.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

    private final ProductMapper productMapper;

    @Override
    public Category toEntity(CategoryRequest request) {
        Category category = new Category();
        applyCategoryFields(category, request);
        return category;
    }

    @Override
    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .tags(category.getTags())
                .products(category.getProducts() == null ? List.of() : category.getProducts().stream()
                        .map(productMapper::toResponse)
                        .toList())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }


    @Override
    public List<CategoryResponse> toList(List<Category> category) {
        return category.stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void updateFromRequest(Category category, CategoryRequest request) {
        if(request == null) return;
        applyCategoryFields(category, request);
    }

    private void applyCategoryFields(Category category, CategoryRequest request) {
        category.setName(request.getName());
        category.setSlug(toSlug(request.getName()));
        category.setDescription(request.getDescription());
        category.setTags(request.getTags());
    }

    private String toSlug(String value) {
        return value.trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
