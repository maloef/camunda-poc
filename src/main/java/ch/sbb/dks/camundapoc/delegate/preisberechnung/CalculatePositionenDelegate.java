/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.preisberechnung;

import ch.sbb.dks.camundapoc.dynamodb.read.DynamoReader;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ue85191 (Markus Loeffler)
 * Wagenevent == Wagenbewegung
 */
@Component("calculate_wagen_positionen")
public class CalculatePositionenDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatePositionenDelegate.class);

    @Autowired
    private DynamoReader dynamoReader;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("Calculating Positionen ...");
    }
}
