/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ue85191 (Markus Loeffler)
 */
public class WagenEventDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(WagenEventDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("executing {}", delegateExecution);
    }
}
