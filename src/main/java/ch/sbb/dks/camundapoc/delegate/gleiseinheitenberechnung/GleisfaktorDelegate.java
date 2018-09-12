/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.gleiseinheitenberechnung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

        Integer dauerInMinuten = (Integer) delegateExecution.getVariable("dauerInMinuten");
        Double einheiten = dauerInMinuten * gleisfaktor;

        delegateExecution.setVariable("einheiten", einheiten);
    }
}
