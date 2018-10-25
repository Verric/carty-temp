package com.verric.carty.orders.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class AddLineItemToOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private long quantity;
    private String productName;
    private long productPrice;
    private String productDescription;
}


