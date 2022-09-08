package com.formation.payMyBuddy.service;

import org.springframework.data.domain.Page;

import com.formation.payMyBuddy.model.Transaction;

public interface ITransactionService {

	public Page<Transaction> getPagedTransactionsByUtilisateur(String email, int pageNumber);

}
