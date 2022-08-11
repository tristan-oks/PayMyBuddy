package com.formation.payMyBuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.service.IUtilisateurService;

@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

	@Autowired
	private IUtilisateurService utilisateurService;
	
	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Iterable<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();
		System.out.println("test started");
		for (Utilisateur user : utilisateurs) {
			System.out.println(user.getNom());
		}
		
	}
}
