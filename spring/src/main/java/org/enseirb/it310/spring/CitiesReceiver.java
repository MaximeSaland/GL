package org.enseirb.it310.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CitiesReceiver {
    private final Logger log = LoggerFactory.getLogger(CitiesReceiver.class);

    @KafkaListener(id = "saland-talence", topics = "cities")
    public void received(String city) {
        log.info("City received {}", city);
    }
}
