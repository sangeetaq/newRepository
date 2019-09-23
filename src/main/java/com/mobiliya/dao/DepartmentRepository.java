package com.mobiliya.dao;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.mobiliya.bean.Department;

//Spring Data Repository
public interface DepartmentRepository extends JpaRepository<Department, Serializable> {

	// Delete Department by Department ID.
	void deleteByDepartmentId(int id);

	// Find Department by Department ID.
	Optional<Department> findByDepartmentId(int i);

}
