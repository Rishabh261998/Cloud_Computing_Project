package com.cs551clc.ecommerce.service.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
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
    @DynamoDBAttribute
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

    @DynamoDBHashKey
    private String primaryKey;
}
