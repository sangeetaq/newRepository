package com.mobiliya.service;

import java.util.List;

import com.mobiliya.bean.Employee;

public interface IEmployeeService {

	Employee getEmployee(int id);

	void addEmployee(Employee employee);

	List<Employee> getEmployees();

	void deleteEmployee(int id);

}
