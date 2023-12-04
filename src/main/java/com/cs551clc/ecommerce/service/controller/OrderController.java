package com.cs551clc.ecommerce.service.controller;

import com.cs551clc.ecommerce.service.data.dynamodb.Order;
import com.cs551clc.ecommerce.service.service.OrderService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin(origins = "http://23.20.222.173:3000", methods={RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET})
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByUserId(
            @RequestParam(name="userId") String userId
    ) throws Exception {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }
}
