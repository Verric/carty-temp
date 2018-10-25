package com.verric.carty.orders.events;

import lombok.Value;

@Value
public class LineItemRemovedFromOrderEvent {
    private String orderId;
    private String lineItemId;
}
