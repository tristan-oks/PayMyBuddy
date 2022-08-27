package com.formation.payMyBuddy.model;

import java.sql.Timestamp;

public class TransactionFormattee {

	private int idTransaction;

	private float montant;

	private String description;

	private Timestamp date;

	private String utilisateurTransaction;

	private String utilisateurContact;

	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getUtilisateurTransaction() {
		return utilisateurTransaction;
	}

	public void setUtilisateurTransaction(String utilisateurTransaction) {
		this.utilisateurTransaction = utilisateurTransaction;
	}

	public String getUtilisateurContact() {
		return utilisateurContact;
	}

	public void setUtilisateurContact(String utilisateurContact) {
		this.utilisateurContact = utilisateurContact;
	}
	
}
