package com.cs551clc.ecommerce.service.controller;

import com.cs551clc.ecommerce.service.data.dynamodb.Order;
import com.cs551clc.ecommerce.service.data.request.OrderRequest;
import com.cs551clc.ecommerce.service.service.OrderService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://23.20.222.173:3000", methods={RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET})
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByUserId(
            @RequestParam(name="userId") String userId
    ) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/order")
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest request) throws Exception {
        orderService.createOrder(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
