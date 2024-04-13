package com.springboot.employee.service;

import java.util.List;
import java.util.Map;

import com.springboot.employee.model.Employee;
import com.springboot.employee.model.FamilyInfo;

public interface FamilyInfoService {

	FamilyInfo addFamilyInfo(FamilyInfo familyInfo);

	FamilyInfo updateFamilyInfo(FamilyInfo familyInfo);

	FamilyInfo getFamilyInfo(Integer id);

	//List<FamilyInfo> getAllFamilyInfo(FamilyInfo familyInfo);

	List<FamilyInfo> getByEmployeeId(Integer employeeId);

	void removeFamilyInfo(Integer id,Integer employeeId);

	//Employee patchFamilyInfo(Integer employId, Map<String, Object> employee);

}
