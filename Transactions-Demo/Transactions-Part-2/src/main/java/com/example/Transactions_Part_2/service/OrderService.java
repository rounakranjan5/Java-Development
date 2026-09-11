package com.example.Transactions_Part_2.service;

import com.example.Transactions_Part_2.entity.Order;
import com.example.Transactions_Part_2.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    OrderRepository orderRepository;
    PaymentAuditService paymentAuditService;

    @Transactional
    public void placeOrder(Order order){
        orderRepository.save(order);
        paymentAuditService.audit(order.getAmount(),order.getId(),order.getProductName());
    }

}
