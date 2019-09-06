package com.mobiliya.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Employee {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	@Column(name = "empId")
	private int empId;

	@Column(name = "empName")
	private String empName;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Employee_Department", joinColumns = { @JoinColumn(name = "emp_id") }, inverseJoinColumns = {
			@JoinColumn(name = "department_id") })
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
