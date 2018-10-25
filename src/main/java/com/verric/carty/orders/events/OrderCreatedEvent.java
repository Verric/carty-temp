package com.verric.carty.orders.events;

import lombok.Value;

@Value
public class OrderCreatedEvent {
    private final String orderId;
    //private String orderName;
}
