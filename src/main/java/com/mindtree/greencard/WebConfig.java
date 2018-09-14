package com.mindtree.greencard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mindtree.greencard.jprepository.superadminrepository.CategoryRepository;
import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.User;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mindtree.greencard")
public class WebConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

	@Bean
	public User getuse() {
		return new User();
	}


	@Bean
	public GreenCardHistory getgcHistory() {
		return new GreenCardHistory();
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(209715202); // 20MB
		multipartResolver.setMaxInMemorySize(10485762); // 1MB
		return multipartResolver;
	}
}