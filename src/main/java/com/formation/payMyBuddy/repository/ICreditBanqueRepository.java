package com.formation.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.CreditBanque;

@Repository
public interface ICreditBanqueRepository extends JpaRepository<CreditBanque,Integer>{

}

