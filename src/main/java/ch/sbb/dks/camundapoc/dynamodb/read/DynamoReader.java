/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.dynamodb.read;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import ch.sbb.dks.camundapoc.dynamodb.config.DynamoDBConfig;
import ch.sbb.dks.camundapoc.dynamodb.model.WagenEvent;


/**
 * @author ue85191 (Markus Loeffler)
 */
public class DynamoReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoReader.class);

    private String tableName;

    private DynamoDB dynamoDB;
    private DynamoDBMapper dynamoDBMapper;

    public DynamoReader(String tableName) {
        this.tableName = tableName;
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(DynamoDBConfig.REGION).build();
        DynamoDBMapperConfig.TableNameOverride tableNameOverride = DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName);
        DynamoDBMapperConfig config = new DynamoDBMapperConfig.Builder().withTableNameOverride(tableNameOverride).build();

        dynamoDB = new DynamoDB(client);
        dynamoDBMapper = new DynamoDBMapper(client, config);
    }

    private long count() {
        TableDescription tableDescription = dynamoDB.getTable(tableName).describe();
        return tableDescription.getItemCount();
    }

//    private List<WagenEvent> findRecords(String partnernummer, int howMany) {
//        Index index = dynamoDB.getTable(tableName).getIndex("partnernummer-messageReceivedTimestamp-index");
//
//        QuerySpec spec = new QuerySpec()
//                .withKeyConditionExpression("partnernummer = :p")
//                .withValueMap(new ValueMap().withString(":p",partnernummer));
//
//        ItemCollection<QueryOutcome> items = index.query(spec);
//        LOGGER.info("items with partnernummer {}: {}", partnernummer, items.getAccumulatedItemCount());
//        Iterator<Item> iter = items.iterator();
//
//        List<WagenEvent> events = new ArrayList<>(howMany);
//        while (iter.hasNext()) {
//            Item next = iter.next();
//            LOGGER.info("next item: {}", items);
//        }
//        return events;
//    }

    public List<WagenEvent> findByWagennummern(List<String> wagennummern) {
        List<WagenEvent> events = new ArrayList<>();
        wagennummern.stream().map(this::findByWagennummer).forEach(events::addAll);
        return events;
    }

    public List<WagenEvent> findByWagennummer(String wagennummer) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":w", new AttributeValue().withS(wagennummer));

        DynamoDBQueryExpression<WagenEvent> queryExpression = new DynamoDBQueryExpression<WagenEvent>()
                .withKeyConditionExpression("UICWaggonNumber = :w")
                .withExpressionAttributeValues(eav);

        List<WagenEvent> events = dynamoDBMapper.query(WagenEvent.class, queryExpression);

        return events;
    }

    public void write(WagenEvent event) {
        dynamoDBMapper.save(event);
    }

//    public void deleteAllBefore(double timestamp) {
//        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression();
//        dynamoDBMapper.delete();
//    }


}
