package com.formation.payMyBuddy.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private IUtilisateurService utilisateurService;

	@GetMapping("/utilisateurs")
	public String listUtilisateurs(Model model, HttpSession session) {
		Iterable<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();
		System.out.println("test html utilisateurs started");
		model.addAttribute("utilisateurs", utilisateurs);
		session.setAttribute("test", "password");
		return "utilisateurs";
	}

	@GetMapping("/connexions")
	public String listConnexions(Model model, HttpSession session) {

		System.out.println("test html connexions started");
		System.out.println(session.getAttribute("test"));
		Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurByEmail("un@test.com");
		Utilisateur utilisateur = optUtilisateur.get();
		model.addAttribute("utilisateur", utilisateur);
		Iterable<Utilisateur> connexions = utilisateur.getConnexions();

		model.addAttribute("connexions", connexions);
		return "connexions";
	}
}
