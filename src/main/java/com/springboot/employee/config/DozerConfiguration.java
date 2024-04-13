package com.springboot.employee.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class DozerConfiguration {

    @Bean
    DozerBeanMapper dozerBeanMapper() {
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
    
//    @Bean
//    public Executor taskExecutor() {
//      ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//      executor.setCorePoolSize(2);
//      executor.setMaxPoolSize(2);
//      executor.setQueueCapacity(500);
//      executor.setThreadNamePrefix("GithubLookup-");
//      executor.initialize();
//      executor.execute(() ->{
//    	  
//      });
//      return executor;
//    }
}
