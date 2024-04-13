package com.springboot.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.FamilyInfo;
import com.springboot.employee.model.FamilyInfoDocument;

@Repository
public interface FamilyInfoEsRepository extends ElasticsearchRepository<FamilyInfoDocument, Integer>{

    List<FamilyInfoDocument> findByEmployeeId(Integer id);

}
