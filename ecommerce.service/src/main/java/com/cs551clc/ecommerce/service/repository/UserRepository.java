package com.cs551clc.ecommerce.service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cs551clc.ecommerce.service.data.dynamodb.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void saveUser(User user) {
        dynamoDBMapper.save(user, new DynamoDBSaveExpression());
    }

    public List<User> getUserById(String userName) {
        Map<String, AttributeValue> idMap = new HashMap<>();
        idMap.put(":userName", new AttributeValue().withS(userName));


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("username= :userName").withExpressionAttributeValues(idMap);

        return dynamoDBMapper.scan(User.class, scanExpression);
    }

    public List<User> getUserByEmail(String emailId) {
        Map<String, AttributeValue> emailMap = new HashMap<>();
        emailMap.put(":emailId", new AttributeValue().withS(emailId));


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("email= :emailId").withExpressionAttributeValues(emailMap);

        return dynamoDBMapper.scan(User.class, scanExpression);
    }

}
