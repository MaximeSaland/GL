package org.enseirb.it310.truckTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

// authors : Maxime Saland et Van Khôi Lê
@Service
public class MapService {
    @Autowired
    WebClient webClient;

    public String getMapURL(Position position) {
        return webClient.post()
                .uri("/map")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(position)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
