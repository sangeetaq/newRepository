package com.mobiliya.service;

import java.util.List;

import com.mobiliya.bean.Employee;
import com.mobiliya.dto.EmployeeDTO;

public interface IEmployeeService {

	EmployeeDTO getEmployee(int id);

	void addEmployee(EmployeeDTO employee);

	List<EmployeeDTO> getEmployees();

	void deleteEmployee(int id);

	void updateEmployee(EmployeeDTO employee, int id);

}
