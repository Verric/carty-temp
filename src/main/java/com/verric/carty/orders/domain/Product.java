package com.verric.carty.orders.domain;

import lombok.Value;

// Value object denoting a Product, note unlike ProductEntity this Product does not have an id,
// this is because in the context of an order a product it self does not have a lifecycle
@Value
public class Product {
    private final String name;
    private final long price;
    private final String description;
}
