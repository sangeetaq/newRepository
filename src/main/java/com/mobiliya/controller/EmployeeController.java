package com.mobiliya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobiliya.bean.Employee;
import com.mobiliya.service.IEmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable("empId") int id) {
		Employee employee = employeeService.getEmployee(id);
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return response;

	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employee = employeeService.getEmployees();
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(employee, HttpStatus.CREATED);
		return response;
	}

	@PostMapping(value = "/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		return response;
	}

	@PutMapping(value = "/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		return response;
	}

	@DeleteMapping("/employee/{empId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("empId") int id) {
		employeeService.deleteEmployee(id);
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(HttpStatus.OK);
		return response;
	}

}
