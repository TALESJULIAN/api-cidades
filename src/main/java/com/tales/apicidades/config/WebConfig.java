package com.tales.apicidades.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tales.apicidades.dtos.CityDTO;
import com.tales.apicidades.dtos.StateDTO;
import com.tales.apicidades.entity.City;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public City city1() {
		return new City();
	}

	@Bean
	public City city2() {
		return new City();
	}
	
	@Bean
	public StateDTO stateDto() {
		return new StateDTO();
	}
	
	@Bean
	public CityDTO cityDto() {
		return new CityDTO();
	}

}
