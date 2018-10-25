package com.verric.carty.orders.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AddLineItemParams {
    @JsonProperty
    @NotBlank
    private String orderId;

    @JsonProperty
    @NotBlank
    private long productId;

    @JsonProperty
    @Min(0)
    @Max(10)
    private long quantity;
}