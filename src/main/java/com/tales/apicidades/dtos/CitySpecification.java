package com.tales.apicidades.dtos;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import com.tales.apicidades.entity.City;

/**
 * 
 * @author Tales
 *
 */
public class CitySpecification implements Specification<City>{
	
    private String randomColumnName; // Column name
    private String valueToSearchFor; // Value of column

    public CitySpecification() {
    	
    }    
    
	public CitySpecification(String randomColumnName, String valueToSearchFor) {
		super();
		this.randomColumnName = randomColumnName;
		this.valueToSearchFor = valueToSearchFor;
	}

	/**
	 * @return the randomColumnName
	 */
	public String getRandomColumnName() {
		return randomColumnName;
	}

	/**
	 * @param randomColumnName the randomColumnName to set
	 */
	public void setRandomColumnName(String randomColumnName) {
		this.randomColumnName = randomColumnName;
	}

	/**
	 * @return the valueToSearchFor
	 */
	public String getValueToSearchFor() {
		return valueToSearchFor;
	}

	/**
	 * @param valueToSearchFor the valueToSearchFor to set
	 */
	public void setValueToSearchFor(String valueToSearchFor) {
		this.valueToSearchFor = valueToSearchFor;
	}

	@Override
	public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.and(cb.equal(root.<String>get(this.randomColumnName), this.valueToSearchFor));
	}

}
