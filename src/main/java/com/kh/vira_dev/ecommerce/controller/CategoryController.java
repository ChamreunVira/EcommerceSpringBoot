package com.kh.vira_dev.ecommerce.controller;

import com.kh.vira_dev.ecommerce.payload.ApiResponse;
import com.kh.vira_dev.ecommerce.io.request.CategoryRequest;
import com.kh.vira_dev.ecommerce.io.response.CategoryResponse;
import com.kh.vira_dev.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Long id) {
        CategoryResponse response = categoryService.getById(id);
        return ResponseEntity.ok().body(ApiResponse.success("Successfully to retrieve category." , response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {
        List<CategoryResponse> response = categoryService.getAll();
        return ResponseEntity.ok().body(ApiResponse.success("Successfully to retrieve all category." , response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> save(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.create(request);
        return ResponseEntity.ok().body(ApiResponse.success("Successfully to create category." , response));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.update(id, request);
        return ResponseEntity.ok().body(ApiResponse.success("Successfully to update category." , response));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().body(ApiResponse.success("Successfully to delete category." , null));
    }

}
