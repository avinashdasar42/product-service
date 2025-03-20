package com.api.thirdparty.product_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
