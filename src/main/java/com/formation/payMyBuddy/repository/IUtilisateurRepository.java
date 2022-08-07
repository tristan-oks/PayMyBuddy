package com.formation.payMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.Utilisateur;

@Repository
public interface IUtilisateurRepository extends CrudRepository<Utilisateur,String>{

}
