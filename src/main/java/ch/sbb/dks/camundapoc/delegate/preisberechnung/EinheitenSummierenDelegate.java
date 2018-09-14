/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.preisberechnung;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.sbb.dks.camundapoc.dynamodb.write.DynamoWriter;
import ch.sbb.dks.camundapoc.model.CargoStoragePreis;
import ch.sbb.dks.camundapoc.model.WagenPosition;
import ch.sbb.dks.camundapoc.model.Wagenbewegungsblock;

/**
 * @author ue85191 (Markus Loeffler)
 */
@Component("einheitenSummierenDelegate")
public class EinheitenSummierenDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(EinheitenSummierenDelegate.class);

    @Autowired
    private DynamoWriter dynamoWriter;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        WagenPosition wagenPosition = (WagenPosition) delegateExecution.getVariable("wagenPosition");

        long sum = wagenPosition.getBloecke().stream().collect(Collectors.summingLong(Wagenbewegungsblock::getEinheiten));

        String zkkNummer = (String) delegateExecution.getVariable("zkkNummer");
        String wagennummer = wagenPosition.getBloecke().get(0).getStartEvent().getWagennummer();
        writeSum(zkkNummer, wagennummer, sum);

        wagenPosition.setEinheiten(sum);
        delegateExecution.setVariable("wagenPosition", wagenPosition);

//        LOGGER.info("Old scholl sum: {}, streams sum: {}", sum, sum1);
    }

    private void writeSum(String zkkNummer, String wagennummer, long sum) {
        CargoStoragePreis preis = new CargoStoragePreis();
        preis.setPreis(sum);
        preis.setZkkNummer(zkkNummer);
        preis.setWagennummer(wagennummer);
        preis.setTimestamp(createTimestamp());
        dynamoWriter.write(preis);
    }

    private String createTimestamp() {
        return LocalDateTime.now().toString();
    }
}
