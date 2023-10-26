package org.enseirb.it310.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		TruckClient tc = new TruckClient();
		Truck t = tc.getById(1);
		System.out.println("immat: " + t.getImmatriculation());
	}
}
