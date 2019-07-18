package com.tales.apicidades.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tales.apicidades.entity.City;
import com.tales.apicidades.model.CityModel;
import com.tales.apicidades.model.GeoCoordinate;
import com.tales.apicidades.model.StateModel;
import com.tales.apicidades.service.interfaces.ICityService;

/**
 * 
 * @author Tales
 *
 */
@Service
public class CityService implements ICityService{

	@Override
	public List<City> getCapitalCitiesOrderByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateModel getMaxUfState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateModel getMinUfState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberCitiesPerState(String uf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City findByIbgeId(Integer ibge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getCitiesPerState(String uf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City saveCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCity(City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<City> filterCsvColumn(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityModel findAllCities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMaxDistance() {
		GeoCoordinate cityOne = new GeoCoordinate(-20.81972,
				-49.37944);
		GeoCoordinate cityTwo = new GeoCoordinate(-20.42278, -49.97278);
				double distance = cityOne.distanceInKm(CityTwo);
		return distance;
	}

}
