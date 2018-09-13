/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.einheitenberechnung;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.sbb.dks.camundapoc.model.Wagenbewegungsblock;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component("wagenfaktorDelegate")
public class WagenfaktorDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(WagenfaktorDelegate.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("wagenfaktor delegate");

        Wagenbewegungsblock block = (Wagenbewegungsblock) delegateExecution.getVariable("wagenbewegungsblock");

        String cluster = block.getCluster();

        Integer faktorCluster = faktorCluster(cluster);
        int dauerInMinuten = minutesBetween(block.getStartEvent().getTimestamp(), block.getEndEvent().getTimestamp());
        block.setDauerInMinuten(dauerInMinuten);

        Integer einheiten = dauerInMinuten * faktorCluster;
        block.setEinheitenWagen(einheiten);
    }

    int minutesBetween(String fromString, String toString) {
        LocalDateTime from = LocalDateTime.parse(fromString, formatter);
        LocalDateTime to = LocalDateTime.parse(toString, formatter);

        int minutes = (int) Duration.between(from, to).toMinutes();
        return minutes;
    }

    private Integer faktorCluster(String cluster) {
        if ("cluster1".equals(cluster)) {
            return 1;
        }
        if ("cluster2".equals(cluster)) {
            return 2;
        }
        return 3;
    }


}
