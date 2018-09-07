/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.mariadb.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Entity
public class MariaWagenEvent {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String wagennummer;

    private String eventType;

    private String eventTimestamp;

    private String partnernummer;

    private String filialcode;

    private String uicLand;

    private String uicBahnhof;

    private String ladestelle;

    private String gleis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWagennummer() {
        return wagennummer;
    }

    public void setWagennummer(String wagennummer) {
        this.wagennummer = wagennummer;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
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
}
