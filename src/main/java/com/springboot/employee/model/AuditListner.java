package com.springboot.employee.model;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class AuditListner {

	@PrePersist
	public void onCreate(Object entity) {
		System.out.println("entity will be created....");
	}

	@PreUpdate
	public void onUpdate(Object entity) {
		System.out.println("entity will be updated....");
	}

	@PreRemove
	public void onRemove(Object entity) {
		System.out.println("entity will be remove....");
	}

	@PostPersist
	public void postCreate(Object entity) {
		System.out.println("entity created....");
	}

	@PostUpdate
	public void postUpdate(Object entity) {
		System.out.println("entity Updated....");
	}

	@PostRemove
	public void postRemove(Object entity) {
		System.out.println("entity removed....");
	}

}
