package com.mobiliya.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobiliya.bean.Employee;
import com.mobiliya.dto.EmployeeDTO;
import com.mobiliya.service.IEmployeeService;

//Employee Controller which has Crud REST API's
@RestController
@Validated
public class EmployeeController {

	private static Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	IEmployeeService employeeService;

	// User and Admin can View Emplpoyee details.
	@GetMapping("/employee/{empId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<EmployeeDTO> getEmployeeDetails(@Validated @PathVariable("empId") int id) {
		logger.info("Starts getEmployeeDetails API ");
		EmployeeDTO employee = employeeService.getEmployee(id);
		ResponseEntity<EmployeeDTO> response = new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
		logger.info("Ends getEmployeeDetails API ");
		return response;
	}

	// User and Admin can view all Employees.
	@GetMapping("/employee")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		logger.info("Starts getEmployees API ");
		List<EmployeeDTO> employee = employeeService.getEmployees();
		ResponseEntity<List<EmployeeDTO>> response = new ResponseEntity<List<EmployeeDTO>>(employee, HttpStatus.OK);
		logger.info("Ends getEmployees API ");
		return response;
	}

	// Admin can Add Employee.
	@PostMapping(value = "/employee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> addEmployee(@Valid @RequestBody EmployeeDTO employee) {
		logger.info("Starts addEmployee API ");
		employeeService.addEmployee(employee);
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		logger.info("Ends addEmployee API ");
		return response;
	}

	// Admin can update Employee.
	@PutMapping(value = "/employee/{empId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> updateEmployee(@RequestBody EmployeeDTO employee, @PathVariable("empId") int id) {
		logger.info("Starts updateEmployee API ");
		employeeService.updateEmployee(employee, id);
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		logger.info("Ends updateEmployee API ");
		return response;
	}

	// Admin can Delete Employee.
	@DeleteMapping("/employee/{empId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("empId") int id) {
		logger.info("Starts deleteEmployee API ");
		employeeService.deleteEmployee(id);
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.OK);
		logger.info("Ends deleteEmployee API ");
		return response;
	}

}
