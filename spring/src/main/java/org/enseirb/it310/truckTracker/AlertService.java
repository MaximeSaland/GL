package org.enseirb.it310.truckTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AlertService {
    @Autowired
    WebClient webClient;

    @Async
    public void checkAndPublish(TruckPositionMessage truckPositionMessage) {
        log.info("Asking alert level for {}", truckPositionMessage);
        // code appel ws détection alerte
    }
}
