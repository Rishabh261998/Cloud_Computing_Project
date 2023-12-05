package com.cs551clc.ecommerce.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AwsStsCredentialRefresher {

    @Autowired
    private DynamoDBConfig dynamoDBConfig;

    // Schedule the refresh task every 15 minutes
    @Scheduled(fixedRate = 14* 60 * 1000)
    public void refreshCredentials() {
        dynamoDBConfig.getDynamoDBMapper();
    }
}

