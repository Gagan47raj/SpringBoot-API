package com.saga.cleanindia.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JpaConfig {
	
	@Bean(name = "mySqlDataSource")
	@Primary
	public DataSource mySqlDatasource()
	{
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/clean_india_apis");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("1234");
		return dataSourceBuilder.build();
	}
}
