package com.verric.carty.orders.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class VoidLineItemCommand {
    @TargetAggregateIdentifier
    private long orderId;
    private long lineItemId;
}
