package com.github.evgdim.tdd;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.evgdim.tdd.controller.PersonController;
import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.service.PersonServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { PersonController.class })
public class O3_MockMvcTests {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonServiceImpl businessService;
	
	@Test
	public void personOfTheYear_should_returnOKTestPerson() throws Exception {
		when(businessService.checkPerson(ArgumentMatchers.any())).thenReturn(new Person(1L, "TestPerson", 55));
		mockMvc.perform(get("/people/1"))
			   .andExpect(status().is2xxSuccessful())
			   .andExpect(jsonPath("$.name", equalTo("TestPerson")));
	}
}
