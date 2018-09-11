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
@Component("wagenevent")
public class WagenEventDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(WagenEventDelegate.class);

    @Autowired
    private DynamoReader dynamoReader;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String zkkNummer = (String)delegateExecution.getVariable("zkkNummer");
        LOGGER.info("zkkNummer:" + zkkNummer, delegateExecution);
        // Todo find By
        List<String> wagennummern = Arrays.asList("338593262375", "275544320854", "318046740417", "318567332610", "338135460362");
        //List<WagenEvent> events = dynamoReader.findByZkkNummer(zkkNummer);
        List<WagenEvent> events = dynamoReader.findByWagennummern(wagennummern);
        delegateExecution.setVariable("wagenEvents", events);
    }
}