package com.springboot.employee.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FamilyInfoCreatedEvent extends FamilyInfoEvent{


	private static final long serialVersionUID = 1083184160979173721L;

	public FamilyInfoCreatedEvent(Integer id) {
		super(id);
	}

}
