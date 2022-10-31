package com.formation.payMyBuddy;

import java.util.Random;

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
public class UtilisateurControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MockHttpSession session;

	@Test
	public void testAcceuil() throws Exception {
		session.setAttribute("email", null);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testAcceuilDejaConnecte() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testInscription() throws Exception {
		session.setAttribute("email", null);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/inscription").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testInscriptionDejaConnecte() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/inscription").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	}
	
	@Test
	public void testConnexion() throws Exception {
		session.setAttribute("email", null);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/connexion").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testConnexionDejaConnecte() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/connexion").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	}
	
	@Test
	public void testDeconnexion() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/deconnexion").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testConnexionsNonConnecte() throws Exception {
		session.setAttribute("email", null);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/connexions").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testConnexions() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/connexions").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testProfilNonConnecte() throws Exception {
		session.setAttribute("email", null);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/profil").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testProfil() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/profil").session(session);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPostInscription() throws Exception {
		session.setAttribute("email", null);
		Random rand = new Random();
		String randomEmail = "test"+ rand.nextDouble(999999) + "@inscription.com"; // génère un nouvel email à chaque fois
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/inscription").session(session)
				.param("nom", "testInscription").param("email", randomEmail).param("motDePasse", "1234");
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	}
	
	@Test
	public void testPostConnexion() throws Exception {
		session.setAttribute("email", null);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/connexion").session(session)
				.param("remember", "vrai").param("email", "un@test.com").param("motDePasse", "1234");
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	}

	@Test
	public void testPostConnexionsContactInexistant() throws Exception {
		session.setAttribute("email", "un@test.com");
		Random rand = new Random();
		String randomEmail = "test"+ rand.nextDouble(999999) + "@inscription.com"; // génère un nouvel email à chaque fois
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/connexions").session(session)
				.param("emailContact", randomEmail);
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPostProfil() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/profil").session(session)
				.param("nom", "testProfil").param("motDePasse", "1234").param("montant", "0.0");
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPostCredit() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/profil").session(session)
				.param("nom", "testProfil").param("motDePasse", "1234").param("montant", "100.0").param("creditDebit", "credit").param("compteBancaire", "TestCredit");
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPostDebit() throws Exception {
		session.setAttribute("email", "un@test.com");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/profil").session(session)
				.param("nom", "testProfil").param("motDePasse", "1234").param("montant", "1.0").param("creditDebit", "debit").param("compteBancaire", "TestDebit");
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}


}
