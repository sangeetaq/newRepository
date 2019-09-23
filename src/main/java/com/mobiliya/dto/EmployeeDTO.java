package com.mobiliya.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobiliya.bean.Department;

public class EmployeeDTO {

	private int empId;

	private String empName;

	@JsonIgnoreProperties("employees")
	Set<DepartmentDTO> departmentDTO;

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

	public Set<DepartmentDTO> getDepartments() {
		return departmentDTO;
	}

	public void setDepartments(Set<DepartmentDTO> departments) {
		this.departmentDTO = departments;
	}

}
