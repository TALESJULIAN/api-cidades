package com.tales.apicidades.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger log = LoggerFactory.getLogger(CityController.class);
	
	@Autowired
	private CityService cityService;
	
	@GetMapping(value = "/getCapitals")
	@ResponseBody
	public List<CityDTO> getCapitals() {
		List<CityDTO> citiesDto = new ArrayList<>();
		List<City> cities =  this.cityService.getCapitalCitiesOrderByName();
		for(City city : cities) {
			CityDTO cityDto = new CityDTO();
			cityDto = cityService.convertCity(city);
			citiesDto.add(cityDto);
		}
		Collections.sort (citiesDto, new Comparator<CityDTO> () {  
            public int compare (CityDTO c1, CityDTO c2) {  
                return c1.getName().toUpperCase().compareTo (c2.getName().toUpperCase());  
            }  
        }); 
		return citiesDto;
	}
	
	@GetMapping(value = "/getByIbge/{ibge_id}")
	@ResponseBody
	public CityDTO getCityByIbgeId(@PathVariable("ibge_id") Integer ibgeId) {
		CityDTO cityDto = new CityDTO();
		City city = new City();
		city = this.cityService.findByIbgeId(ibgeId);
		cityDto = cityService.convertCity(city);
		return cityDto;
	}
	
	@GetMapping(value = "/getByUf/{uf}")
	@ResponseBody
	public List<CityDTO> getCitiesByUf(@PathVariable("uf") String uf) {
		List<CityDTO> citiesDto = new ArrayList<CityDTO>();
		List<City> cities = this.cityService.getCitiesPerState(uf);
		for(City city : cities) {
			CityDTO cityDto = new CityDTO();
			cityDto = cityService.convertCity(city);
			citiesDto.add(cityDto);
		}
		return citiesDto;
	}
	
	@GetMapping(value = "/getAll")
	@ResponseBody
	public Integer getQtdeRecords() {
		return this.cityService.getQtdeRecords();
	}
	
	
	@DeleteMapping(value = "/delete/{ibge_id}")
	public String deleteCityByIbgeId(@PathVariable("ibge_id") Integer ibgeId) {
		if (!this.cityService.removeCity(ibgeId)) {
			log.info("Ibge id : {} not found.", ibgeId);
			return "Cidade não encontrada";
		}
		else {
			return "Cidade deletada com sucesso!";
		}
	}
	
	
}
