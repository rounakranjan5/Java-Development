package com.example.OrderService.controller;

import com.example.OrderService.config.UserClient;
import com.example.OrderService.dto.OrderResponse;
import com.example.OrderService.dto.User;
import com.example.OrderService.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private RestTemplate restTemplate;
    private UserClient userClient;


    public OrderController(RestTemplate restTemplate, UserClient userClient) {
        this.restTemplate = restTemplate;
        this.userClient = userClient;
    }

    private List<Order> orderList= Arrays.asList(

            new Order(1L,1L,"Washing Machine",54000.00),
            new Order(2L,2L,"Macbook M5",3500000.00),
            new Order(3L,3L,"Headphones",540.00)
    );


    @GetMapping
    public List<Order> getAllorders()
    {
        return  orderList;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderList.stream().filter(order->order.getId().equals(id))
                .findFirst().orElseThrow(()-> new RuntimeException("Order not found"));
    }

    // get order with user details
    @GetMapping("/{id}/with-user")
    public OrderResponse getOrderWithUser(@PathVariable Long id){
        Order order=getOrderById(id);

        if(order==null) return null;

        /*

        -- Rest Template ---

        String url="http://localhost:8081/api/user/"+order.getUserId();

        ResponseEntity<User>response=restTemplate.getForEntity(url, User.class);

        return new OrderResponse(order,response.getBody());

         */

        /*

        -- Open Feign

         */

        User user= userClient.getUserById(order.getUserId());

        return new OrderResponse(order,user);

    }

}
