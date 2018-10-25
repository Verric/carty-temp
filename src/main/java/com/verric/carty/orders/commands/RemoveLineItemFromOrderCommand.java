package com.verric.carty.orders.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class RemoveLineItemFromOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private String lineItemId;
}
