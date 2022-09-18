package com.formation.payMyBuddy.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.formation.payMyBuddy.model.Utilisateur;

public interface IUtilisateurService {

	public Iterable<Utilisateur> getUtilisateurs();

	public Optional<Utilisateur> getUtilisateurByEmail(String email);
	
	public String dejaConnecte(Model model, HttpSession session);
}
