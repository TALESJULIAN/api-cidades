package com.tales.apicidades.batch.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.tales.apicidades.entity.City;

/**
 * 
 * 
 * @author Tales
 *
 */
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		// if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
		log.info("!!! JOB FINISHED! Time to verify the results");

		List<City> results = jdbcTemplate.query("SELECT ibge_id,uf,name,capital,lon,lat,"
				+ "no_accents,alternative_names,microregion,mesoregion FROM city", new RowMapper<City>() {
			@Override
			public City mapRow(ResultSet rs, int row) throws SQLException {
				return new City(rs.getInt("ibge_id"), rs.getString("uf"), rs.getString("name"), 
                		rs.getBoolean("capital"), rs.getDouble("lon"), rs.getDouble("lat"),
                		rs.getString("no_accents"), rs.getString("alternative_names"), 
                		rs.getString("microregion"), rs.getString("mesoregion"));
			}
		});

		for (City city : results) {
			log.info("Found <" + city + "> in the database.");
		}

	}

}
