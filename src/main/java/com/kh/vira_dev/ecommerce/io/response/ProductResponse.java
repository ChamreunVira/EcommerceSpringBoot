package com.kh.vira_dev.ecommerce.io.response;

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
public class ProductResponse {

    private Long id;

    private Long categoryId;

    private String categoryName;

    private String title;

    private String picture;

    private String summary;

    private String description;

    private Float price;

    private String discountType;

    private Float discountValue;

    private List<String> tags;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
