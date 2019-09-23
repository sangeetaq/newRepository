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

import com.mobiliya.bean.Department;
import com.mobiliya.dto.DepartmentDTO;
import com.mobiliya.service.IDepartmentService;

//Department Controller which has Crud REST API's
@RestController
@Validated
public class DepartmentController {

	private static Logger logger = LogManager.getLogger(DepartmentController.class);

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@Autowired
	IDepartmentService departmentService;

	// User and Admin can View Department details.
	@GetMapping("/department/{departmentId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("departmentId") int id) {
		logger.info("Starts getDepartment API ");
		DepartmentDTO department = departmentService.getDepartment(id);
		ResponseEntity<DepartmentDTO> response = new ResponseEntity<DepartmentDTO>(department, HttpStatus.OK);
		logger.info("Ends getDepartment API ");
		return response;
	}

	// Admin can add Department.
	@PostMapping(value = "/department")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
		logger.info("Starts addDepartment API ");
		departmentService.addDepartment(departmentDTO);
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		logger.info("Ends addDepartment API ");
		return response;
	}

	// User and Admin can view all Departments.
	@GetMapping("/department")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<DepartmentDTO>> getDepartments() {
		logger.info("Starts getDepartments API ");
		List<DepartmentDTO> department = departmentService.getDepartments();
		ResponseEntity<List<DepartmentDTO>> response = new ResponseEntity<List<DepartmentDTO>>(department,
				HttpStatus.OK);
		logger.info("Ends getDepartments API ");
		return response;
	}

	// Admin can update Department.
	@PutMapping(value = "/department/{departmentId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> updateDepartment(@RequestBody DepartmentDTO department,
			@PathVariable("departmentId") int departmentId) {
		logger.info("Starts updateDepartment API ");
		departmentService.updateDepartment(department, departmentId);
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		logger.info("Ends updateDepartment API ");
		return response;
	}

	// Admin can Delete Department.
	@DeleteMapping("/department/{departmentId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("departmentId") int departmentId) {
		logger.info("Starts deleteDepartment API ");
		departmentService.deleteDepartment(departmentId);
		ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.FOUND);
		logger.info("Ends deleteDepartment API ");
		return response;
	}

}
