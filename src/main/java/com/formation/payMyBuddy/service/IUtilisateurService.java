package com.formation.payMyBuddy.service;

import java.util.Optional;

import com.formation.payMyBuddy.model.Utilisateur;

public interface IUtilisateurService {

	public Iterable<Utilisateur> getUtilisateurs();

	public Optional<Utilisateur> getUtilisateurByEmail(String email);

	public String getNom(String email);

	public String modifierUtilisateur(String email, Utilisateur utilisateur, String compteBancaire, String creditDebit, float montant);
}
