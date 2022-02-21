package com.portal.sportsevent.api.resource.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.sportsevent.api.dto.UserResponseDto;
import com.portal.sportsevent.model.User;
import com.portal.sportsevent.repository.UserRepository;

import lombok.val;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.properties"})
@AutoConfigureMockMvc(addFilters = false)
public class UserResourceImplTest {
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;
	
	@Test
	@DisplayName("Teste - GET /api/user/list : List all of users")
	void listTest() throws Exception {
		 save();

		val result = mockMvc.perform(get("/api/user/list")).andExpect(status().isOk()).andDo(print()).andReturn();
		  
		val response = result.getResponse().getContentAsString();
		  
		val dto = new ObjectMapper().readValue(response, new TypeReference<List<UserResponseDto>>(){});
		 
		assertTrue(dto.size() > 0);
		assertTrue(dto.stream().findFirst().get().getId() > 0);
	}
	
	@Test
	@DisplayName("Teste - POST /api/user/save : Save users")
	void saveTest() throws Exception {
		
		val user  = createUser();

		val result = mockMvc.perform(
				post("/api/user/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(status().isCreated())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		val dto = new ObjectMapper()
				.readValue(response, UserResponseDto.class);

		assertFalse(ObjectUtils.isEmpty(dto));
		assertTrue(dto.getId() > 0);
	}
	
	@Test
	@DisplayName("Teste - GET /api/user/find/{id} : Get user by id")
	void findByIdest() throws Exception {
		val user = save();

		val result = mockMvc.perform(get("/api/user/find/{id}", user.getId()))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		val dto = new ObjectMapper()
				.readValue(response, UserResponseDto.class);

		assertFalse(ObjectUtils.isEmpty(dto));
		assertTrue(dto.getId() > 0);
	}
	
	@Test
	@DisplayName("Teste - PUT /api/user/update/{id} : Update user")
	void updateTest() throws Exception {
		val user = save();
		
		user.setEmail("jb@gmail.com");
		
		MockHttpServletRequestBuilder builder = 
				MockMvcRequestBuilders.put("/api/user/update/{id}", user.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(user));
		
		val mvcResult = mockMvc.perform(builder)
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
				
		val response = mvcResult.getResponse().getContentAsString();
		
		val eventModify = new ObjectMapper().readValue(response, UserResponseDto.class);
		
		assertFalse(ObjectUtils.isEmpty(eventModify));
		assertTrue(eventModify.getEmail().equalsIgnoreCase(user.getEmail()));
		
	}
	
	@Test
	@DisplayName("Teste - DELETE /api/user/remove/{id} : Remove user")
	void removeTest() throws Exception {
		val user = save();
		
		mockMvc.perform(delete("/api/user/remove/{id}", user.getId()))
				.andExpect(status().isNoContent())
				.andDo(print())
				.andReturn();
	}
		
	User save() {
		return userRepository.save(createUser());
	}
	
	User createUser() {
		User user = new User();
			
		user.setFullName("John Batista");
		user.setNickname("JB");
		user.setEmail("johnbatista@gmail.com");
		user.setPassword("123456");
		
		return user;
	}
}
