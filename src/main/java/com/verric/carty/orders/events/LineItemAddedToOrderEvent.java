package com.verric.carty.orders.events;

import lombok.Value;

@Value
public class LineItemAddedToOrderEvent {
    private String orderId;
    private String lineItemId;
    private long quantity;
    private String productName;
    private long productPrice;
    private String productDescription;
}
