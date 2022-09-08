package com.formation.payMyBuddy.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.formation.payMyBuddy.model.Transaction;
import com.formation.payMyBuddy.repository.ITransactionRepository;
import com.formation.payMyBuddy.service.ITransactionService;

@Service
public class TransactionServiceImplementation implements ITransactionService {

	@Autowired
	private ITransactionRepository transactionRepository;

	public Page<Transaction> getPagedTransactionsByUtilisateur(String email, int pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber - 1, 3);
		Page<Transaction> page = transactionRepository.findByUtilisateurTransactionEmail(email, pageable);
		return page;

	}
}
