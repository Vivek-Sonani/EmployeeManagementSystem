package com.springboot.employee.event;

public class EmployeeCreatedEvent extends EmployeeEvent {

	private static final long serialVersionUID = 2577504824357999371L;

	public EmployeeCreatedEvent(Integer employeeId) {
		super(employeeId);
	}

}
