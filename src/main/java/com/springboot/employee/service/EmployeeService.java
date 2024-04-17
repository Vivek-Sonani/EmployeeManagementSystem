package com.springboot.employee.service;

import java.util.List;
import java.util.Map;

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

  Employee patch(String id, Map<String, Object> employee);

}
