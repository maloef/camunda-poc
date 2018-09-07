/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.mariadb.write;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.sbb.dks.camundapoc.convert.EventConverter;
import ch.sbb.dks.camundapoc.dynamodb.model.WagenEvent;
import ch.sbb.dks.camundapoc.mariadb.model.MariaWagenEvent;
import ch.sbb.dks.camundapoc.mariadb.repository.MariaWagenEventRepository;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component
public class MariaWriter {

    private final MariaWagenEventRepository repo;
    private final EventConverter converter;

    @Autowired
    public MariaWriter(MariaWagenEventRepository repo, EventConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    public void write(List<WagenEvent> dynamoEvents) {
        List<MariaWagenEvent> mariaEvents = converter.convert(dynamoEvents);
        repo.saveAll(mariaEvents);
    }

    public void write(WagenEvent dynamoEvent) {
        MariaWagenEvent mariaEvent = converter.convert(dynamoEvent);

//        MariaWagenEvent event = new MariaWagenEvent();
//
//        event.setEventType("L1");
//        event.setPartnernummer("450155");
//        event.setFilialcode("43");
//        event.setLadestelle("Ladestelle A");
//        event.setGleis("Gleis 9");
//        event.setUicLand("85");
//        event.setUicBahnhof("12345");
//        event.setWagennummer("888888888888");
//        event.setEventTimestamp(LocalDateTime.now().toString());

        repo.save(mariaEvent);
    }

}
