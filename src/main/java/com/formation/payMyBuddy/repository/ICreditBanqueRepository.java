package com.formation.payMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.CreditBanque;

@Repository
public interface ICreditBanqueRepository extends CrudRepository<CreditBanque,Integer>{

}

