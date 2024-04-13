package com.springboot.employee.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import com.springboot.employee.model.Employee;

public class EmployeeUpdatedEvent extends EmployeeEvent {

	private static final long serialVersionUID = 8018876335498109369L;

	public EmployeeUpdatedEvent(Integer employeeId) {
		super(employeeId);
	}

}
