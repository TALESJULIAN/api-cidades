package com.tales.apicidades.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tales.apicidades.batch.model.CityIO;
import com.tales.apicidades.dtos.CityDTO;
import com.tales.apicidades.dtos.CitySpecification;
import com.tales.apicidades.dtos.GeoCoordinateDTO;
import com.tales.apicidades.dtos.StateDTO;
import com.tales.apicidades.entity.City;
import com.tales.apicidades.repositories.interfaces.ICityRepository;
import com.tales.apicidades.repositories.interfaces.ICitySpecificationRepository;
import com.tales.apicidades.service.interfaces.ICityService;

/**
 * 
 * @author Tales
 *
 */
@Service
public class CityService implements ICityService{
	
	private List<City> listCityMaxDistance;
	
	private List<City> listCityMinDistance;
	
	private Double maxDistance;
	
	private Double minDistance;
	
	@Autowired
	private StateDTO stateMaxCities;
	
	@Autowired
	private StateDTO stateMinCities;
	
	@Autowired
	private StateDTO stateDto;
	
	@Autowired
	private CityDTO cityDto;
	
	@Autowired
	private ICityRepository cityRepository;
	
	@Autowired
	private ICitySpecificationRepository citySpecificationRepository;

	@Autowired
	private City city1;
	
	@Autowired
	private City city2;
	
	private static final Logger log = LoggerFactory.getLogger(CityService.class);

	public CityService() {
		setCity1(new City());
		setCity2(new City());
		this.listCityMaxDistance = new ArrayList<>();
		this.listCityMinDistance = new ArrayList<>();
	}

	@Override
	public List<City> getCapitalCitiesOrderByName() {
		return this.cityRepository.findByCapital(Boolean.TRUE);
	}

	@Override
	public StateDTO getMaxUfState() {
		this.stateMaxCities = null;
		List<City> cities = this.cityRepository.findByCapital(Boolean.TRUE);
		List<StateDTO> states = new ArrayList<>();
		for(City city : cities) {
			StateDTO stateDto = new StateDTO();
			stateDto.setUf(city.getUf());
			states.add(stateDto);
		}
		if(!states.isEmpty()) {
			for(StateDTO state : states) {
				state.setNumberOfCities(this.cityRepository.countByUf(state.getUf()));
				if(this.stateMaxCities == null || this.stateMaxCities.getNumberOfCities() == null) {
					this.stateMaxCities = state;
					System.out.println(this.stateMaxCities.toString());
				}
				else {
					if(this.stateMaxCities.getNumberOfCities() < state.getNumberOfCities()) {
						this.stateMaxCities = state;
						System.out.println(this.stateMaxCities.toString());
					}
				}
			}
		}
		return this.stateMaxCities;
	}

	@Override
	public StateDTO getMinUfState() {
		this.stateMinCities = null;
		List<City> cities = this.cityRepository.findByCapital(Boolean.TRUE);
		List<StateDTO> states = new ArrayList<>();
		for(City city : cities) {
			StateDTO stateDto = new StateDTO();
			stateDto.setUf(city.getUf());
			states.add(stateDto);
		}
		if(!states.isEmpty()) {
			for(StateDTO state : states) {
				System.out.println(state.toString());
				state.setNumberOfCities(this.cityRepository.countByUf(state.getUf()));
				if(this.stateMinCities == null || this.stateMinCities.getNumberOfCities() == null) {
					this.stateMinCities = state;
					System.out.println(this.stateMinCities.toString());
				}
				else {
					if(this.stateMinCities.getNumberOfCities() > state.getNumberOfCities()) {
						this.stateMinCities = state;
						System.out.println(this.stateMinCities.toString());
					}
				}
			}
		}
		
		return this.stateMinCities;
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
		this.cityRepository.save(city);
		return city;
	}

	@Override
	public boolean removeCity(Integer ibgeId) {
		boolean deleted = false;
		City city = new City();
		try {
			city = cityRepository.findByIbgeId(ibgeId);
			this.cityRepository.delete(city);
			deleted = true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleted;
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
	public Integer getQtdeRecords() {
		return this.cityRepository.findAll().size();
	}
	
	@Override
	public void getDistance() {
		City city1 = new City();
		City city2 = new City();
		
		List<City> cities = this.cityRepository.findAll();
		
		city1 = cities.get(0);
		
		for(int i = 0; i < cities.size(); i++) {
			city1 = cities.get(i);
			for(int j = (i+1); j < cities.size(); j++) {
				city2 = cities.get(j);
				GeoCoordinateDTO cityOne = new GeoCoordinateDTO(city1.getLat(),
						city1.getLon());
				GeoCoordinateDTO cityTwo = new GeoCoordinateDTO(city2.getLat(), city2.getLon());
						double distance = cityOne.distanceInKm(cityTwo);
				//Set max distance 
				if(this.maxDistance == null || this.maxDistance == 0) {
					this.maxDistance = distance;
					if(this.listCityMaxDistance.isEmpty()) {
						this.listCityMaxDistance.add(city1);
						this.listCityMaxDistance.add(city2);
					}
				}
				else {
					if(distance > this.maxDistance) {
						this.maxDistance = distance;
						this.listCityMaxDistance.clear();
						this.listCityMaxDistance.add(city1);
						this.listCityMaxDistance.add(city2);
					}
				}
				//Set min distance
				if(this.minDistance == null || this.minDistance == 0) {
					this.minDistance = distance;
					if(this.listCityMinDistance.isEmpty()) {
						this.listCityMinDistance.add(city1);
						this.listCityMinDistance.add(city2);
					}
				}
				else {
					if(distance < this.minDistance) {
						this.minDistance = distance;
						this.listCityMinDistance.clear();
						this.listCityMinDistance.add(city1);
						this.listCityMinDistance.add(city2);
					}
				}
			}
			
		}
	}
	
	/**
	 * 
	 * @param column
	 * @param value
	 * @return List<City>
	 */
	public Integer getCitiesByColumnValue(String column, String value){
		if(column.equals("capital") && (value.equals("TRUE") || value.equals("true"))) {
			return this.cityRepository.findByCapital(Boolean.TRUE).size(); 
		}
		else if(column.equals("capital") && (value.equals("FALSE") || value.equals("false"))) {
			return this.cityRepository.findByCapital(Boolean.FALSE).size();
		}
		else {
			CitySpecification citySpecification = new CitySpecification(column, value);
			return this.citySpecificationRepository.findAll(citySpecification, new Sort(column)).size();
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
	 * @return the listCityMaxDistance
	 */
	public List<City> getListCityMaxDistance() {
		return listCityMaxDistance;
	}

	/**
	 * @param listCityMaxDistance the listCityMaxDistance to set
	 */
	public void setListCityMaxDistance(List<City> listCityMaxDistance) {
		this.listCityMaxDistance = listCityMaxDistance;
	}

	/**
	 * @return the listCityMinDistance
	 */
	public List<City> getListCityMinDistance() {
		return listCityMinDistance;
	}

	/**
	 * @param listCityMinDistance the listCityMinDistance to set
	 */
	public void setListCityMinDistance(List<City> listCityMinDistance) {
		this.listCityMinDistance = listCityMinDistance;
	}

	/**
	 * Convert City entity into CityDTO
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
	
	/**
	 * Convert CityDTO into City Entity
	 * @param cityDto
	 * @return city
	 */
	public City convertCityDto(CityDTO cityDto) {
		City city = new City();
		city.setIbge_id(cityDto.getIbge_id());
		city.setName(cityDto.getName());
		city.setUf(cityDto.getUf());
		city.setCapital(cityDto.getCapital());
		city.setLat(cityDto.getLat());
		city.setLon(cityDto.getLon());
		city.setAlternative_names(cityDto.getAlternative_names());
		city.setNo_accents(cityDto.getNo_accents());
		city.setMesoregion(cityDto.getMesoregion());
		city.setMicroregion(cityDto.getMicroregion());
		
		return city;
	}
	
	
	public City convertCityIO(CityIO cityIo) {
		City city = new City();
		city.setIbge_id(Integer.parseInt(cityIo.getIbge_id()));
		city.setName(cityIo.getName());
		city.setUf(cityDto.getUf());
		city.setCapital(cityDto.getCapital());
		city.setLat(cityDto.getLat());
		city.setLon(cityDto.getLon());
		city.setAlternative_names(cityDto.getAlternative_names());
		city.setNo_accents(cityDto.getNo_accents());
		city.setMesoregion(cityDto.getMesoregion());
		city.setMicroregion(cityDto.getMicroregion());
		return city;
	}

	public static Logger getLog() {
		return log;
	}

}
