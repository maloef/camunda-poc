/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.preisberechnung;

import java.util.stream.Collectors;

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
@Component("einheitenSummierenDelegate")
public class EinheitenSummierenDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(EinheitenSummierenDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        WagenPosition wagenPosition = (WagenPosition) delegateExecution.getVariable("wagenPosition");

        int sum = wagenPosition.getBloecke().stream().collect(Collectors.summingInt(Wagenbewegungsblock::getEinheiten));
//        for (Wagenbewegungsblock block : wagenPosition.getBloecke()) {
//            sum += block.getEinheiten();
//        }

        wagenPosition.setEinheiten(sum);
        delegateExecution.setVariable("wagenPosition", wagenPosition);

//        wagenPosition.getBloecke().stream().map(Wagenbewegungsblock::getEinheiten).mapToInt(Integer::intValue).sum();

//        LOGGER.info("Old scholl sum: {}, streams sum: {}", sum, sum1);
    }
}
