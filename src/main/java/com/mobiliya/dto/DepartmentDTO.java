package com.mobiliya.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobiliya.bean.Employee;

public class DepartmentDTO {

	@NotNull(message = "Department ID Not be Null")
	private int departmentId;

	@NotEmpty(message = "Department Name Not be Empty")
	@NotNull(message = "Department Name Not be Null")
	private String departmentName;

	@JsonIgnoreProperties("departments")
	private Set<EmployeeDTO> employees;

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

	public Set<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeDTO> employees) {
		this.employees = employees;
	}
}
