/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.model;

import java.time.Instant;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Represents an event about which NWM informs us via an MQ message. The MQ message is converted to a WagenEvent and stored in DynamoDB. This table uses a composite primary key.
 * It consists of:
 *
 * <ul>
 * <li>
 * Partition key (aka hash key): wagennummer
 * </li>
 * <li>
 * Sort key (aka range key): eventType_eventSource_eventTimestamp - As the primary key must identify a record, this is a concatenation of three fields.
 * </li>
 * </ul>
 *
 * The tableName attribute is left empty here because we want to use different table names for different environments (e.g. EventStoreCargo, EventStoreCargo_Test,
 * EventStoreCargo_Dev). The table name is set at runtime in the class {@link ch.sbb.dks.cargostorage.config.DynamoDBConfig}.
 *
 * @author ue85191 (Markus Loeffler)
 */
@DynamoDBTable(tableName = "")
public class WagenEvent {

    private static final String SEPARATOR = "_";
    private static final String EVENT_SOURCE = "nwm";

    @DynamoDBHashKey(attributeName = "UICWaggonNumber")
    private String wagennummer;

    @DynamoDBIgnore
    private String eventType;
    @DynamoDBIgnore
    private String timestamp;

    private String partnernummer;

    private String filialcode;

    private String uicLand;

    private String uicBahnhof;

    private String ladestelle;

    private String gleis;

    private double messageReceivedTimestamp = Instant.now().toEpochMilli() / 1000.0;

    @DynamoDBRangeKey(attributeName = "EventType_EventSource_EventTimestamp")
    public String getEventTypeEventSourceEventTimestamp() {
        return eventType + SEPARATOR + EVENT_SOURCE + SEPARATOR + timestamp;
    }

    public void setEventTypeEventSourceEventTimestamp(String eventTypeEventSourceEventTimestamp) {
        String[] parts = eventTypeEventSourceEventTimestamp.split(SEPARATOR);
        if (parts.length != 3) {
            throw new IllegalArgumentException("EventType_EventSource_EventTimestamp must contain 2 underscores - value was: " + eventTypeEventSourceEventTimestamp);
        }
        if (!EVENT_SOURCE.equals(parts[1])) {
            throw new IllegalArgumentException("second part of argument must be " + EVENT_SOURCE + " - argument was: " +  eventTypeEventSourceEventTimestamp);
        }
        eventType = parts[0];
        timestamp = parts[2];
    }

    public void setWagennummer(String wagennummer) {
        this.wagennummer = wagennummer;
    }

    public String getWagennummer() {
        return wagennummer;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPartnernummer() {
        return partnernummer;
    }

    public void setPartnernummer(String partnernummer) {
        this.partnernummer = partnernummer;
    }

    public String getFilialcode() {
        return filialcode;
    }

    public void setFilialcode(String filialcode) {
        this.filialcode = filialcode;
    }

    public String getUicLand() {
        return uicLand;
    }

    public void setUicLand(String uicLand) {
        this.uicLand = uicLand;
    }

    public String getUicBahnhof() {
        return uicBahnhof;
    }

    public void setUicBahnhof(String uicBahnhof) {
        this.uicBahnhof = uicBahnhof;
    }

    public String getLadestelle() {
        return ladestelle;
    }

    public void setLadestelle(String ladestelle) {
        this.ladestelle = ladestelle;
    }

    public String getGleis() {
        return gleis;
    }

    public void setGleis(String gleis) {
        this.gleis = gleis;
    }

    public double getMessageReceivedTimestamp() {
        return messageReceivedTimestamp;
    }

    public void setMessageReceivedTimestamp(double messageReceivedTimestamp) {
        this.messageReceivedTimestamp = messageReceivedTimestamp;
    }
}
