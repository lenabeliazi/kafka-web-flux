package web.flux.demo.WebFluxApp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import web.flux.demo.WebFluxApp.constants.Constants;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = Constants.TOPIC_NAME,
            groupId = Constants.GROUP_ID)
    public void consume(String message){
        LOGGER.info(String.format("Message received -> %s", message));
    }
}
