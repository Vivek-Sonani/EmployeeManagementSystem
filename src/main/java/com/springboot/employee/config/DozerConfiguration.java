package com.springboot.employee.config;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class DozerConfiguration {

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
//    	List<String> mappingFiles = Arrays.asList("mapping.xml");
//        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
//        dozerBeanMapper.setMappingFiles(Collections.singletonList("mapping.xml"));
//       System.setProperty("dozer.configuration", "dozer.properties");
//        dozerBeanMapper.setMappingFiles(mappingFiles);
		// dozerBeanMapper.setStatisticsEnabled(false);
		// dozerBeanMapper.setELEnabled(false);
		List<String> mappingFiles = Arrays.asList("mapping.xml");
		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		dozerBeanMapper.setMappingFiles(Collections.singletonList("mapping.xml"));
		return dozerBeanMapper;
	}
}
