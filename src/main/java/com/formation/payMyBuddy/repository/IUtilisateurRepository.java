package com.formation.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.Utilisateur;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur,String>{

}
