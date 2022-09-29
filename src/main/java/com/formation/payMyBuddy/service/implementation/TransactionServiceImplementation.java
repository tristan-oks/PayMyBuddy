package com.formation.payMyBuddy.service.implementation;

import java.sql.Timestamp;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.formation.payMyBuddy.model.DebitBanque;
import com.formation.payMyBuddy.model.Transaction;
import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.repository.IDebitBanqueRepository;
import com.formation.payMyBuddy.repository.ITransactionRepository;
import com.formation.payMyBuddy.service.ITransactionService;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Service
public class TransactionServiceImplementation implements ITransactionService {

	@Autowired
	private ITransactionRepository transactionRepository;
	@Autowired
	private IUtilisateurService utilisateurService;
	@Autowired
	private IDebitBanqueRepository debitBanqueRepo;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Page<Transaction> getPagedTransactionsByUtilisateur(String email, int pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber, 3);
		Page<Transaction> page = transactionRepository.findByUtilisateurTransactionEmailOrderByDateDesc(email, pageable);
		return page;

	}

	public String transaction(String email, Utilisateur connexion, float montant, String description) {
		String message = "";
		Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateurByEmail(email);
		Utilisateur utilisateur = optUtilisateur.get();
		float solde = utilisateur.getSolde();
		if (solde < montant * (1 + 0.005)) {
			message = "montant trop élevé !";
			logger.info(message);
			return message;
		}
		utilisateur.setSolde((float) (solde - montant * (1 + 0.005)));
		// utilisateurRepo.save(utilisateur);
		connexion.setSolde((float) (solde + montant));
		// utilisateurRepo.save(connexion);

		DebitBanque debit = new DebitBanque();
		debit.setCompteBancaire("PayMyBuddy");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		debit.setDate(timestamp);
		debit.setMontant((float) (montant * 0.005));
		debit.setUtilisateurDebit(utilisateur);
		debitBanqueRepo.save(debit);

		Transaction transaction = new Transaction();
		transaction.setMontant(montant);
		transaction.setDate(timestamp);
		transaction.setUtilisateurContact(connexion);
		transaction.setUtilisateurTransaction(utilisateur);
		transaction.setDescription(description);
		transactionRepository.save(transaction);

		message = "Transaction effectuée";
		logger.info(message);
		return message;
	}
}
