package com.cs551clc.ecommerce.service.data.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
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
    @DynamoDBHashKey(attributeName = "username")
    String username;

    @DynamoDBAutoGeneratedKey
    @DynamoDBRangeKey(attributeName = "orderId")
    String orderId;

    @DynamoDBAttribute
    List<String> productIdList;

    @DynamoDBAttribute
    List<BigInteger> productQtyList;
}
