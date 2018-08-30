package com.mindtree.greencard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.mindtree.greencard")
@SpringBootApplication
public class GreenCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenCardApplication.class, args);
	}

}
