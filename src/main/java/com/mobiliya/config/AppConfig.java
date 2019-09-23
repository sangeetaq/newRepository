package com.mobiliya.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppConfig {

	private static Logger logger = LogManager.getLogger();

	// Create Bean of ModelMapper which converts Entity to DTO and DTO to Entity.
	@Bean
	public ModelMapper modelMapper() {
		logger.info("Creates Model Mapper Bean ");
		logger.info("Info level log message");
		logger.debug("Debug level log message");
		logger.error("Error level log message");
		return new ModelMapper();
	}
}
