/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ch.sbb.dks.camundapoc.dynamodb.model.WagenEvent;
import ch.sbb.dks.camundapoc.mariadb.model.MariaWagenEvent;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component
public class EventConverter {

    public List<MariaWagenEvent> convert(List<WagenEvent> dynamoEvents) {
        return dynamoEvents.stream().map(this::convert).collect(Collectors.toList());
    }

    public MariaWagenEvent convert(WagenEvent dynamoEvent) {
        MariaWagenEvent mariaEvent = new MariaWagenEvent();
        mariaEvent.setWagennummer(dynamoEvent.getWagennummer());
        mariaEvent.setPartnernummer(dynamoEvent.getPartnernummer());
        mariaEvent.setUicLand(dynamoEvent.getUicLand());
        mariaEvent.setUicBahnhof(dynamoEvent.getUicBahnhof());
        mariaEvent.setLadestelle(dynamoEvent.getLadestelle());
        mariaEvent.setGleis(dynamoEvent.getGleis());
        mariaEvent.setFilialcode(dynamoEvent.getFilialcode());
        mariaEvent.setEventType(dynamoEvent.getEventType());
        mariaEvent.setEventTimestamp(dynamoEvent.getTimestamp());

        return mariaEvent;
    }
}
