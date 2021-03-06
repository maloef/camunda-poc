/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.preisberechnung;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.sbb.dks.camundapoc.model.WagenPosition;
import ch.sbb.dks.camundapoc.model.Wagenbewegungsblock;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component("calculateEinheitenDelegate")
public class CalculateEinheitenDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateEinheitenDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("calculating einheiten");

        WagenPosition wagenPosition = (WagenPosition) delegateExecution.getVariable("wagenPosition");
        LOGGER.info("blöcke: {}", wagenPosition.getBloecke().size());
    }
}
