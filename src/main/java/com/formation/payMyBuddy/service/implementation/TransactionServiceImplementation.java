package com.formation.payMyBuddy.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.payMyBuddy.model.Transaction;
import com.formation.payMyBuddy.model.TransactionFormattee;
import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.service.ITransactionService;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Service
public class TransactionServiceImplementation implements ITransactionService{
	
	@Autowired
	private IUtilisateurService utilisateurService;

	
	public List<TransactionFormattee> getTransactionsByUtilisateur(String email) {
	System.out.println("test html transactions started");
	Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurByEmail("un@test.com");
	Utilisateur utilisateur = optUtilisateur.get();
	System.out.println(utilisateur.getEmail());
	System.out.println(utilisateur);
	List<TransactionFormattee> transactions = new ArrayList<>();
	for (Transaction transactionBrute : utilisateur.getTransactions()) {
		TransactionFormattee transaction = new TransactionFormattee();
		transaction.setDate(transactionBrute.getDate());
		transaction.setDescription(transactionBrute.getDescription());
		transaction.setIdTransaction(transactionBrute.getIdtransaction());
		transaction.setMontant(transactionBrute.getMontant());
		transaction.setUtilisateurContact(transactionBrute.getUtilisateurContact().getEmail());
		transaction.setUtilisateurTransaction(transactionBrute.getUtilisateurTransaction().getEmail());
		transactions.add(transaction);
	}
	return transactions;
	}
}
