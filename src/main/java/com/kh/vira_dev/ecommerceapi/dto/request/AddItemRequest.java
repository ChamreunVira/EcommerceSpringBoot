package com.kh.vira_dev.ecommerceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddItemRequest {

    @NotBlank(message = "product is is required.")
    private Integer productId;

    @NotBlank(message = "quantity is required.")
    private Integer quantity;
}
