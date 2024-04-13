package com.springboot.employee.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.springboot.employee.model.FamilyInfo.Relationship;
import com.springboot.employee.repository.FamilyInfoRepository;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "familyinfo_index")
public class FamilyInfoDocument {
	// define enum Relationship because number of fields are constant.
//	enum Relationship {
//		FATHER, MOTHER, BROTHER
//	}

	@Id
	@Field(type = FieldType.Integer)
	private Integer id;

	@Field(type = FieldType.Text)
	private String name;

	@Enumerated(EnumType.STRING)
	@Field(type = FieldType.Keyword)
	private Relationship relationship;

	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy", lenient = OptBoolean.TRUE)
	@Field(type = FieldType.Date)
	private Date dob;

	@Field(type = FieldType.Text)
	private String occupation;

	// @ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	// @Field(type = FieldType.Integer)
	private Employeedocument employee;

	@CreatedDate
	// @Temporal(TemporalType.TIMESTAMP)
	@Field(type = FieldType.Date)
	private Date createdDate;

	@LastModifiedDate
	// @Temporal(TemporalType.TIMESTAMP)
	@Field(type = FieldType.Date)
	private Date modifiedDate;

	@Field(type = FieldType.Integer)
	private Integer employId;
	
	//List<FamilyInfo> list = new ArrayList<>();

	public FamilyInfoDocument(FamilyInfo familyInfo) {
		super();
		this.id = familyInfo.getId();
		this.name = familyInfo.getName();
		this.relationship = familyInfo.getRelationship();
		this.employId = familyInfo.getEmployee().getEmployId();
		this.dob = familyInfo.getDob();
		this.occupation = familyInfo.getOccupation();
		this.createdDate = familyInfo.getCreatedDate();
		this.modifiedDate = familyInfo.getModifiedDate();
		//list.add(familyInfo);
	}


	public Employeedocument getEmployee() {
		return employee;
	}

	public void setEmployee(Employeedocument employee) {
		this.employee = employee;
	}

	public FamilyInfoDocument() {

	}

	public FamilyInfoDocument(Integer id, String name, Relationship relationship, Date dob, String occupation,
			Employeedocument employee, Date createdDate, Date modifiedDate, Integer employId) {
		super();
		this.id = id;
		this.name = name;
		this.relationship = relationship;
		this.dob = dob;
		this.occupation = occupation;
		this.employee = employee;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.employId = employId;
	}

	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public Date getDob() {
		return dob;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	@Override
	public String toString() {
		return "FamilyInfo [id=" + id + ", name=" + name + ", relationship=" + relationship + ", dob=" + dob
				+ ", occupation=" + occupation + ", employee=" + employee + "]";
	}

}
