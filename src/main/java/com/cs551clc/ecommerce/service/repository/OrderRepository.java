package com.cs551clc.ecommerce.service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cs551clc.ecommerce.service.data.dynamodb.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void saveOrder(Order order) {
        dynamoDBMapper.save(order, new DynamoDBSaveExpression());
    }

    public List<Order> getOrderByUserId(String userName) {
        Map<String, AttributeValue> userIdMap = new HashMap<>();
        userIdMap.put(":userName", new AttributeValue().withS(userName));


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("username= :userName").withExpressionAttributeValues(userIdMap);

        return dynamoDBMapper.scan(Order.class, scanExpression);
    }
}
