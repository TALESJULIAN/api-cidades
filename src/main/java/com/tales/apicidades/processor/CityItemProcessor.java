package com.tales.apicidades.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.tales.apicidades.batch.model.CityIO;

public class CityItemProcessor implements ItemProcessor<CityIO, CityIO>{
	
	private static final Logger log = LoggerFactory.getLogger(CityItemProcessor.class);

	@Override
	public CityIO process(final CityIO city) throws Exception {

		log.info("Reading data : " + city);

		return city;
	}
}
