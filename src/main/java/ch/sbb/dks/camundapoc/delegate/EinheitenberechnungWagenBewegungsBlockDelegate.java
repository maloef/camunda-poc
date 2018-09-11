/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate;

import ch.sbb.dks.camundapoc.dynamodb.read.DynamoReader;
import ch.sbb.dks.camundapoc.model.WagenEvent;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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
        LOGGER.info("Calculating Einheiten ...");
    }
}
