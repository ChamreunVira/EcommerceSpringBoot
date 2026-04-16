package com.kh.vira_dev.ecommerce.mapper;

import com.kh.vira_dev.ecommerce.entity.Category;
import com.kh.vira_dev.ecommerce.io.request.CategoryRequest;
import com.kh.vira_dev.ecommerce.io.response.CategoryResponse;

import java.util.List;

public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toList(List<Category> category);

    void updateFromRequest(Category category , CategoryRequest request);

}
