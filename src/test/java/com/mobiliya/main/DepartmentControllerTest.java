package com.mobiliya.main;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiliya.bean.Department;
import com.mobiliya.bean.JwtRequest;
import com.mobiliya.controller.AuthRestAPIs;
import com.mobiliya.controller.DepartmentController;
import com.mobiliya.dto.DepartmentDTO;
import com.mobiliya.dto.EmployeeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	DepartmentController departmentController;

	@Autowired
	AuthRestAPIs jwtAuthenticationController;

	@Test
	public void testDgetDepartments() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("adamgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(MockMvcRequestBuilders.get("/department").header("Authorization", "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testCgetDepartment() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("adamgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);

		mockMvc.perform(MockMvcRequestBuilders.get("/department/101").header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testAaddDepartment() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		Department obj = new Department();
		obj.setDepartmentId(101);
		obj.setDepartmentName("IT");

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/department")
				.header("Authorization", "Bearer " + accessToken).content(new ObjectMapper().writeValueAsString(obj))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		mvcResult.getResponse().getContentAsString();
	}

	@Test
	public void testBupdateDepartment() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmpId(1);
		employee.setEmpName("Sangeeta");
		Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();
		employees.add(employee);
		DepartmentDTO department = new DepartmentDTO();
		department.setDepartmentId(101);
		department.setDepartmentName("HR");
		department.setEmployees(employees);
		mockMvc.perform(MockMvcRequestBuilders.put("/department/{departmentId}", 101)
				.header("Authorization", "Bearer " + accessToken)
				.content(new ObjectMapper().writeValueAsString(department)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void testEdeleteDepartment() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(MockMvcRequestBuilders.delete("/department/101")
				.header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound());

	}

}
