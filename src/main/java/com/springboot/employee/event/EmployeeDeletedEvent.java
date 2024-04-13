package com.springboot.employee.event;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import com.springboot.employee.model.Employee;

public class EmployeeDeletedEvent extends EmployeeEvent {
	private static final long serialVersionUID = -1867746191969590754L;

	public EmployeeDeletedEvent(Integer employeeId) {
		super(employeeId);
	}

}
