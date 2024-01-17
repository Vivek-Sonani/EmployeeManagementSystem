package com.springboot.employee;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FlywayConfiguration {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) //this is bydefault scope
	public FlywayMigrationStrategy cleanup() {
		return flyway -> {
			flyway.repair();
			flyway.migrate();
		};
	}

}
