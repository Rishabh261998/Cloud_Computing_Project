package com.cs551clc.ecommerce.service.data.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName="Orders")
public class Order {
    @DynamoDBHashKey
    String username;

    @DynamoDBIndexHashKey(attributeName = "orderId", globalSecondaryIndexName = "orderId")
    String orderId;

    @DynamoDBAttribute
    List<String> productIdList;

    @DynamoDBAttribute
    List<BigInteger> productQtyList;
}
