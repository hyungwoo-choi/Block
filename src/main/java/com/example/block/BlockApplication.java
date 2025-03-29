package com.example.block;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockApplication.class, args);
	}

}
