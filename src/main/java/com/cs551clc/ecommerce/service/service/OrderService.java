package com.cs551clc.ecommerce.service.service;

import com.cs551clc.ecommerce.service.data.dynamodb.Order;
import com.cs551clc.ecommerce.service.repository.OrderRepository;
import com.cs551clc.ecommerce.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public List<Order> getOrdersByUserId(String userName) {
        if(userRepository.getUserById(userName).isEmpty())
            throw new IllegalArgumentException("Username does not exist!");
        List<Order> orders = orderRepository.getOrderByUserId(userName);
        if(orders.isEmpty())
            throw new NoSuchElementException("Order List is Empty!");
        return orders;
    }
}
