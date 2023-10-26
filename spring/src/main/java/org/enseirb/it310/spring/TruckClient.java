package org.enseirb.it310.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TruckClient {
    @Autowired
    WebClient truckClient;
    public static final int TRUCK_ID = 1;

    public Truck getById(int id) {
        return truckClient.get().uri("/truck/{id}", id)
                .retrieve()
                .bodyToMono(Truck.class)
                .block();
    }
}
