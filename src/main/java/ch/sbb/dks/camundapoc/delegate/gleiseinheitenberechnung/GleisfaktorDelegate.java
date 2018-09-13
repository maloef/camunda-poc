/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.gleiseinheitenberechnung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.sbb.dks.camundapoc.model.Wagenbewegungsblock;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component("gleisfaktorDelegate")
public class GleisfaktorDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(GleisfaktorDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("einheiten mal gleisfaktor");

        Double gleisfaktor = (Double) delegateExecution.getVariable("gleisfaktor");
        LOGGER.info("gleisfaktor: {}", gleisfaktor);

        Wagenbewegungsblock wagenbewegungsblock = (Wagenbewegungsblock) delegateExecution.getVariable("wagenbewegungsblock");
        long dauerInMinuten = wagenbewegungsblock.getDauerInMinuten();
        long einheitenGleis = dauerInMinuten * gleisfaktor.intValue();

        wagenbewegungsblock.setEinheitenGleis(einheitenGleis);

//        delegateExecution.setVariable("einheiten", einheiten);
    }
}
