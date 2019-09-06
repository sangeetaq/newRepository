package com.mobiliya.main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiliya.bean.Department;
import com.mobiliya.controller.AuthRestAPIs;
import com.mobiliya.controller.DepartmentController;
import com.mobiliya.controller.EmployeeController;
import com.mobiliya.main.SpringBootWebApplication;
import com.mobiliya.service.DepartmentServiceImpl;
import com.mobiliya.service.IDepartmentService;
import com.mobiliya.util.JwtRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	DepartmentController departmentController;

	@Autowired
	AuthRestAPIs jwtAuthenticationController;

	@Test
	public void getDepartments() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("adamgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		List<Department> departments = new ArrayList<Department>();
		Department department = new Department();
		department.setDepartmentId(101);
		department.setDepartmentName("IT");
		departments.add(department);
		mockMvc.perform(MockMvcRequestBuilders.get("/department").header("Authorization", "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void getDepartment() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("adamgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);

		mockMvc.perform(MockMvcRequestBuilders.get("/department/102").header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void addDepartment() throws Exception {
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
		String content = mvcResult.getResponse().getContentAsString();
	}

	@Test
	public void updateDepartment() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		Department department = new Department();
		department.setDepartmentId(101);
		department.setDepartmentName("IT");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put("/department").header("Authorization", "Bearer " + accessToken)
						.content(new ObjectMapper().writeValueAsString(department))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
	}

	@Test
	public void deleteDepartment() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(MockMvcRequestBuilders.delete("/department/102")
				.header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound());

	}

}
