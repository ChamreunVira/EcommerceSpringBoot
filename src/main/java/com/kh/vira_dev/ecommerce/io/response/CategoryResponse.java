package com.kh.vira_dev.ecommerce.io.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;

    private String slug;

    private String name;

    private String description;

    private List<String> tags;

    @JsonProperty("products")
    List<ProductResponse> products;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
