package com.springboot.employee;

import java.text.SimpleDateFormat;

import org.apache.commons.collections.CollectionUtils;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
// @EnableGlobalMethodSecurity //--> for @preAuthorize annotation
public class EmployeeManagementSystem {
  public static void main(String[] args) {
    SpringApplication.run(EmployeeManagementSystem.class, args);
  }
}
// @Bean
// public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
// return args -> {
// System.out.println("Let's inspect the beans provided by Spring Boot:");
// String[] beanNames = ((ListableBeanFactory) ctx).getBeanDefinitionNames();
// Arrays.sort(beanNames);
// for (String beanName : beanNames) {
// System.out.println(beanName);
// }
// };
// }
// @Bean
// public boolean createTestIndex(RestHighLevelClient restHighLevelClient) throws Exception {
// try {
// DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("hello-world");
// restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT); // 1
// } catch (Exception ignored) {
// }
//
// CreateIndexRequest createIndexRequest = new CreateIndexRequest("hello-world");
// createIndexRequest.settings(
// Settings.builder().put("index.number_of_shards", 1)
// .put("index.number_of_replicas", 0));
// restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT); // 2
//
// return true;
// }
