package com.tales.apicidades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tales.apicidades.service.impl.CityService;

/**
 * 
 * @author Tales
 *
 */
@RestController
@RequestMapping("/city")
public class CityController {
	
	@SuppressWarnings("unused")
	@Autowired
	private CityService cityService;
	
	
}
