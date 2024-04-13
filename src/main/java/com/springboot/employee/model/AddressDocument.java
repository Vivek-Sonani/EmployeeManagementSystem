package com.springboot.employee.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "address_index")
public class AddressDocument {
	@Id
	@Field(type = FieldType.Integer)
	private Integer id;

	@Column
	@Field(type = FieldType.Text)
	private String city;

	@Column
	@Field(type = FieldType.Text)
	private String state;

	//@OneToOne(fetch = FetchType.EAGER)
	// @JsonProperty("employee_id")
	@Field(type = FieldType.Integer)
	@JsonIgnore
	private Employeedocument employee;

	@Field(type = FieldType.Integer)
	private Integer employId;
	
//	@LastModifiedBy
//	@Field(type = FieldType.Text)
//	private String modifiedBy;
//
//	public String getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(String modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}

	public Employeedocument getEmployee() {
		return employee;
	}

	public void setEmployee(Employeedocument employee) {
		this.employee = employee;
	}

	public AddressDocument() {

		super();
	}

	public AddressDocument(Integer id, String city, String state) {
		super();
		this.id = id;
		this.city = city;
		this.state = state;
	}

	public AddressDocument(Address address) {
		this.id = address.getId();
		this.city = address.getCity();
		this.state = address.getState();
		this.employId = address.getEmployee().getEmployId();
		//this.modifiedBy = address.getModifiedBy();
	}

	public Integer getEmployId() {
		return employId;
	}

	public void setEmployId(Integer employId) {
		this.employId = employId;
	}

	public Integer getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", state=" + state + ", employee=" + employee + "]";
	}

}
