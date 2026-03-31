package com.kh.vira_dev.ecommerce.entity.composite;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CartItemId {

    private Long cartId;

    private Long productId;

}
