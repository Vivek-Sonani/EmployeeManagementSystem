package com.springboot.employee.service;

import java.util.List;

import com.springboot.employee.model.FamilyInfo;

public interface FamilyInfoService {

	FamilyInfo addFamilyInfo(FamilyInfo familyInfo);

	FamilyInfo updateFamilyInfo(FamilyInfo familyInfo);

	FamilyInfo getFamilyInfo(Integer id);

	//List<FamilyInfo> getAllFamilyInfo(FamilyInfo familyInfo);

	List<FamilyInfo> getByEmployeeId(Integer employeeId);

	void removeFamilyInfo(Integer id);

}
