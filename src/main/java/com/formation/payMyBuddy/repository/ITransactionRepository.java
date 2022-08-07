package com.formation.payMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.Transaction;

@Repository
public interface ITransactionRepository extends CrudRepository<Transaction,Integer>{

}
