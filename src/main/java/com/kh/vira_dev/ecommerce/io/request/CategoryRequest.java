package com.kh.vira_dev.ecommerce.io.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Name is required.")
    @Size(min = 2 , max = 30 , message = "Name must be between 2 and 30 characters.")
    private String name;

    @NotBlank(message = "Description is required.")
    @Size(min = 2, max = 50 , message = "Description must be between 2 and 50 characters.")
    private String description;

    @NotNull(message = "Tags is required.")
    private List<String> tags;

//    @Valid
//    private List<ProductRequest> products;

}
