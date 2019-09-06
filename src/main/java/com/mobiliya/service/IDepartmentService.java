package com.mobiliya.service;

import java.util.List;

import com.mobiliya.bean.Department;

public interface IDepartmentService {

	void addDepartment(Department department);

	Department getDepartment(int id);

	List<Department> getDepartments();

	void deleteDepartment(int id);

}
