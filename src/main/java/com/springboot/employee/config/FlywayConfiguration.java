package com.springboot.employee.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FlywayConfiguration {

    //this is bydefault scope
    @Bean
    //@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    FlywayMigrationStrategy cleanup() {
		return flyway -> {
			flyway.repair();
			flyway.migrate();
		};
	}

}
