/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.dynamodb.write;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import ch.sbb.dks.camundapoc.dynamodb.config.DynamoDBConfig;
import ch.sbb.dks.camundapoc.dynamodb.read.DynamoReader;
import ch.sbb.dks.camundapoc.model.CargoStoragePreis;
import ch.sbb.dks.camundapoc.model.WagenEvent;

/**
 * @author ue85191 (Markus Loeffler)
 */
public class DynamoWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoReader.class);

    private String tableName;

    private DynamoDB dynamoDB;
    private DynamoDBMapper dynamoDBMapper;

    public DynamoWriter(String tableName) {
        this.tableName = tableName;
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(DynamoDBConfig.REGION).build();
        DynamoDBMapperConfig.TableNameOverride tableNameOverride = DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName);
        DynamoDBMapperConfig config = new DynamoDBMapperConfig.Builder().withTableNameOverride(tableNameOverride).build();

        dynamoDB = new DynamoDB(client);
        dynamoDBMapper = new DynamoDBMapper(client, config);
    }

    public void write(CargoStoragePreis preis) {
        dynamoDBMapper.save(preis);
    }
}
