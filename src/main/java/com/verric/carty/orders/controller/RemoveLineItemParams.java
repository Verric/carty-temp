package com.verric.carty.orders.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class RemoveLineItemParams {

    @JsonProperty
    @NotBlank
    private final String orderId;

    @JsonProperty
    @NotBlank
    private final String lineItemId;

}
