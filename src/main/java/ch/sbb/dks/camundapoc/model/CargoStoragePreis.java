/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * @author ue85191 (Markus Loeffler)
 */
@DynamoDBTable(tableName = "")
public class CargoStoragePreis {

    @DynamoDBHashKey(attributeName = "zkkNummer")
    private String zkkNummer;

    @DynamoDBRangeKey(attributeName = "wagennummer")
    private String wagennummer;

    private long preis;
    private String timestamp;

    public String getZkkNummer() {
        return zkkNummer;
    }

    public void setZkkNummer(String zkkNummer) {
        this.zkkNummer = zkkNummer;
    }

    public String getWagennummer() {
        return wagennummer;
    }

    public void setWagennummer(String wagennummer) {
        this.wagennummer = wagennummer;
    }

    public long getPreis() {
        return preis;
    }

    public void setPreis(long preis) {
        this.preis = preis;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
