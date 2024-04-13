package com.springboot.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.FamilyInfo;

@Repository
public interface FamilyInfoRepository extends JpaRepository<FamilyInfo, Integer> {
	List<FamilyInfo> findByEmployeeEmployId(Integer employeeId);
}
