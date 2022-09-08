package com.formation.payMyBuddy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.formation.payMyBuddy.model.Transaction;
import com.formation.payMyBuddy.service.ITransactionService;

@Controller
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;

	@GetMapping("/transactions")
	public String listTransactions(Model model, HttpSession session) {

		Page<Transaction> transactions = transactionService.getPagedTransactionsByUtilisateur("un@test.com", 1);
		
		model.addAttribute("transactions", transactions.getContent());
		model.addAttribute("pagenumber", transactions.getNumber());
		model.addAttribute("totalpages", transactions.getTotalPages());
		return "transactions";

	}

}
