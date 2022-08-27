package com.formation.payMyBuddy.service;

import java.util.List;

import com.formation.payMyBuddy.model.TransactionFormattee;

public interface ITransactionService {
	
	public List<TransactionFormattee> getTransactionsByUtilisateur(String email);

}
