package com.verric.carty.orders.eventhandlers;

import com.verric.carty.orders.events.LineItemAddedToOrderEvent;
import com.verric.carty.orders.events.LineItemRemovedFromOrderEvent;
import com.verric.carty.orders.events.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    @EventHandler
    public void on(OrderCreatedEvent event) {
        System.out.println("EVENT: " + event.toString());
    }

    @EventHandler
    public void on(LineItemAddedToOrderEvent event) {
        System.out.println("EVENT: " + event.toString());
    }

    @EventHandler
    public void on(LineItemRemovedFromOrderEvent event) {
        System.out.println("EVENT: " + event.toString());
    }
}
