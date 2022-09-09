package com.formation.payMyBuddy.controller;

import javax.servlet.http.HttpSession;

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

	@GetMapping("/transactions")
	public String getTransactionsFirstPage(Model model, HttpSession session) {
		return listTransactions(model, 1, session);
	}

	@GetMapping("/transactions/{pageNumber}")
	public String listTransactions(Model model, @PathVariable("pageNumber") int currentPage, HttpSession session) {

		if (currentPage < 1) {
			return "error";
		}
		Page<Transaction> transactions = transactionService.getPagedTransactionsByUtilisateur("un@test.com",
				currentPage - 1);

		model.addAttribute("transactions", transactions.getContent());
		model.addAttribute("pagenumber", transactions.getNumber() + 1);
		model.addAttribute("totalpages", transactions.getTotalPages());
		return "transactions";

	}

}
