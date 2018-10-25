package com.verric.carty.orders.controller;

import com.verric.carty.orders.commands.AddLineItemToOrderCommand;
import com.verric.carty.orders.commands.CreateOrderCommand;
import com.verric.carty.orders.commands.RemoveLineItemFromOrderCommand;
import com.verric.carty.product.ProductEntity;
import com.verric.carty.product.ProductRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final ProductRepository productRepository;
    private final CommandGateway gateway;

    @Autowired
    public OrderController(ProductRepository productRepository, CommandGateway gateway) {
        this.productRepository = productRepository;
        this.gateway = gateway;
    }

    @PostMapping("/create-order")
    public void createOrder() {
        this.gateway.send(new CreateOrderCommand());
    }

    @PostMapping("/add-line-item")
    public ResponseEntity<Void> addLineItemToOrder(@RequestBody final AddLineItemParams params) {
        ProductEntity product = this.productRepository.findById(params.getProductId()).orElseThrow(RuntimeException::new);
        this.gateway.send(new AddLineItemToOrderCommand(
                params.getOrderId(),
                params.getQuantity(),
                product.getName(),
                product.getPrice(),
                product.getDescription()
        ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove-line-item")
    public ResponseEntity<Void> removeLineItemFromOrder(@RequestBody final RemoveLineItemParams params) {
        this.gateway.send(new RemoveLineItemFromOrderCommand(params.getOrderId(), params.getLineItemId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
