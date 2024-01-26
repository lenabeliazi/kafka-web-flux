package web.flux.kafka.demo.WebFluxKafkaApp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import web.flux.kafka.demo.WebFluxKafkaApp.constants.Constants;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        kafkaTemplate.send(Constants.TOPIC_NAME, message);
    }

}
