package com.formation.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction,Integer>{

}
