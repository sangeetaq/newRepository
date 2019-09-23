package com.mobiliya.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Employee Entity 
@Entity
public class Employee {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@Column(name = "empId")
	@NotNull
	private int empId;

	@Column(name = "empName")
	@NotEmpty(message = "Employee Name should not be empty")
	private String empName;

	// Employee has many to many bidirectional relationship with Employee
	// @JsonIgnoreProperties avoid recurssion relationship
	@ManyToMany(cascade = { CascadeType.ALL }, targetEntity = Department.class)
	@JoinTable(name = "Employee_Department", joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
	@JsonIgnoreProperties("employees")
	Set<Department> departments;

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", departments=" + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

}
