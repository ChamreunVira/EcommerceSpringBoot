package com.kh.vira_dev.ecommerce.controller;

import com.kh.vira_dev.ecommerce.io.request.PageableRequest;
import com.kh.vira_dev.ecommerce.io.request.ProductRequest;
import com.kh.vira_dev.ecommerce.io.response.ProductResponse;
import com.kh.vira_dev.ecommerce.payload.ApiResponse;
import com.kh.vira_dev.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll(PageableRequest pageable) {
        List<ProductResponse> response = productService.getAll(pageable.toPageable());
        return ResponseEntity.ok().body(ApiResponse.success("Successfully to get all product." , response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Long id) {
        ProductResponse response = productService.getById(id);
        return ResponseEntity.ok().body(ApiResponse.success("Successfully to get product." , response));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<ProductResponse>> save(@Valid @ModelAttribute ProductRequest request){

        ProductResponse response = productService.create(request);

        return ResponseEntity.ok().body(ApiResponse.success("Product created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request){
        ProductResponse response = productService.update(id, request);
        return ResponseEntity.ok().body(ApiResponse.success("Product updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().body(ApiResponse.success("Product deleted successfully." , null));
    }

}
