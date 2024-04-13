package com.springboot.employee.model;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.join.JoinField;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "employee_index")
public class Employeedocument {
	@Id
	@Field(type = FieldType.Integer)
	Integer id;

	@Field(type = FieldType.Text)
	String firstname;

	@Field(type = FieldType.Text)
	String middlename;

	@Field(type = FieldType.Text)
	String lastname;

	// @Length
	@Field(type = FieldType.Text)
	String email;

	@Field(type = FieldType.Text)
	String position;

	@Field(type = FieldType.Integer)
	int contactNo;

	@Field(type = FieldType.Float)
	Float salary;

	@CreatedDate
	@Field(type = FieldType.Date, format = DateFormat.basic_date_time)
	// @Field(type = FieldType.Date, pattern = "dd.mm.uuuu")
    @JsonFormat(pattern = "yyyyMMdd'T'HHmmss.SSSX")
	private Date createdDate;

	@LastModifiedDate
	@Field(type = FieldType.Date, format = DateFormat.basic_date_time)
  //@Field(type = FieldType.Date, format = DateFormat.basic_date_time) --> 	@Field(type = FieldType.Date, format = {}) after 4.2 version , no need to declare manually
    @JsonFormat(pattern = "yyyyMMdd'T'HHmmss.SSSX")
	private Date modifiedDate;

	@CreatedBy
	@Field(type = FieldType.Text)
	private String createdBy;

	@LastModifiedBy
	@Field(type = FieldType.Text)
	private String modifiedBy;

	// @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade =
	// CascadeType.REMOVE)
	@Field(type = FieldType.Object)
	private AddressDocument address;

	// @JsonManagedReference
	// @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	@Field(type = FieldType.Nested)
	private List<FamilyInfoDocument> familyInfo;

	public Employeedocument(Employee employee) {
		this.id = employee.getEmployId();
		this.firstname = employee.getFirstname();
		this.middlename = employee.getMiddlename();
		this.lastname = employee.getLastname();
		this.position = employee.getPosition();
		this.email = employee.getEmail();
		this.contactNo = employee.getContactNo();
		this.salary = employee.getSalary();
		this.createdBy = employee.getCreatedBy();
		this.createdDate = employee.getCreatedDate();
		this.modifiedBy = employee.getModifiedBy();
		this.modifiedDate = employee.getModifiedDate();

		if (employee.getAddress() != null) {
			this.address = new AddressDocument(employee.getAddress());
		}
	    this.familyInfo = new ArrayList<>();
//		// Mapping familyInfo if available
		if (employee.getFamilyInfo() != null) {
			for (FamilyInfo familyInfoItem : employee.getFamilyInfo()) {
				this.familyInfo.add(new FamilyInfoDocument(familyInfoItem));
			}
		} else {
			this.familyInfo = new ArrayList<>();
		}
	}

	public List<FamilyInfoDocument> getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(List<FamilyInfoDocument> familyInfo) {
		this.familyInfo = familyInfo;
	}

	public AddressDocument getAddress() {
		return address;
	}

	public void setAddress(AddressDocument address) {
		this.address = address;
	}

	public Employeedocument() {
		// this.setEmployId((int) Math.random());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer employId) {
		this.id = employId;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "Employee [employId=" + id + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname="
				+ lastname + ", email=" + email + ", position=" + position + ", contactNo=" + contactNo + ", salary="
				+ salary + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}

}
