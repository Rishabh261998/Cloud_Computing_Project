package com.cs551clc.ecommerce.service.data.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName="Inventory")
public class InventoryItem {

    @DynamoDBRangeKey(attributeName = "sortKey")
    private String sortKey;

    @DynamoDBAttribute
    private String img_link;

    @DynamoDBAttribute
    private BigDecimal price;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private String category;

    @DynamoDBAttribute
    private BigInteger qty_available;

    @DynamoDBHashKey(attributeName = "primaryKey")
    private String primaryKey;
}
