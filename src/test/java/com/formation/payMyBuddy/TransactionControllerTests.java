package com.formation.payMyBuddy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MockHttpSession session;

	@Test
	public void testListTransactions() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/transactions").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testListTransactionsNotConnected() throws Exception {
		session.setAttribute("email", null);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/transactions/1").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPostTransaction() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transactions").session(session)
				.param("montant", "100.0").param("description", "description").param("connexion", "deux@test.com");
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
