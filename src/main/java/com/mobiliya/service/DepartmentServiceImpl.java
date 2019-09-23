package com.mobiliya.service;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobiliya.bean.Department;
import com.mobiliya.bean.Employee;
import com.mobiliya.bean.RecordNotFoundException;
import com.mobiliya.dao.DepartmentRepository;
import com.mobiliya.dto.DepartmentDTO;
import com.mobiliya.dto.EmployeeDTO;

@Service("departmentService")
@Transactional

public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	public void addDepartment(DepartmentDTO departmentDTO) {
		// Convert DTO Data to Entity Data
		Department department = modelMapper.map(departmentDTO, Department.class);
		departmentRepository.save(department);
	}

	public DepartmentDTO getDepartment(int id) {
		Department department = departmentRepository.findByDepartmentId(id)
				.orElseThrow(() -> new RecordNotFoundException("Department id " + id + " does not exist"));
		// Convert Entity Data to DTO data.
		DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
		return departmentDTO;
	}

	public List<DepartmentDTO> getDepartments() {
		List<Department> department = departmentRepository.findAll();
		Type listType = new TypeToken<List<DepartmentDTO>>() {
		}.getType();
		// Convert list of Entity to list of DTO
		List<DepartmentDTO> departmentDTOs = modelMapper.map(department, listType);
		return departmentDTOs;
	}

	public void deleteDepartment(int id) {
		departmentRepository.deleteByDepartmentId(id);
	}

	@Override
	public void updateDepartment(DepartmentDTO departmentDTO, int departmentId) {
		Department department = departmentRepository.findByDepartmentId(departmentId)
				.orElseThrow(() -> new RecordNotFoundException("Department id " + departmentId + " does not exist"));
		department.setDepartmentName(departmentDTO.getDepartmentName());
		Type listType = new TypeToken<List<Employee>>() {
		}.getType();
		List<Employee> employees = modelMapper.map(new HashSet(department.getEmployees()), listType);
		department.setEmployees(new HashSet(employees));
	}

}
