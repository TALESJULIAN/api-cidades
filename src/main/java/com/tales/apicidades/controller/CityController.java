package com.tales.apicidades.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tales.apicidades.dtos.CityDTO;
import com.tales.apicidades.dtos.StateDTO;
import com.tales.apicidades.entity.City;
import com.tales.apicidades.response.Response;
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
	
	@GetMapping(value = "/getMaxCitiesPerState")
	@ResponseBody
	public StateDTO getMaxCitiesPerState() {
		StateDTO stateDto = new StateDTO();
		stateDto = this.cityService.getMaxUfState();
		return stateDto;
	}
	
	@GetMapping(value = "/getMinCitiesPerState")
	@ResponseBody
	public StateDTO getMinCitiesPerState() {
		StateDTO stateDto = new StateDTO();
		stateDto = this.cityService.getMinUfState();
		return stateDto;
	}
	
	@GetMapping(value = "/getQtdeByUf/{uf}")
	@ResponseBody
	public Integer getQtdeByUf(@PathVariable("uf") String uf) {
		return this.cityService.getNumberCitiesPerState(uf);
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
	
	@PostMapping
	public ResponseEntity<Response<CityDTO>> adicionar(@Valid @RequestBody CityDTO cityDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando lançamento: {}", cityDto.toString());
		Response<CityDTO> response = new Response<CityDTO>();
		City city = this.cityService.convertCityDto(cityDto);

		if (result.hasErrors()) {
			log.error("Erro validando cidade: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		city = this.cityService.saveCity(city);
		response.setData(this.cityService.convertCity(city));
		return ResponseEntity.ok(response);
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
	
	@GetMapping(value = "/getMaxDistance")
	@ResponseBody
	public List<City> getMaxDistance(){
		List<City> cities = new ArrayList<>();
		this.cityService.getDistance();
		cities = this.cityService.getListCityMaxDistance();
		return cities;
	}
	
	@GetMapping(value = "/getMinDistance")
	@ResponseBody
	public List<City> getMinDistance(){
		List<City> cities = new ArrayList<>();
		this.cityService.getDistance();
		cities = this.cityService.getListCityMinDistance();
		return cities;
	}
	
}
