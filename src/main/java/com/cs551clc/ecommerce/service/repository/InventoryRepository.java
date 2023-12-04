package com.cs551clc.ecommerce.service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cs551clc.ecommerce.service.data.dynamodb.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<InventoryItem> getAllItems() {
        return dynamoDBMapper.scan(InventoryItem.class, new DynamoDBScanExpression());
    }

    public List<InventoryItem> getItemByProductKey(String productKey) {
        Map<String, AttributeValue> productKeyMap = new HashMap<>();
        productKeyMap.put(":productKey", new AttributeValue().withS(productKey));


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("primaryKey= :productKey").withExpressionAttributeValues(productKeyMap);

        return dynamoDBMapper.scan(InventoryItem.class, scanExpression);
    }

    public void saveItem(InventoryItem item) {
        dynamoDBMapper.save(item);
    }
}
