package com.springboot.employee.service;

import java.util.List;

import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Employeedocument;

public interface EmployeeEsService {
	void getEsEmployee(Integer id);

	void index(Integer id);

	void deleteEsEmployee(Integer id);

	Iterable<Employeedocument> getAllEsEmployee();

	Employeedocument updateEmployee(Integer id);

	List<Employeedocument> searchEmployee(String text);

	List<Employeedocument> searchByFirstnameAndRelationship(String firstname, String relationship);

}
