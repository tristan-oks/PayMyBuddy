package com.formation.payMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.DebitBanque;

@Repository
public interface IDebitBanqueRepository extends CrudRepository<DebitBanque,Integer>{

}
