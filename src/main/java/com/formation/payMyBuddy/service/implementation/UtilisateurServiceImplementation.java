package com.formation.payMyBuddy.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.repository.IUtilisateurRepository;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Service
public class UtilisateurServiceImplementation implements IUtilisateurService{

	@Autowired
	private IUtilisateurRepository utilisateurRepo;
	
		public Iterable<Utilisateur> getUtilisateurs() {
		return utilisateurRepo.findAll();
	}
}
