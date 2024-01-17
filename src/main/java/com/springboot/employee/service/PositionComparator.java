package com.springboot.employee.service;

import java.util.Comparator;

import com.springboot.employee.model.Employee;

public class PositionComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		Employee e1 = (Employee) o1;
		Employee e2 = (Employee) o2;
		return e1.getPosition().compareTo(e2.getPosition());
	}
}
