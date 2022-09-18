package com.formation.payMyBuddy.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formation.payMyBuddy.model.Transaction;
import com.formation.payMyBuddy.service.ITransactionService;

@Controller
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;
	
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
			logger.info("nonConnecte");
			return "nonConnecte";
		}
		logger.info("Connecte sous : " + session.getAttribute("email"));
		
		if (currentPage < 1) {
			logger.info("la page de transactions page n° " + currentPage + "n'existe pas");
			return "error";
		}
		Page<Transaction> transactions = transactionService.getPagedTransactionsByUtilisateur(session.getAttribute("email").toString(),
				currentPage - 1);

		model.addAttribute("transactions", transactions.getContent());
		model.addAttribute("pagenumber", transactions.getNumber() + 1);
		model.addAttribute("totalpages", transactions.getTotalPages());
		return "transactions";

	}

}
