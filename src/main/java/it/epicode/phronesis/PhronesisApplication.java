package it.epicode.phronesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PhronesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhronesisApplication.class, args);
	}

}
