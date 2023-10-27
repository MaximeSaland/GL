package org.enseirb.it310.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

// Authors : Van Khôi LE et Maxime SALAND
@Component
public class TruckClient {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    WebClient truckClient;
    public static final int TRUCK_ID = 1;

    public Truck getById(int id) {
        return truckClient.get().uri("/truck/{id}", id)
                .retrieve()
                .bodyToMono(Truck.class)
                .block();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Truck truck = getById(TRUCK_ID);
            log.info("ID: " + TRUCK_ID);
            log.info("Immat: " + truck.getImmatriculation());
        };
    }
}
