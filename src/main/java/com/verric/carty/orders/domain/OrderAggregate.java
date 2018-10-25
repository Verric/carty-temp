package com.verric.carty.orders.domain;


import com.verric.carty.orders.commands.AddLineItemToOrderCommand;
import com.verric.carty.orders.commands.CreateOrderCommand;
import com.verric.carty.orders.commands.RemoveLineItemFromOrderCommand;
import com.verric.carty.orders.events.LineItemAddedToOrderEvent;
import com.verric.carty.orders.events.LineItemRemovedFromOrderEvent;
import com.verric.carty.orders.events.OrderCreatedEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@EqualsAndHashCode(of="orderId")
@ToString
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    @AggregateMember
    private List<LineItem> lineItems;

    // For Axon
    protected OrderAggregate() {}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand cmd) {
        apply(new OrderCreatedEvent(UUID.randomUUID().toString()));
    }

    @CommandHandler
    public void addLineItemToOrder(AddLineItemToOrderCommand cmd) {
        apply(new LineItemAddedToOrderEvent(
                this.orderId,
                UUID.randomUUID().toString(),
                cmd.getQuantity(),
                cmd.getProductName(),
                cmd.getProductPrice(),
                cmd.getProductDescription()));
    }

    @CommandHandler
    public void removeLineItemFromOrder(RemoveLineItemFromOrderCommand cmd) {
        apply(new LineItemRemovedFromOrderEvent(cmd.getOrderId(), cmd.getLineItemId()));
    }

    @EventSourcingHandler
    protected void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.lineItems = new ArrayList<>();
    }

    @EventSourcingHandler
    protected void on(LineItemAddedToOrderEvent event) {
        Product product = new Product(event.getProductName(), event.getProductPrice(), event.getProductDescription());
        LineItem lineItem = new LineItem(event.getLineItemId(), product, event.getQuantity());
        this.lineItems.add(lineItem);
    }

    @EventSourcingHandler
    protected void on(LineItemRemovedFromOrderEvent event) {
        this.lineItems.removeIf(li -> li.getLineItemId().equals(event.getLineItemId()));
    }
}
