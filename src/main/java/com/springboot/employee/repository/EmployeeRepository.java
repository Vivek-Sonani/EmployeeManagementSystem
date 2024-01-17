package com.springboot.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee>{
	
	@Modifying
	@Query(value = "DELETE e, a FROM employee e LEFT JOIN address a ON e.employ_id = a.employee_id WHERE e.position = :position",nativeQuery = true)
	void deleteByLastName(@Param("position") String position);

	//@Modifying
	@Query(value = "select * from employee where firstname=:firstname", nativeQuery = true)
	List<Employee> getEmployeeByFirstName(@Param("firstname") String firstname);

}