package com.tales.apicidades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tales.apicidades.dtos.CityDTO;
import com.tales.apicidades.entity.City;
import com.tales.apicidades.service.impl.CityService;

/**
 * 
 * @author Tales
 *
 */
@RestController
@RequestMapping("/city")
@CrossOrigin(origins = "*")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping(value = "/{ibge_id}")
	@ResponseBody
	public CityDTO getCityByIbgeId(@PathVariable("ibge_id") Integer ibgeId) {
		CityDTO cityDto = new CityDTO();
		City city = new City();
		city = this.cityService.findByIbgeId(ibgeId);
		cityDto = cityService.convertCity(city);
		return cityDto;
	}
	
	@DeleteMapping(value = "/{ibge_id}")
	public void deleteCity(@PathVariable("ibge_id") Integer ibgeId) {
		this.cityService.removeCity(ibgeId);
	}
	
	
}
