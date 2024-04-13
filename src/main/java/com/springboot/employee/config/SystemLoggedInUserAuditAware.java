package com.springboot.employee.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class SystemLoggedInUserAuditAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("name of user :" + authentication);
		System.out.println(Optional.of(authentication));
		return Optional.of(authentication);
	}

}
