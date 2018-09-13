/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.preisberechnung;

import ch.sbb.dks.camundapoc.model.WagenPosition;
import ch.sbb.dks.camundapoc.model.Wagenbewegungsblock;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component("clusterLookupDelegate")
public class ClusterLookupDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterLookupDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("looking up clusters");
        String cluster = (String) delegateExecution.getVariable("cluster");
        Wagenbewegungsblock wagenbewegungsblock = (Wagenbewegungsblock) delegateExecution.getVariable("wagenbewegungsblock");
        wagenbewegungsblock.setCluster(cluster);

        List<Wagenbewegungsblock> wagenbewegungsbloeckeList = (List<Wagenbewegungsblock>) delegateExecution.getVariable("wagenbewegungsbloecke");
        Integer loopCounter = (Integer) delegateExecution.getVariable("loopCounter");
        wagenbewegungsbloeckeList.set(loopCounter, wagenbewegungsblock);

//        WagenPosition wagenPosition = (WagenPosition) delegateExecution.getVariable("wagenPosition");
        WagenPosition wagenPosition = new WagenPosition(wagenbewegungsbloeckeList);

        delegateExecution.setVariable("wagenbewegungsbloecke", wagenbewegungsbloeckeList);
        delegateExecution.setVariable("wagenPosition", wagenPosition);

    }
}
