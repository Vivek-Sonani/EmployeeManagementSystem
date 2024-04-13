package com.springboot.employee.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.AddressDocument;

@Repository
public interface AddressEsRepository extends ElasticsearchRepository<AddressDocument, Integer> {

}
