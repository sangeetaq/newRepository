package com.mobiliya.main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.mobiliya.bean.Department;
import com.mobiliya.bean.Employee;
import com.mobiliya.controller.AuthRestAPIs;
import com.mobiliya.controller.EmployeeController;
import com.mobiliya.service.EmployeeServiceImpl;
import com.mobiliya.util.JwtAuthenticationEntryPoint;
import com.mobiliya.util.JwtRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	EmployeeController employeeController;

	@Autowired
	AuthRestAPIs jwtAuthenticationController;

	@Test
	public void getEmployees() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("javainuse");
		request.setPassword("password");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(MockMvcRequestBuilders.get("/employee").header("Authorization", "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getEmployeeDetails() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("javainuse");
		request.setPassword("password");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/employee/{empId}", 102).header("Authorization", "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addEmployee() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("javainuse");
		request.setPassword("password");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		Employee employee = new Employee();
		employee.setEmpId(101);
		employee.setEmpName("Sangeeta");

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/employee").header("Authorization", "Bearer " + accessToken)
						.content(new ObjectMapper().writeValueAsString(employee))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
	}

	@Test
	public void updateEmployee() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("javainuse");
		request.setPassword("password");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		Employee employee = new Employee();
		employee.setEmpId(101);
		employee.setEmpName("Sangeeta");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put("/employee").header("Authorization", "Bearer " + accessToken)
						.content(new ObjectMapper().writeValueAsString(employee))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
	}

	@Test
	public void deleteEmployee() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("javainuse");
		request.setPassword("password");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(MockMvcRequestBuilders.delete("/employee/102").header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
