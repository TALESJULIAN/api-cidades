package com.tales.apicidades.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tales.apicidades.entity.City;

/**
 * 
 * @author Tales
 *
 */
@Repository
public interface ICityRepository extends JpaRepository<City, Integer>{
	
	
	
}
