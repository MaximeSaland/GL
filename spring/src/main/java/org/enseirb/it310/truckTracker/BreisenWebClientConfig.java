package org.enseirb.it310.truckTracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// authors : Maxime Saland et Van Khôi Lê
@Configuration
public class BreisenWebClientConfig {
    private final String BREISEN_URL = "http://breisen.datamix.ovh:8080";

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(BREISEN_URL).build();
    }
}
