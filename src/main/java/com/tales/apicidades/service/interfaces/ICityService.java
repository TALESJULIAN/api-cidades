package com.tales.apicidades.service.interfaces;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tales.apicidades.entity.City;
import com.tales.apicidades.model.StateModel;

/**
 * 
 * @author Tales
 *
 */
@Service
public interface ICityService {
	
	List<City> getCapitalCitiesOrderByName();
	
	StateModel getMaxUfState();
	
	StateModel getMinUfState();
	
	Integer getNumberCitiesPerState(String uf);
	
	City findByIbgeId(Integer ibgeId);
	
	List<City> getCitiesPerState(String uf);
	
	City saveCity(City city);
	
	void removeCity(City city);
	
	List<City> filterCsvColumn(String column, String value);
	
	List<City> findAllCities();
	
	void getDistance(City city1, City city2);
}
