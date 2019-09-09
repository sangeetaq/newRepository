package com.mobiliya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobiliya.bean.Department;
import com.mobiliya.service.IDepartmentService;

@RestController
public class DepartmentController {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@Autowired
	IDepartmentService departmentService;

	@GetMapping("/department/{departmentId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Department> getDepartment(@PathVariable("departmentId") int id) {
		Department department = departmentService.getDepartment(id);
		ResponseEntity<Department> response = new ResponseEntity<Department>(department, HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/department")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
		ResponseEntity<Department> response = new ResponseEntity<Department>(department, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/department")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Department>> getDepartments() {
		List<Department> department = departmentService.getDepartments();
		ResponseEntity<List<Department>> response = new ResponseEntity<List<Department>>(department, HttpStatus.OK);
		return response;
	}

	@PutMapping(value = "/department")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
		ResponseEntity<Department> response = new ResponseEntity<Department>(department, HttpStatus.CREATED);
		return response;
	}

	@DeleteMapping("/department/{departmentId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Department> deleteDepartment(@PathVariable("departmentId") int departmentId) {
		departmentService.deleteDepartment(departmentId);
		ResponseEntity<Department> response = new ResponseEntity<Department>(HttpStatus.FOUND);
		return response;
	}

}
