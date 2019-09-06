package com.mobiliya.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.mobiliya.bean.Department;

public interface DepartmentRepository extends CrudRepository<Department, Serializable> {

	void deleteByDepartmentId(int id);

	Department findByDepartmentId(int i);

}
