package com.formation.payMyBuddy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.formation.payMyBuddy.model.TransactionFormattee;
import com.formation.payMyBuddy.service.ITransactionService;

@Controller
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;

	@GetMapping("/transactions")
	public String listTransactions(Model model, HttpSession session) {

		List<TransactionFormattee> transactions = transactionService.getTransactionsByUtilisateur("un@test.com");
		model.addAttribute("transactions", transactions);
		return "transactions";

	}

}
