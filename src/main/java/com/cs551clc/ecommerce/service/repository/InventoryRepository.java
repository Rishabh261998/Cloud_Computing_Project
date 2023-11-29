package com.cs551clc.ecommerce.service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.cs551clc.ecommerce.service.data.dynamodb.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<InventoryItem> getAllItems() {
        return dynamoDBMapper.scan(InventoryItem.class, new DynamoDBScanExpression());
    }
}
