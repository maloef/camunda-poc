package ch.sbb.dks.camundapoc;

import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ch.sbb.dks.camundapoc.model.WagenEvent;
import ch.sbb.dks.camundapoc.dynamodb.read.DynamoReader;

@SpringBootApplication
@EnableProcessApplication("camundapoc")
public class CamundaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamundaApplication.class);

    public static void main(String... args) {
        ApplicationContext applicationContext = SpringApplication.run(CamundaApplication.class, args);

        List<String> wagennummern = Arrays.asList("338593262375", "275544320854", "318046740417", "318567332610", "338135460362");

        DynamoReader dynamoReader = applicationContext.getBean(DynamoReader.class);
        List<WagenEvent> events = dynamoReader.findByWagennummern(wagennummern);

        LOGGER.info("found {} events for wagen {}", events.size(), wagennummern);
    }
}
