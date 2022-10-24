package com.formation.payMyBuddy.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.payMyBuddy.model.Transaction;
import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.service.ITransactionService;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Controller
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private IUtilisateurService utilisateurService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/transactions")
	public String getTransactionsFirstPage(Model model, HttpSession session) {
		logger.info("get first transactions page");
		return listTransactions(model, 1, session);
	}

	@GetMapping("/transactions/{pageNumber}")
	public String listTransactions(Model model, @PathVariable("pageNumber") int currentPage, HttpSession session) {
		logger.info("get transactions page n° " + currentPage);
		if (session.getAttribute("email") == null) {
			return nonConnecte();
		}
		logger.info("Connecte sous : " + session.getAttribute("email"));

		if (currentPage < 1) {
			logger.info("la page de transactions page n° " + currentPage + "n'existe pas");
			return "error";
		}
		Page<Transaction> transactions = transactionService
				.getPagedTransactionsByUtilisateur(session.getAttribute("email").toString(), currentPage - 1);

		model.addAttribute("transactions", transactions.getContent());
		model.addAttribute("pagenumber", transactions.getNumber() + 1);
		model.addAttribute("totalpages", transactions.getTotalPages());

		Optional<Utilisateur> optUtilisateur = utilisateurService
				.getUtilisateurByEmail(session.getAttribute("email").toString());
		Utilisateur utilisateur = optUtilisateur.get();
		model.addAttribute("listConnexions", utilisateur.getConnexions());
		return "transactions";
	}

	@PostMapping("/transactions")
	public String postTransaction(Model model, @ModelAttribute("connexion") Utilisateur connexion,
			@ModelAttribute("montant") float montant, @ModelAttribute("description") String description,
			HttpSession session) {

		logger.info("connexion : " + connexion.toString());
		logger.info("description : " + description);
		logger.info("montant : " + montant);

		if (session.getAttribute("email") == null) {
			return nonConnecte();
		}

		if (montant != 0) {
			String email = session.getAttribute("email").toString();
			String message = transactionService.transaction(email, connexion, montant, description);
			model.addAttribute("message", message);
		}
		return listTransactions(model, 1, session);
	}

	private String nonConnecte() {
		logger.info("nonConnecte");
		return "nonConnecte";
	}
}
