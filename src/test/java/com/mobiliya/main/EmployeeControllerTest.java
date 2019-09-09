package com.mobiliya.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiliya.bean.Employee;
import com.mobiliya.controller.AuthRestAPIs;
import com.mobiliya.controller.EmployeeController;
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
		request.setUsername("adamgkz");
		request.setPassword("123456789");
		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(MockMvcRequestBuilders.get("/employee").header("Authorization", "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getEmployeeDetails() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("adamgkz");
		request.setPassword("123456789");

		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/employee/{empId}", 102).header("Authorization", "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.empId").value(102));
	}

	@Test
	public void addEmployee() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");

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
	}

	@Test
	public void updateEmployee() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");

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
	}

	@Test
	public void deleteEmployee() throws Exception {
		JwtRequest request = new JwtRequest();
		request.setUsername("thomasgkz");
		request.setPassword("123456789");

		String accessToken = jwtAuthenticationController.createAuthenticationToken(request);
		mockMvc.perform(MockMvcRequestBuilders.delete("/employee/101").header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
