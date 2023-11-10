package org.enseirb.it310.truckTracker;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// authors : Maxime Saland et Van Khôi Lê
@Component
public class Consumer implements ConsumerSeekAware {
    private final Logger log = LoggerFactory.getLogger(Application.class);
    private List<TruckPositionMessage> truckPositionMessages = new ArrayList<>();
    private final long kafkaSeekTs = Instant.now().minus(1, ChronoUnit.HOURS).toEpochMilli();

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        callback.seekToTimestamp(assignments.keySet(), kafkaSeekTs);
    }

    @KafkaListener(id = "msaland-0", topics = "trucks.position")
    public synchronized void received(TruckPositionMessage positionMessage, Acknowledgment ack) {
        log.info(positionMessage.toString());
        truckPositionMessages.add(positionMessage);
        ack.acknowledge();
    }

    public List<TruckPositionMessage> getPositions() {
        return truckPositionMessages;
    }

    public boolean checkTruckPresenceById(int id) {
        return truckPositionMessages.stream().anyMatch(p -> p.getTruckId() == id);
    }
}
