package com.formation.payMyBuddy.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/")
	public ModelAndView acceuil(ModelMap model,
			@RequestParam(required = false, name = "message") String message, HttpSession session) {
		logger.info("page d'acceuil");
		if (session.getAttribute("email") != null) {
			String nom = utilisateurService.getNom(session.getAttribute("email").toString());
			model.addAttribute("nom", nom);
			return new ModelAndView("connecte", model);
		}
		if (message != null) {
			model.addAttribute("message", message);
			logger.info("message : " + message);
		} else {
			model.addAttribute("message", "");
		}

		return new ModelAndView("acceuil", model);
	}


	@GetMapping("/inscription")
	public ModelAndView formulaireInscription(ModelMap model, HttpSession session) {

		logger.info("affichage page Inscription");
		if (session.getAttribute("email") != null) {
			return new ModelAndView("redirect:/");
		}

		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		String message = "";
		model.addAttribute("message", message);
		return new ModelAndView("inscription", model);
	}

	@PostMapping("/inscription")
	public ModelAndView inscription(@ModelAttribute("utilisateur") Utilisateur utilisateur, ModelMap model,
			HttpSession session) {

		if (session.getAttribute("email") != null) {
			return new ModelAndView("redirect:/");
		}
		if (utilisateurRepository.findById(utilisateur.getEmail()).isPresent()) {
			String message = "Utilisateur existant !";
			model.addAttribute("message", message);
			return new ModelAndView("inscription", model);
		}
//		if (utilisateur.getEmail().isBlank() || utilisateur.getNom().isBlank()
//				|| utilisateur.getMotDePasse().isBlank()) {
//			String messageErreur = "Veuiller renseigner tous les champs !";
//			model.addAttribute("messageErreur", messageErreur);
//			return "inscription";
//		}
		utilisateur.setSolde(0);
		utilisateurRepository.save(utilisateur);
		String message = "utilisateur inscrit : " + utilisateur.getNom();
		model.addAttribute("message", message);
		return new ModelAndView("redirect:/", model);
	}

	@GetMapping("/connexion")
	public ModelAndView formulaireConnexion(ModelMap model, HttpSession session) {

		logger.info("affichage page connexion");
		if (session.getAttribute("email") != null) {
			return new ModelAndView("redirect:/");
		}
		String remember = "faux";
		// session.setAttribute("remember", remember);
		model.addAttribute("remember", remember);
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		String message = "";
		model.addAttribute("message", message);
		return new ModelAndView("connexion", model);
	}

	@PostMapping("/connexion")
	public ModelAndView connexion(@ModelAttribute("utilisateur") Utilisateur utilisateur,
			@ModelAttribute("remember") String remember, ModelMap model, HttpSession session) {

		logger.info("connexion");
		if (!utilisateurRepository.findById(utilisateur.getEmail()).isPresent()) {
			logger.info("Utilisateur inexistant !");
			String message = "Utilisateur inexistant !";
			model.addAttribute("message", message);
			return new ModelAndView("connexion", model);
		}
		Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurByEmail(utilisateur.getEmail());
		Utilisateur utilisateurAConnecter = optUtilisateur.get();

		logger.info("utilisateur mot de passe BDD : " + utilisateurAConnecter.getMotDePasse()
				+ ", utilisateur mot de passe entré : " + utilisateur.getMotDePasse());

		if (!utilisateurAConnecter.getMotDePasse().equals(utilisateur.getMotDePasse())) {
			String message = "Mot de passe incorrect !";
			logger.info(message);
			model.addAttribute("message", message);
			return new ModelAndView("connexion", model);
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

		logger.info("utilisateur connecté : " + utilisateurAConnecter.toString());
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/deconnexion")
	public ModelAndView deconnexion(ModelMap model, HttpSession session) {
		logger.info("deconnexion");
		session.invalidate();
		String message = "Vous êtes déconnecté";
		model.addAttribute("message", message);
		return new ModelAndView("acceuil", model);
	}
	
	@GetMapping("/connexions")
	public ModelAndView listConnexions(ModelMap model, HttpSession session) {

		logger.info("Liste des connexions");
		if (session.getAttribute("email") == null) {
			logger.info("nonConnecte");
			return new ModelAndView("nonConnecte");
		}
		Optional<Utilisateur> optUtilisateur = utilisateurService
				.getUtilisateurByEmail(session.getAttribute("email").toString());
		Utilisateur utilisateur = optUtilisateur.get();
		model.addAttribute("utilisateur", utilisateur);
		Iterable<Utilisateur> connexions = utilisateur.getConnexions();

		model.addAttribute("connexions", connexions);
		model.addAttribute("emailContact", "");
		return new ModelAndView("connexions", model);
	}
	
	@PostMapping("/connexions")
	@Transactional
	public ModelAndView ajouterConnexion(@ModelAttribute("utilisateur") Utilisateur utilisateur,
			@ModelAttribute("emailContact") String emailContact, ModelMap model, HttpSession session) {
		
		logger.info("Ajout de connexion : " + emailContact);
		if (session.getAttribute("email") == null) {
			logger.info("nonConnecte");
			return new ModelAndView("nonConnecte");
		}
		
		Optional<Utilisateur> optUtilisateur = utilisateurService
				.getUtilisateurByEmail(session.getAttribute("email").toString());
		utilisateur = optUtilisateur.get();
		List<Utilisateur> connexions = utilisateur.getConnexions();
		
		if (!utilisateurRepository.findById(emailContact).isPresent()) {
			String message = "Contact Inexistant !";
			model.addAttribute("message", message);
			model.addAttribute("connexions", connexions);
			return new ModelAndView("connexions", model);
		}
		
		optUtilisateur = utilisateurService
				.getUtilisateurByEmail(emailContact);
		Utilisateur utilisateurAConnecter = optUtilisateur.get();
		
		if (connexions.contains(utilisateurAConnecter)) {
			String message = "Contact déjà ajouté ! : " + emailContact;
			logger.info(message);
			model.addAttribute("message", message);
			model.addAttribute("connexions", connexions);
			return new ModelAndView("connexions", model);
		}
		
		connexions.add(utilisateurAConnecter);
		utilisateur.setConnexions(connexions);
		utilisateurRepository.save(utilisateur);
		
		String message = "Contact Ajouté : " + emailContact;
		logger.info(message);
		model.addAttribute("message", message);
		model.addAttribute("connexions", connexions);
		return new ModelAndView("connexions", model);
	}
	

}
