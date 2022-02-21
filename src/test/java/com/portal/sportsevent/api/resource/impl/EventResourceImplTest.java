package com.portal.sportsevent.api.resource.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
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
import com.portal.sportsevent.api.dto.EventResponseDto;
import com.portal.sportsevent.model.Address;
import com.portal.sportsevent.model.Event;
import com.portal.sportsevent.model.User;
import com.portal.sportsevent.repository.EventRepository;
import com.portal.sportsevent.repository.UserRepository;

import lombok.val;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.properties"})
@AutoConfigureMockMvc(addFilters = false)
public class EventResourceImplTest {
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@DisplayName("Teste - GET /api/event/list : List all of events")
	void listTest() throws Exception {
		 save();

		val result = mockMvc.perform(get("/api/event/list")).andExpect(status().isOk()).andDo(print()).andReturn();
		  
		val response = result.getResponse().getContentAsString();
		  
		val dto = new ObjectMapper().readValue(response, new TypeReference<List<EventResponseDto>>(){});
		 
		assertTrue(dto.size() > 0);
		assertTrue(dto.stream().findFirst().get().getId() > 0);
	}
	
	@Test
	@DisplayName("Teste - POST /api/event/save : Save events")
	void saveTest() throws Exception {
		
		val event  = createEvent();

		val result = mockMvc.perform(
				post("/api/event/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(event)))
				.andExpect(status().isCreated())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		val dto = new ObjectMapper()
				.readValue(response, EventResponseDto.class);

		assertFalse(ObjectUtils.isEmpty(dto));
		assertTrue(dto.getId() > 0);
	}
	
	@Test
	@DisplayName("Teste - GET /api/event/find/{id} : Get event by id")
	void findByIdest() throws Exception {
		val event = save();

		val result = mockMvc.perform(get("/api/event/find/{id}", event.getId()))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		val dto = new ObjectMapper()
				.readValue(response, EventResponseDto.class);

		assertFalse(ObjectUtils.isEmpty(dto));
		assertTrue(dto.getId() > 0);
	}
	
	@Test
	@DisplayName("Teste - PUT /api/event/update/{id} : Update event")
	void updateTest() throws Exception {
		val event = save();
		
		event.setDescription("New description");
		
		MockHttpServletRequestBuilder builder = 
				MockMvcRequestBuilders.put("/api/event/update/{id}", event.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(event));
		
		val mvcResult = mockMvc.perform(builder)
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
				
		val response = mvcResult.getResponse().getContentAsString();
		
		val eventModify = new ObjectMapper().readValue(response, EventResponseDto.class);
		
		assertFalse(ObjectUtils.isEmpty(eventModify));
		assertTrue(eventModify.getDescription().equalsIgnoreCase(event.getDescription()));
		
	}
	
	@Test
	@DisplayName("Teste - DELETE /api/remove/{id} : Remove event")
	void removeTest() throws Exception {
		val event = save();
		
		mockMvc.perform(delete("/api/event/remove/{id}", event.getId()))
				.andExpect(status().isNoContent())
				.andDo(print())
				.andReturn();
	}
	
	@Test
	@DisplayName("Teste - POST /api/event/link/user/{id} : Link user to event")
	void linkUser() throws Exception {
		
		val event  = save();
		
		val user = getUser();

		val result = mockMvc.perform(
				post("/api/event/link/user/{id}", user.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(event)))
				.andExpect(status().isCreated())
				.andDo(print())
				.andReturn();

		val response = result.getResponse().getContentAsString();

		val dto = new ObjectMapper()
				.readValue(response, EventResponseDto.class);

		assertFalse(ObjectUtils.isEmpty(dto));
		assertTrue(dto.getId() > 0);
		assertFalse(dto.getUsers().isEmpty());
		assertTrue(dto.getUsers().get(0).getId() > 0);
	}
		
	Event save() {
		return eventRepository.save(createEvent());
	}
	
	Event createEvent() {
		 Long datetime = System.currentTimeMillis();
	     Timestamp timestamp = new Timestamp(datetime);
	     
	    Address address = new Address();
	    
	    address.setZipCode("40444-000");
	    address.setPublicArea("Holand street");
	    address.setNumber(30);
	    address.setNeighborhood("Comercio");
	    address.setCity("SSA");
	    address.setState("BA");
	    
		Event event = new Event();
		
		event.setDescription("Soccer on the street");
		event.setDateTime(timestamp);
		event.setAddress(address);
		
		return event;
	}
	
	User getUser() {
		User user = new User();
		
		user.setFullName("John Batista");
		user.setNickname("JB");
		user.setEmail("johnbatista@gmail.com");
		user.setPassword("123456");
		
		return userRepository.save(user);
	}
}
