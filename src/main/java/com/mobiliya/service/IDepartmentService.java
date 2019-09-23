package com.mobiliya.service;

import java.util.List;

import com.mobiliya.bean.Department;
import com.mobiliya.dto.DepartmentDTO;

public interface IDepartmentService {

	void addDepartment(DepartmentDTO department);

	DepartmentDTO getDepartment(int id);

	List<DepartmentDTO> getDepartments();

	void deleteDepartment(int id);

	void updateDepartment(DepartmentDTO department, int departmentId);

}
