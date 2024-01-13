package com.springboot.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.springboot.employee.model.Employee;

public interface EmployeeService {

	Employee getEmployee(Integer id);

	List<Employee> getAllEmployee();

	Employee addEmployee(Employee employee);

	void deleteEmployee(Integer id);

	Employee updateEmployee(Employee employee);

	void deleteEmployeesByPosition(String position);

	List<Employee> getEmployeeByFirstName(String firstname);

	List<Employee> FindAllByPage(int pageNo, int pageSize);

	List<Employee> findByName(String firstname);

	List<Employee> findByPosition(String position);

}
