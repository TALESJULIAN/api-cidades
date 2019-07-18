package com.tales.apicidades.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.tales.apicidades.batch.validate.CityValidate;

public class CityItemProcessor implements ItemProcessor<CityValidate, CityValidate>{
	
	private static final Logger log = LoggerFactory.getLogger(CityItemProcessor.class);

	@Override
	public CityValidate process(final CityValidate city) throws Exception {

		log.info("Reading data : " + city);

		return city;
	}
}
