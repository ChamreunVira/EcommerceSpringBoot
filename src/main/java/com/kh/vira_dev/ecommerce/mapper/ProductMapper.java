package com.kh.vira_dev.ecommerce.mapper;

import com.kh.vira_dev.ecommerce.entity.Product;
import com.kh.vira_dev.ecommerce.io.request.ProductRequest;
import com.kh.vira_dev.ecommerce.io.response.ProductResponse;

public interface ProductMapper {

    Product toEntity(ProductRequest request);

    ProductResponse toResponse(Product product);

    void updateFromRequest(Product product , ProductRequest request);

}
