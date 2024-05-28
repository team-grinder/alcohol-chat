package com.alcoholchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@SpringBootApplication
public class AlcoholChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlcoholChatApplication.class, args);
	}

}
