/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.dynamodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import ch.sbb.dks.camundapoc.dynamodb.read.DynamoReader;

/**
 * Configuration class for DynamoDB. This class creates a DynamoDBMapper bean which is used to perform CRUD operations. The DynamoDB
 * instance can be a local test instance on a developer machine, or a real AWS instance.<p/>
 *
 * See the class ch.sbb.dks.cargostorage.local.CreateDynamoDBTable in the test folder if you want to create a local instance.
 *
 * @author ue85191 (Markus Loeffler)
 */
@Configuration
public class DynamoDBConfig {

    public static final String REGION = "eu-central-1";
    public static final String LOCAL_INSTANCE_ENDPOINT = "http://localhost:8000";

    @Value("${dynamodb.localInstance}")
    private boolean localInstance;

    @Value("${dynamodb.table}")
    private String table;

    @Bean
    public DynamoDBMapper getDynamoDBMapper() {
        AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();
        if (localInstance) {
            clientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(LOCAL_INSTANCE_ENDPOINT, REGION));
        } else {
            clientBuilder.withRegion(REGION);
        }
        AmazonDynamoDB client = clientBuilder.build();

        DynamoDBMapperConfig.TableNameOverride tableNameOverride = DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(table);
        DynamoDBMapperConfig config = new DynamoDBMapperConfig.Builder().withTableNameOverride(tableNameOverride).build();

        DynamoDBMapper mapper = new DynamoDBMapper(client, config);
        return mapper;
    }

    @Bean
    public DynamoReader getDynamoReader() {
        DynamoReader dynamoReader = new DynamoReader(table);

        return dynamoReader;
    }
}
