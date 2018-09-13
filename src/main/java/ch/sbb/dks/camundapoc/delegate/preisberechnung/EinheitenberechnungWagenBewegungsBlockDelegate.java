/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.preisberechnung;

import ch.sbb.dks.camundapoc.dynamodb.read.DynamoReader;
import ch.sbb.dks.camundapoc.model.WagenEvent;
import ch.sbb.dks.camundapoc.model.Wagenbewegungsblock;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ue85191 (Markus Loeffler)
 * Wagenevent == Wagenbewegung
 */
@Component("einheitenberechnung_wagenbewegungsblock")
public class EinheitenberechnungWagenBewegungsBlockDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(EinheitenberechnungWagenBewegungsBlockDelegate.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    private DynamoReader dynamoReader;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<WagenEvent> eventsFromContext = (List<WagenEvent>) delegateExecution.getVariable("wagenEvents");
        List<WagenEvent> events = new ArrayList<>(eventsFromContext);
        LOGGER.info("first timestamp unsorted events: {}", events.get(0).getTimestamp());
        sortEvents(events);
        LOGGER.info("first timestamp sorted events: {}", events.get(0).getTimestamp());
        LOGGER.info("Calculating Einheiten ...");

        List<Wagenbewegungsblock> bloecke = new ArrayList<>();
        boolean useDummyBloecke = true;
        if (useDummyBloecke) {
            bloecke.add(createBlock(events.get(0), events.get(1), "Eanos"));
            bloecke.add(createBlock(events.get(1), events.get(2), "Fas"));
            delegateExecution.setVariable("wagenbewegungsbloecke", bloecke);
        } else {
            Map<String, List<WagenEvent>> wagennummernToEvents = splitWagenEvents(events);
            for (String wagennummer : wagennummernToEvents.keySet()) {
                List<WagenEvent> wagenEvents = wagennummernToEvents.get(wagennummer);
                for (int i = 0; i < wagenEvents.size() - 1; i++) {
                    String wagentyp = i % 2 == 0 ? "Eanos" : "Fas";
                    bloecke.add(createBlock(events.get(i), events.get(i + 1), wagentyp));
                }
//                WagenEvent last = wagenEvents.get(wagenEvents.size() - 1);
            }
            delegateExecution.setVariable("wagenbewegungsbloecke", bloecke);
        }
    }

    private Wagenbewegungsblock createBlock(WagenEvent e1, WagenEvent e2, String wagentyp) {
        Wagenbewegungsblock block = new Wagenbewegungsblock(e1, e2);
        block.setWagentyp(wagentyp);
        return block;
    }

    private void sortEvents(List<WagenEvent> events) {
        Collections.sort(events, new Comparator<WagenEvent>() {
            @Override
            public int compare(WagenEvent o1, WagenEvent o2) {
                LocalDateTime first = LocalDateTime.parse(o1.getTimestamp(), formatter);
                LocalDateTime second = LocalDateTime.parse(o2.getTimestamp(), formatter);
                if (first.isBefore(second)) {
                    return -1;
                }
                if (first.isAfter(second)) {
                    return 1;
                }
                return 0;
            }
        });
    }

    private Map<String, List<WagenEvent>> splitWagenEvents(List<WagenEvent> events) {
        HashMap<String, List<WagenEvent>> wagennummernToEvents = new HashMap<>();
        for (WagenEvent event : events) {
            String nummer = event.getWagennummer();
            if (wagennummernToEvents.containsKey(nummer)) {
                wagennummernToEvents.get(nummer).add(event);
            } else {
                List<WagenEvent> newEvents = new ArrayList<>();
                newEvents.add(event);
                wagennummernToEvents.put(nummer, newEvents);
            }
        }
        return wagennummernToEvents;
    }

    private String lookupCluster(String wagennummer) {
        return null;
    }
}
