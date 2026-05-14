package com.kh.vira_dev.ecommerceapi.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateItemRequest {

    private short itemId;

    private Integer quantity;

}
