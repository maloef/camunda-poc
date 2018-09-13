/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.einheitenberechnung;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component("langsteherfaktorDelegate")
public class LangsteherfaktorDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(LangsteherfaktorDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer langsteherfaktor = (Integer) delegateExecution.getVariable("langsteherfaktor");
        LOGGER.info("langsteherfaktor: {}", langsteherfaktor);
        if (langsteherfaktor == null) {
            langsteherfaktor = 1;
            delegateExecution.setVariable("langsteherfaktor", 1);
        }

        Long einheiten = (Long) delegateExecution.getVariable("einheiten");
        einheiten *= langsteherfaktor;

        delegateExecution.setVariable("einheiten", einheiten);
    }
}
