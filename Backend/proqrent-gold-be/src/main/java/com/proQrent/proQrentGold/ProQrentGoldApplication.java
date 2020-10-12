package com.proQrent.proQrentGold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProQrentGoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProQrentGoldApplication.class, args);
	}

}
