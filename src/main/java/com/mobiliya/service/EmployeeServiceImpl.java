package com.mobiliya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobiliya.bean.Employee;
import com.mobiliya.dao.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee getEmployee(int id) {
		return employeeRepository.findByEmpId(id);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public void deleteEmployee(int id) {
		employeeRepository.deleteByEmpId(id);
	}

	public Object add(double d, double e) {
		return d + e;
	}

}
