package com.mobiliya.dao;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import com.mobiliya.bean.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Serializable> {

	void deleteByEmpId(int id);

	Employee findByEmpId(int id);
}
