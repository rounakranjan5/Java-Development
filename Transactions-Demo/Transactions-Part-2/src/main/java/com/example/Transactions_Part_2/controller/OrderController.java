package com.example.Transactions_Part_2.controller;

import com.example.Transactions_Part_2.entity.Order;
import com.example.Transactions_Part_2.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        orderService.placeOrder(order);
        return ResponseEntity.ok("order placed...");
    }

}
