package org.enseirb.it310.truckTracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class AlertService {
    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    WebClient webClient;

    @Autowired
    Producer producer;

    private boolean checkAlert(Integer position) {
        return position > 0;
    }

    @Async
    public void checkAndPublish(TruckPositionMessage position) {
        log.info("Asking alert level for {}", position);
        // code appel ws detection alerte
        Integer alertFlag = webClient.post()
            .uri("/position/check")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(position)
            .retrieve()
            .bodyToMono(Integer.class)
            .block();

        if (checkAlert(alertFlag)) {
            log.info("ALERT : Truck " + position.getTruckId() + " is on alert level " + alertFlag);
            producer.sendMessage(position);
        }
    }
}
