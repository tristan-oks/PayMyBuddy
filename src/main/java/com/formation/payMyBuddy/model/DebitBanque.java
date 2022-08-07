package com.formation.payMyBuddy.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "debitbanque")
public class DebitBanque {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "emailutilisateur")
	private String emailUtilisateur;
	
	@Column(name = "comptebancaire")
	private String compteBancaire;
	
	@Column(name = "montant")
	private float montant;
	
	@Column(name = "date")
	private Timestamp date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}

	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}

	public String getCompteBancaire() {
		return compteBancaire;
	}

	public void setCompteBancaire(String compteBancaire) {
		this.compteBancaire = compteBancaire;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	

}
