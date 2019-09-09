package com.mobiliya.main;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiliya.controller.AuthRestAPIs;
import com.mobiliya.util.JwtRequest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class JwtAuthenticationControllerTest {

	private MockMvc mockMvc;

	@MockBean
	AuthRestAPIs jwtAuthenticationController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(jwtAuthenticationController).build();
	}

	@Test
	public void createAuthenticationToken() throws Exception {
		String username = "javainuse";
		String password = "password";

		JwtRequest jwtRequest = new JwtRequest();
		jwtRequest.setPassword(password);
		jwtRequest.setUsername(username);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
				.content(new ObjectMapper().writeValueAsString(jwtRequest)).contentType(MediaType.APPLICATION_JSON));
		int status = resultActions.andReturn().getResponse().getStatus();
		assertEquals(200, status);

		String response = resultActions.andReturn().getResponse().getContentAsString();
		response = response.replace("{\"access_token\": \"", "");
		String token = response.replace("\"}", "");

		mockMvc.perform(MockMvcRequestBuilders.get("/department/102").header("Authorization", "Bearer " + token))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
