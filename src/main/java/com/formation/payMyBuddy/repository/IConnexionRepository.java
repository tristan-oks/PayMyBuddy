package com.formation.payMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.payMyBuddy.model.ConnexionId;
import com.formation.payMyBuddy.model.Connexion;

@Repository
public interface IConnexionRepository extends CrudRepository<Connexion,ConnexionId>{
	}
