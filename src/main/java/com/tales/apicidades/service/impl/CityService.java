package com.tales.apicidades.service.impl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.apicidades.dtos.CityDTO;
import com.tales.apicidades.dtos.GeoCoordinateDTO;
import com.tales.apicidades.dtos.StateDTO;
import com.tales.apicidades.entity.City;
import com.tales.apicidades.repositories.interfaces.ICityRepository;
import com.tales.apicidades.service.interfaces.ICityService;

/**
 * 
 * @author Tales
 *
 */
@Service
public class CityService implements ICityService{
	
	private LinkedHashMap<City, City> mapCityMaxDistance;
	
	private LinkedHashMap<City, City> mapCityMinDistance;
	
	private Double maxDistance;
	
	private Double minDistance;
	
	@Autowired
	private StateDTO stateDto;
	
	@Autowired
	private CityDTO cityDto;
	
	@Autowired
	private ICityRepository cityRepository;

	@Autowired
	private City city1;
	
	@Autowired
	private City city2;
	
	public CityService() {
		setCity1(new City());
		setCity2(new City());
		this.mapCityMaxDistance = new LinkedHashMap<>();
		this.mapCityMinDistance = new LinkedHashMap<>();			
	}

	@Override
	public List<City> getCapitalCitiesOrderByName() {
		return this.cityRepository.getCapitalCitiesOrderByName(true);
	}

	@Override
	public StateDTO getMaxUfState() {
		return this.getMaxUfState();
	}

	@Override
	public StateDTO getMinUfState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberCitiesPerState(String uf) {
		return this.cityRepository.findByUf(uf).size();
	}

	@Override
	public City findByIbgeId(Integer ibgeId) {
		return this.cityRepository.findByIbgeId(ibgeId);
	}

	@Override
	public List<City> getCitiesPerState(String uf) {
		return this.cityRepository.findByUf(uf);
	}

	@Override
	public City saveCity(City city) {
		return this.cityRepository.save(city);
	}

	@Override
	public void removeCity(Integer ibgeId) {
		City city = new City();
		try {
			city = cityRepository.findByIbgeId(ibgeId);
			this.cityRepository.delete(city);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<City> filterCsvColumn(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> findAllCities() {
		return this.cityRepository.findAll();
	}

	@Override
	public void getDistance(City city1, City city2) {
		GeoCoordinateDTO cityOne = new GeoCoordinateDTO(city1.getLat(),
				city1.getLon());
		GeoCoordinateDTO cityTwo = new GeoCoordinateDTO(city2.getLat(), city2.getLon());
				double distance = cityOne.distanceInKm(cityTwo);
		//Set max distance 
		if(this.maxDistance == null || this.maxDistance == 0) {
			this.maxDistance = distance;
			if(this.mapCityMaxDistance.isEmpty()) {
				this.mapCityMaxDistance.put(city1, city2);
			}
		}
		else {
			if(distance > this.maxDistance) {
				this.maxDistance = distance;
				this.mapCityMaxDistance.clear();
				this.mapCityMaxDistance.put(city1, city2);
			}
		}
		//Set min distance
		if(this.minDistance == null || this.minDistance == 0) {
			this.minDistance = distance;
			if(this.mapCityMinDistance.isEmpty()) {
				this.mapCityMinDistance.put(city1, city2);
			}
		}
		else {
			if(distance > this.minDistance) {
				this.minDistance = distance;
				this.mapCityMinDistance.clear();
				this.mapCityMinDistance.put(city1, city2);
			}
		}
	}

	/**
	 * @return the maxDistance
	 */
	public Double getMaxDistance() {
		return maxDistance;
	}

	/**
	 * @return the minDistance
	 */
	public Double getMinDistance() {
		return minDistance;
	}

	/**
	 * @return the stateDto
	 */
	public StateDTO getStateDto() {
		return stateDto;
	}

	/**
	 * @param stateDto the stateDto to set
	 */
	public void setStateDto(StateDTO stateDto) {
		this.stateDto = stateDto;
	}

	/**
	 * @return the cityDto
	 */
	public CityDTO getCityDto() {
		return cityDto;
	}

	/**
	 * @param cityDto the cityDto to set
	 */
	public void setCityDto(CityDTO cityDto) {
		this.cityDto = cityDto;
	}

	/**
	 * @return the cityRepository
	 */
	public ICityRepository getCityRepository() {
		return cityRepository;
	}

	/**
	 * @param cityRepository the cityRepository to set
	 */
	public void setCityRepository(ICityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public City getCity1() {
		return city1;
	}

	public void setCity1(City city1) {
		this.city1 = city1;
	}

	public City getCity2() {
		return city2;
	}

	public void setCity2(City city2) {
		this.city2 = city2;
	}
	
	/**
	 * 
	 * @param city
	 * @return cityDTO
	 */
	public CityDTO convertCity(City city) {
		CityDTO cityDto = new CityDTO();
		cityDto.setIbge_id(city.getIbgeId());
		cityDto.setName(city.getName());
		cityDto.setUf(city.getUf());
		cityDto.setCapital(city.getCapital());
		cityDto.setLat(city.getLat());
		cityDto.setLon(city.getLon());
		cityDto.setAlternative_names(city.getAlternative_names());
		cityDto.setNo_accents(city.getNo_accents());
		cityDto.setMesoregion(city.getMesoregion());
		cityDto.setMicroregion(city.getMicroregion());
		
		return cityDto;
	}
	
}
