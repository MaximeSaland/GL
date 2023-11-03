package org.enseirb.it310.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Consumer {
    private final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final int limit = 10;
    ArrayList<Truck> lastMsg = new ArrayList();

    @KafkaListener(id = "msaland", topics = "msaland.json")
    public void received(Truck msg) {
        log.info("Message received {}", msg);
        if (lastMsg.size() == limit) { lastMsg.removeLast(); }
        lastMsg.addFirst(msg);
    }

    public ArrayList<Truck> getLast10Messages() {
        return lastMsg;
    }
}
