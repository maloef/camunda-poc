/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component("clusterLookupDelegate")
public class ClusterLookupDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterLookupDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("looking up clusters");
    }
}
