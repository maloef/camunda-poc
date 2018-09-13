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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ue85191 (Markus Loeffler)
 * Wagenevent == Wagenbewegung
 */
@Component("einheitenberechnung_wagenbewegungsblock")
public class EinheitenberechnungWagenBewegungsBlockDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(EinheitenberechnungWagenBewegungsBlockDelegate.class);

    @Autowired
    private DynamoReader dynamoReader;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<WagenEvent> events = (List<WagenEvent>) delegateExecution.getVariable("wagenEvents");
        LOGGER.info("Calculating Einheiten ...");

        List<Wagenbewegungsblock> bloecke = new ArrayList<Wagenbewegungsblock>();
//        Map<String, List<WagenEvent>> wagennummernToEvents = splitWagenEvents();
//        for (String wagennummer : wagennummernToEvents.keySet()) {
//            List<WagenEvent> wagenEvents = wagennummernToEvents.get(wagennummer);
//            for (int i = 0; i < wagenEvents.size() - 1; i++) {
//                bloecke.add(new Wagenbewegungsblock(wagenEvents.get(i), wagenEvents.get(i + 1)));
//            }
//            Wagenbewegungsblock wagenbewegungsblock = new Wagenbewegungsblock(wagenEvents.get(wagenEvents.size() - 1), null);
//        }
        bloecke.add(new Wagenbewegungsblock(events.get(0), events.get(1)));
        bloecke.get(0).setWagentyp("Eanos");
        bloecke.add(new Wagenbewegungsblock(events.get(1), events.get(2)));
        bloecke.get(1).setWagentyp("Fas");
        delegateExecution.setVariable("wagenbewegungsbloecke", bloecke);

    }

    private Map<String, List<WagenEvent>> splitWagenEvents() {
        return null;
    }

    private String lookupCluster(String wagennummer) {
        return null;
    }
}
