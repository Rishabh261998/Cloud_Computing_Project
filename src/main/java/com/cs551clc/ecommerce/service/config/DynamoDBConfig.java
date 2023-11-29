package com.cs551clc.ecommerce.service.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;

@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDBMapper getDynamoDBMapper() {
        // Replace these values with the role ARN, external ID, and region
        String roleArn = "arn:aws:iam::868151963346:role/db_role";

        // Create an STS client
        AssumeRoleResponse assumeRoleResponse;
        try (StsClient stsClient = StsClient.builder().region(Region.US_EAST_1).build()) {

            // Assume the IAM role
            assumeRoleResponse = stsClient.assumeRole(AssumeRoleRequest.builder()
                    .roleArn(roleArn)
                    .roleSessionName("AssumedRoleSession")
                    .build());
        }

        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
                new BasicSessionCredentials(
                        assumeRoleResponse.credentials().accessKeyId(),
                        assumeRoleResponse.credentials().secretAccessKey(),
                        assumeRoleResponse.credentials().sessionToken()
                )
        );

        AmazonDynamoDB dbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(credentialsProvider)
                .build();

        return new DynamoDBMapper(dbClient);
    }
}

