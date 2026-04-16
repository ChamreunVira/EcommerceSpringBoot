package com.kh.vira_dev.ecommerce.entity.composite;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderLineId {

    private Long orderId;

    private Long productId;

}
