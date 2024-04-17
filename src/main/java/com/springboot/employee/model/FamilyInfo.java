package com.springboot.employee.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.OptBoolean;

@Entity
@EntityListeners(value = {AuditListner.class, AuditingEntityListener.class})
@Table(name = "family_info")
public class FamilyInfo {
  // define enum Relationship because number of fields are constant.
  enum Relationship {
    FATHER, MOTHER, BROTHER
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String name;

  @Column
  @Enumerated(EnumType.STRING)
  private Relationship relationship;

  @Column
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy", lenient = OptBoolean.TRUE)
  private Date dob;

  @Column
  private String occupation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  @JsonBackReference
  private Employee employee;

  @Column(name = "created_date", updatable = false)
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Column(name = "modified_date")
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifiedDate;

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public FamilyInfo() {
    super();
  }

  public FamilyInfo(Integer id, String name, Relationship relationship, Date dob,
      String occupation) {
    super();
    this.id = id;
    this.name = name;
    this.relationship = relationship;
    this.dob = dob;
    this.occupation = occupation;
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
    return "FamilyInfo [id=" + id + ", name=" + name + ", relationship=" + relationship + ", dob="
        + dob + ", occupation=" + occupation + ", employee=" + employee + "]";
  }

}
