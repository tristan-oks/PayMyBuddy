package com.formation.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.DebitBanque;

@Repository
public interface IDebitBanqueRepository extends JpaRepository<DebitBanque,Integer>{

}
