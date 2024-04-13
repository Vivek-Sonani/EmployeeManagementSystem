package com.springboot.employee.event;


public class FamilyInfoUpdatedEvent extends FamilyInfoEvent {

	private static final long serialVersionUID = 1354536774044838185L;

	public FamilyInfoUpdatedEvent(Integer employeeId) {
		super(employeeId);
	}
	
	


}
