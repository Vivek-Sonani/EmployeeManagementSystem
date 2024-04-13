package com.springboot.employee.event;


public class FamilyInfoDeletedEvent extends FamilyInfoEvent{

	private static final long serialVersionUID = 4863495857302057234L;

	public FamilyInfoDeletedEvent(Integer employeeId) {
		super(employeeId);
	}
	
	


}
