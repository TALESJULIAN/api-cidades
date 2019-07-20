package com.tales.apicidades.repositories.interfaces;

import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tales.apicidades.entity.City;

/**
 * 
 * @author Tales
 *
 */
@NamedQueries({
		@NamedQuery(name = "ICityRepository.findByCapital",
		query = "SELECT c FROM city c WHERE c.capital = :isCapital ORDER BY c.name"),
		@NamedQuery(name = "ICityRepository.countByUf",
		query = "SELECT COUNT(c), c.uf FROM city c WHERE c.uf = : uf GROUP BY c.uf")
})

@Repository
public interface ICityRepository extends JpaRepository<City, Long>{
	
	City findByIbgeId(Integer ibgeId);
	
	List<City> findByCapital(@Param("isCapital") Boolean capital);
	
	List<City> findByUf(String uf);

	Long countByUf(@Param("uf") String uf);	

}
