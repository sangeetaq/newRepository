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
import com.mobiliya.dao.EmployeeRepository;
import com.mobiliya.dto.EmployeeDTO;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public EmployeeDTO getEmployee(int id) {
		Employee employee = employeeRepository.findByEmpId(id)
				.orElseThrow(() -> new RecordNotFoundException("Employee id " + id + " does not exist"));
		// Convert Entity Data to DTO Data
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		return employeeDTO;
	}

	public void addEmployee(EmployeeDTO employeeDTO) {
		// Convert DTO Data to Entity Data
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		employeeRepository.save(employee);
	}

	public List<EmployeeDTO> getEmployees() {
		List<Employee> employee = employeeRepository.findAll();
		Type listType = new TypeToken<List<EmployeeDTO>>() {
		}.getType();
		// Convert Entity List to DTO List Data.
		List<EmployeeDTO> employeeDTOS = modelMapper.map(employee, listType);
		return employeeDTOS;
	}

	public void deleteEmployee(int id) {
		employeeRepository.deleteByEmpId(id);
	}

	@Override
	public void updateEmployee(EmployeeDTO employee, int empId) {
		Employee employeeBean = employeeRepository.findByEmpId(empId)
				.orElseThrow(() -> new RecordNotFoundException("Department id " + empId + " does not exist"));
		employeeBean.setEmpName(employee.getEmpName());
		Type listType = new TypeToken<List<Department>>() {
		}.getType();
		List<Department> departments = modelMapper.map(new HashSet(employee.getDepartments()), listType);
		employeeBean.setDepartments(new HashSet(departments));
		employeeRepository.save(employeeBean);
	}

}
