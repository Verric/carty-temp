package com.verric.carty.orders.domain;

import lombok.*;
import org.axonframework.commandhandling.model.EntityId;

@Getter
@Setter
@EqualsAndHashCode(of="lineItemId")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
    @EntityId
    private String lineItemId;
    private Product product;
    private long quantity;
}
