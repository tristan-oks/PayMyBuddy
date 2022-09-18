package com.formation.payMyBuddy.service.implementation;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.repository.IUtilisateurRepository;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Service
public class UtilisateurServiceImplementation implements IUtilisateurService {

	@Autowired
	private IUtilisateurRepository utilisateurRepo;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Iterable<Utilisateur> getUtilisateurs() {
		return utilisateurRepo.findAll();
	}

	public Optional<Utilisateur> getUtilisateurByEmail(String email) {
		return utilisateurRepo.findById(email);
	}

	public String dejaConnecte(Model model, HttpSession session) {
		logger.info("Déjà Connecté");
		Optional<Utilisateur> optUtilisateur = getUtilisateurByEmail(session.getAttribute("email").toString());
		String nom = optUtilisateur.get().getNom();
		model.addAttribute("nom", nom);
		return "connecte";
	}

}
