package com.springboot.employee.event;

import java.util.HashMap;

import org.springframework.context.ApplicationEvent;

public class EmployeeEvent extends ApplicationEvent {

	private static final long serialVersionUID = 8970553785094240499L;

	private Integer employeeId;

	public EmployeeEvent() {
		super(new HashMap<>());
	}

	public EmployeeEvent(Integer employeeId) {
		this();
		this.employeeId = employeeId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
