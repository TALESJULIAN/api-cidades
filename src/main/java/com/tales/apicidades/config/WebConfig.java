package com.tales.apicidades.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tales.apicidades.entity.City;
import com.tales.apicidades.model.CityModel;
import com.tales.apicidades.model.StateModel;

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
	public StateModel stateModel() {
		return new StateModel();
	}
	
	@Bean
	public CityModel cityModel() {
		return new CityModel();
	}

}
