package com.springboot.employee.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Employeedocument;

@Repository
public interface EmployeeEsRepository extends ElasticsearchRepository<Employeedocument, Integer> {
	
	// |
	// -->> this is only for understanding purpose
	   
//	 @Query("{\"match\": {\"name\": \"?0\"}}")
//	    List<Product> findProductsByName(String name);
//
//	    @Query("{\"bool\": {\"must\": [{\"match\": {\"description\": \"?0\"}}, {\"range\": {\"price\": {\"gte\": ?1}}}]}}")
//	    List<Product> findProductsByDescriptionAndPriceGreaterThanEqual(String description, double price);
	
//	 // Find the first product sorted by name
//    Product findFirstByName(String name);
//
//    // Find the top 5 products sorted by price in descending order
//    List<Product> findTop5ByOrderByPriceDesc();
}