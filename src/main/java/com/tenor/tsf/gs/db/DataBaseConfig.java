package com.tenor.tsf.gs.db;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
@Configuration
public class DataBaseConfig {
	
	@Bean
    public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.h2.Driver");
		driverManagerDataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		driverManagerDataSource.setUsername("sa");
		driverManagerDataSource.setPassword("");
		
		
        return driverManagerDataSource;
    }
	  
	/*
	 * @Bean public JdbcTemplate createJdbcTeamplate() {
	 * 
	 * JdbcTemplate template = new JdbcTemplate();
	 * template.setDataSource(dataSource());
	 * 
	 * return template; }
	 */
}
