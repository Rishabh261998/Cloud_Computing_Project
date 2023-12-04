package com.cs551clc.ecommerce.service.service;

import com.cs551clc.ecommerce.service.data.request.OrderRequest;
import com.cs551clc.ecommerce.service.mapper.OrderRequestMapper;
import com.cs551clc.ecommerce.service.utils.exception.ProductUnavailableException;
import com.cs551clc.ecommerce.service.data.dynamodb.InventoryItem;
import com.cs551clc.ecommerce.service.data.dynamodb.Order;
import com.cs551clc.ecommerce.service.repository.OrderRepository;
import com.cs551clc.ecommerce.service.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    private OrderRequestMapper mapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventoryService inventoryService;

    public List<Order> getOrdersByUserId(String userName) {
        if(userRepository.getUserById(userName).isEmpty())
            throw new IllegalArgumentException("Username does not exist!");
        List<Order> orders = orderRepository.getOrderByUserId(userName);
        if(orders.isEmpty())
            throw new NoSuchElementException("Order List is Empty!");
        return orders;
    }

    public void createOrder(OrderRequest request) throws Exception{
        if(userRepository.getUserById(request.getUsername()).isEmpty())
            throw new BadRequestException("Username does not exist!");
        List<String> productIds = request.getProductIdList();
        List<BigInteger> quantities = request.getProductQtyList();
        if(productIds.size()!= quantities.size())
            throw new BadRequestException("Product and Quantity List have size mismatch!");

        //Validate qtys are available//
        Map<String,BigInteger> productMap=getUnavailableProducts(productIds,quantities);
        if(!productMap.isEmpty())
        {
            throw new ProductUnavailableException(productMap);
        }

        updateItems(productIds,quantities);

        //Save order to repository//
        Order order = mapper.mapOrder(request);
        orderRepository.saveOrder(order);
    }

    private Map<String,BigInteger> getUnavailableProducts(List<String> productIds, List<BigInteger> quantities) throws Exception {
        Map<String,BigInteger> productMap=new HashMap<>();

        for(int i=0;i< productIds.size();i++)
        {
            String productId= productIds.get(i);
            BigInteger qty = quantities.get(i);
            InventoryItem item = inventoryService.getItemById(productId);
            if(item.getQty_available().compareTo(qty)<0) {
                productMap.put(productId, qty);
            }
        }
        return productMap;
    }

    //Function for decreasing each product by given qty in the order//
    private void updateItems(List<String> productIds, List<BigInteger> quantities) throws Exception {
        for(int i=0;i< productIds.size();i++)
        {
            String productId= productIds.get(i);
            BigInteger qty = quantities.get(i);
            inventoryService.transactItem(productId,qty);
        }
    }
}
