package org.enseirb.it310.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TruckClientConfig {
    public static final String TRUCK_URL = "http:localhost:8080";

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(TRUCK_URL).build();
    }
}
