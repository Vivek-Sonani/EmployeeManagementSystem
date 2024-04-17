package com.springboot.employee.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@EntityListeners(value = {AuditListner.class, AuditingEntityListener.class})
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer employId;

  @Column
  String firstname;

  @Column
  String middlename;

  @Column
  String lastname;

  @Column
  // @Length
  String email;

  @Column
  String position;

  @Column
  int contactNo;

  @Column
  Float salary;

  @Column(name = "created_date", nullable = false, updatable = false)
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Column(name = "modified_date", nullable = false)
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifiedDate;

  @Column(name = "created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "modified_by")
  @LastModifiedBy
  private String modifiedBy;

  @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Address address;

  @JsonManagedReference
  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
  private List<FamilyInfo> familyInfo;

  public List<FamilyInfo> getFamilyInfo() {
    return familyInfo;
  }

  public void setFamilyInfo(List<FamilyInfo> familyInfo) {
    this.familyInfo = familyInfo;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Employee() {
    this.setEmployId((int) Math.random());
  }

  public Integer getEmployId() {
    return employId;
  }

  public void setEmployId(Integer employId) {
    this.employId = employId;
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
    return "Employee [employId=" + employId + ", firstname=" + firstname + ", middlename="
        + middlename + ", lastname=" + lastname + ", email=" + email + ", position=" + position
        + ", contactNo=" + contactNo + ", salary=" + salary + ", createdBy=" + createdBy
        + ", modifiedBy=" + modifiedBy + "]";
  }

}
