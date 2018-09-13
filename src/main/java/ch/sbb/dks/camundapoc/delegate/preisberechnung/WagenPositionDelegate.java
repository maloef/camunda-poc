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
@Component("wagenPositionDelegate")
public class WagenPositionDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(WagenPositionDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("WagenPosition wird aus Blöcken zusammengesetzt");

        List<Wagenbewegungsblock> bloecke = (List<Wagenbewegungsblock>) delegateExecution.getVariable("wagenbewegungsbloecke");
        WagenPosition wagenPosition = new WagenPosition(bloecke);

        delegateExecution.setVariable("wagenPosition", wagenPosition);

    }
}
