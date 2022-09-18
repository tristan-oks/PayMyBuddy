package com.formation.payMyBuddy.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.repository.IUtilisateurRepository;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private IUtilisateurService utilisateurService;
	@Autowired
	private IUtilisateurRepository utilisateurRepository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/utilisateurs")
	public String listUtilisateurs(Model model, HttpSession session) {
		Iterable<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();
		logger.info("liste des utilisateurs");
		model.addAttribute("utilisateurs", utilisateurs);
		session.invalidate();
		return "utilisateurs";
	}

	@GetMapping("/connexions")
	public String listConnexions(Model model, HttpSession session) {

		logger.info("Liste des connexions");
		if (session.getAttribute("email") == null) {
			logger.info("nonConnecte");
			return "nonConnecte";
		}
		Optional<Utilisateur> optUtilisateur = utilisateurService
				.getUtilisateurByEmail(session.getAttribute("email").toString());
		Utilisateur utilisateur = optUtilisateur.get();
		model.addAttribute("utilisateur", utilisateur);
		Iterable<Utilisateur> connexions = utilisateur.getConnexions();

		model.addAttribute("connexions", connexions);
		return "connexions";
	}

	@GetMapping("/inscription")
	public String formulaireInscription(Model model, HttpSession session) {

		logger.info("affichage page Inscription");
		if (session.getAttribute("email") != null) {
			return utilisateurService.dejaConnecte(model, session);
		}

		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		String messageErreur = "";
		model.addAttribute("messageErreur", messageErreur);
		return "inscription";
	}

	@PostMapping("/inscription")
	public String inscription(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model,
			HttpSession session) {

		if (session.getAttribute("email") != null) {
			return utilisateurService.dejaConnecte(model, session);
		}
		if (utilisateurRepository.findById(utilisateur.getEmail()).isPresent()) {
			String messageErreur = "Utilisateur existant !";
			model.addAttribute("messageErreur", messageErreur);
			return "inscription";
		}
//		if (utilisateur.getEmail().isBlank() || utilisateur.getNom().isBlank()
//				|| utilisateur.getMotDePasse().isBlank()) {
//			String messageErreur = "Veuiller renseigner tous les champs !";
//			model.addAttribute("messageErreur", messageErreur);
//			return "inscription";
//		}
		utilisateur.setSolde(0);
		utilisateurRepository.save(utilisateur);
		logger.info("utilisateur inscrit : " + utilisateur.toString());
		String messageErreur = "utilisateur inscrit : " + utilisateur.getNom();
		model.addAttribute("messageErreur", messageErreur);
		return "acceuil";
	}

	@GetMapping("/")
	public String acceuil(Model model, HttpSession session) {
		logger.info("page d'acceuil");
		if (session.getAttribute("email") != null) {
			return utilisateurService.dejaConnecte(model, session);
		}
		String messageErreur = "";
		model.addAttribute("messageErreur", messageErreur);
		return "acceuil";
	}

	@GetMapping("/connexion")
	public String formulaireConnexion(Model model, HttpSession session) {

		logger.info("affichage page connexion");
		if (session.getAttribute("email") != null) {
			return utilisateurService.dejaConnecte(model, session);
		}
		String remember = "faux";
		//session.setAttribute("remember", remember);
		model.addAttribute("remember", remember);
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		String messageErreur = "";
		model.addAttribute("messageErreur", messageErreur);
		return "connexion";
	}

	@PostMapping("/connexion")
	public String connexion(@ModelAttribute("utilisateur") Utilisateur utilisateur,
			@ModelAttribute("remember") String remember, Model model, HttpSession session) {

		logger.info("connexion");
		if (!utilisateurRepository.findById(utilisateur.getEmail()).isPresent()) {
			logger.info("Utilisateur inexistant !");
			String messageErreur = "Utilisateur inexistant !";
			model.addAttribute("messageErreur", messageErreur);
			return "connexion";
		}
		Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurByEmail(utilisateur.getEmail());
		Utilisateur utilisateurAConnecter = optUtilisateur.get();

		logger.info("utilisateur mot de passe BDD : " + utilisateurAConnecter.getMotDePasse()
				+ ", utilisateur mot de passe entré : " + utilisateur.getMotDePasse());
		
		if (!utilisateurAConnecter.getMotDePasse().equals(utilisateur.getMotDePasse())) {
			String messageErreur = "Mot de passe incorrect !";
			logger.info(messageErreur);
			model.addAttribute("messageErreur", messageErreur);
			return "connexion";
		}

		session.setAttribute("email", utilisateur.getEmail());
		logger.debug("remember : " + remember);
		if (remember.equals("vrai")) {
			session.setMaxInactiveInterval(60 * 60 * 24 * 30 * 6); // rester connecté 6 mois
			logger.info("Rester connecté : " + session.getMaxInactiveInterval() + "seconds");
		} else {
			session.setMaxInactiveInterval(60 * 10); // rester connecté 10 minutes
			logger.info("Ne pas Rester connecté : " + session.getMaxInactiveInterval() + "seconds");
		}

		logger.info("utilisateur connecté : " + utilisateur.toString());
		return utilisateurService.dejaConnecte(model, session);
	}

	@GetMapping("/connecte")
	public String connecte(Model model, HttpSession session) {
		logger.info("Page Connecté");
		if (session.getAttribute("email") == null) {
			logger.info("nonConnecte");
			return "nonConnecte";
		}
		return utilisateurService.dejaConnecte(model, session);
	}
	
	@GetMapping("/deconnexion")
	public String deconnexion(Model model, HttpSession session) {
		logger.info("deconnexion");
		session.invalidate();
		String messageErreur = "Vous êtes déconnecté";
		model.addAttribute("messageErreur", messageErreur);
		return "acceuil";
	}
}
