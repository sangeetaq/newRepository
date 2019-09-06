package com.mobiliya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobiliya.bean.Department;
import com.mobiliya.dao.DepartmentRepository;

@Service("departmentService")
@Transactional

public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	public void addDepartment(Department department) {
		departmentRepository.save(department);
	}

	public Department getDepartment(int id) {
		Department department = departmentRepository.findByDepartmentId(id);
		return department;
	}

	public List<Department> getDepartments() {
		return (List<Department>) departmentRepository.findAll();
	}

	public void deleteDepartment(int id) {
		departmentRepository.deleteByDepartmentId(id);
	}

}
