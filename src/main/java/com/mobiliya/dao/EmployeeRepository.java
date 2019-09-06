package com.mobiliya.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import com.mobiliya.bean.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Serializable> {

	void deleteByEmpId(int id);

	Employee findByEmpId(int id);
}
