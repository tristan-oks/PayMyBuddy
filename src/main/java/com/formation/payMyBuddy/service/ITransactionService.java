package com.formation.payMyBuddy.service;

import org.springframework.data.domain.Page;

import com.formation.payMyBuddy.model.Transaction;
import com.formation.payMyBuddy.model.Utilisateur;

public interface ITransactionService {

	public Page<Transaction> getPagedTransactionsByUtilisateur(String email, int pageNumber);

	public String transaction(String email, Utilisateur connexion, float montant, String description);

}
