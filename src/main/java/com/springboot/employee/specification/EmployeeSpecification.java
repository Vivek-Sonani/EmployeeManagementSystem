package com.springboot.employee.specification;

import org.springframework.data.jpa.domain.Specification;


import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Employee_;

public class EmployeeSpecification {

	public static Specification<Employee> hasFirstname(String firstname) {
		return ((root, Query, builder) -> {
			 Query.distinct(true);
			System.out.println("running.......");// distinct value we get
			return builder.equal(root.get(Employee_.firstname), firstname);
		});
	}
	public static Specification<Employee> hasPosition(String position) {
		return ((root, CriteriaQuery, CriteriaBuilder) -> {
			CriteriaQuery.distinct(true);
			System.out.println("position");
			return CriteriaBuilder.equal(root.get(Employee_.position), position);
		});
	}
}
