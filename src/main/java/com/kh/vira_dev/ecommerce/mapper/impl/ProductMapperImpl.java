package com.kh.vira_dev.ecommerce.mapper.impl;

import com.kh.vira_dev.ecommerce.constant.DiscountType;
import com.kh.vira_dev.ecommerce.entity.Category;
import com.kh.vira_dev.ecommerce.entity.Product;
import com.kh.vira_dev.ecommerce.exception.ResourceNotFoundException;
import com.kh.vira_dev.ecommerce.io.request.ProductRequest;
import com.kh.vira_dev.ecommerce.io.response.ProductResponse;
import com.kh.vira_dev.ecommerce.mapper.ProductMapper;
import com.kh.vira_dev.ecommerce.repository.CategoryRepository;
import com.kh.vira_dev.ecommerce.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {

    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        Category category = findByOrThrow(request.getCategoryId());
        product.setCategory(category);
        applyProductFields(product , request);
        return product;
    }

    public void updateFromRequest(Product product, ProductRequest request) {
        if(request.getCategoryId() != null) {
            Category category = findByOrThrow(request.getCategoryId());
            product.setCategory(category);
        }
        applyProductFields(product, request);
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .title(product.getTitle())
                .description(product.getDescription())
                .summary(product.getSummary())
                .price(product.getPrice())
                .picture(product.getPicture())
                .discountType(product.getDiscountType().name())
                .discountValue(product.getDiscountValue())
                .tags(product.getTags())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private void applyProductFields(Product product , ProductRequest request) {
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setSummary(request.getSummary());
        product.setPrice(request.getPrice());
        applyProductPicture(product , request.getPicture() , true);
        product.setDiscountType(DiscountType.valueOf(request.getDiscountType()));
        product.setDiscountValue(request.getDiscountValue());
        product.setTags(request.getTags());
    }

    private Category findByOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
    }

    private void applyProductPicture(Product product, MultipartFile picture , boolean pictureRequired) {
        if(picture != null && !picture.isEmpty()) {
            String existingPicture = product.getPicture();
            String uploadedPicture = fileUploadService.upload(picture);
            product.setPicture(uploadedPicture);
            //if exists delete it
            if(existingPicture != null && !existingPicture.isEmpty() && !existingPicture.equals(uploadedPicture)) {
                fileUploadService.delete(existingPicture);
            }
            return;
        }

        //make sure data picture inside DB is not blank/empty
        if(pictureRequired && (product.getPicture() == null || picture.isEmpty())) {
            throw new IllegalArgumentException("Picture is required for new product.");
        }
    }
}
