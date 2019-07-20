package com.tales.apicidades.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.tales.apicidades.entity.City;

public interface ICitySpecificationRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

}
