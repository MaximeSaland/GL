package org.enseirb.it310.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class Producer {
    public static final String TOPIC = "msaland.json";
    private final Logger log = LoggerFactory.getLogger(Producer.class);

    @Autowired
    KafkaTemplate<String, Truck> kafkaTemplate;

/*
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationStarted() {
        log.info("Application starting");
        kafkaTemplate.send(TOPIC, "(￣▽￣)/ " + Instant.now());
    }
*/

    public void sendMessage(Truck msg) {
        log.info("msg: " + msg);
        kafkaTemplate.send(TOPIC, msg);
    }
}
