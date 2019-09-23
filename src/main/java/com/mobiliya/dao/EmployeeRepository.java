package com.mobiliya.dao;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.mobiliya.bean.Employee;

//Spring Data Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {

	// Delete Employee by Empolyee ID.
	void deleteByEmpId(int id);

	// Find Employee by Empolyee ID.
	Optional<Employee> findByEmpId(int id);
}
