package com.mobiliya.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Department Entity 
@Entity
public class Department {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@Column(name = "department_id")
	private int departmentId;

	@Column(name = "department_name")
	private String departmentName;

	// Department has many to many bidirectional relationship with Employee
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Employee_Department", joinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "emp_id", "department_id" }) })
	@JsonIgnoreProperties("departments")
	private Set<Employee> employees;

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ",  departmentName=" + departmentName + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
}
