package com.kh.vira_dev.ecommerce.io.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotNull(message = "Category Id is required.")
    private Long categoryId;

    @NotBlank(message = "Title is required.")
    private String title;

    private MultipartFile picture;

    @NotBlank(message = "Summary is required.")
    private String summary;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Price is required.")
    private Float price;

    @NotBlank(message = "Discount Type is required.")
    private String discountType;

    @NotNull(message = "Discount Value is required.")
    private Float discountValue;

    @NotNull(message = "Tags is required.")
    private List<String> tags;
}
