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
@Component("aktionsfaktorDelegate")
public class AktionsfaktorDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(AktionsfaktorDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("aktionsfaktor delegate");

        Integer einheiten = (Integer) delegateExecution.getVariable("einheiten");
        Integer faktorAktion = (Integer) delegateExecution.getVariable("faktorAktion");

        einheiten *= faktorAktion;

        delegateExecution.setVariable("einheiten", einheiten);
    }
}