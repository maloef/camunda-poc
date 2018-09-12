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
@Component("wagenfaktorDelegate")
public class WagenfaktorDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(WagenfaktorDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("wagenfaktor delegate");
        Integer faktorCluster = (Integer) delegateExecution.getVariable("faktorCluster");
        Integer dauerInMinuten = (Integer) delegateExecution.getVariable("dauerInMinuten");

        Integer einheiten = dauerInMinuten * faktorCluster;
        delegateExecution.setVariable("einheiten", einheiten);

    }
}
