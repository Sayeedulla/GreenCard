package com.mindtree.greencard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.mindtree.greencard")
@SpringBootApplication
public class GreenCardApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GreenCardApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GreenCardApplication.class);
	}
}
