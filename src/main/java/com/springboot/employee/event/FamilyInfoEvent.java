package com.springboot.employee.event;

import java.util.HashMap;

import org.springframework.context.ApplicationEvent;

public class FamilyInfoEvent extends ApplicationEvent{

	private static final long serialVersionUID = -4274380886614868866L;
	
	private Integer employeeId;

	public FamilyInfoEvent() {
		super(new HashMap<>());
	}

	public FamilyInfoEvent(Integer employeeId) {
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
