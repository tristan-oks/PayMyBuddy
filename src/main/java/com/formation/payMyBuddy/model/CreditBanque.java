package com.formation.payMyBuddy.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "creditbanque")
public class CreditBanque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcredit")
	private int idCredit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utilisateurcredit")
	private Utilisateur utilisateurCredit;

	@Column(name = "comptebancaire")
	private String compteBancaire;
	
	@Column(name = "montant")
	private float montant;
	
	@Column(name = "date")
	private Timestamp date;

	public int getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(int idCredit) {
		this.idCredit = idCredit;
	}

	public Utilisateur getUtilisateurCredit() {
		return utilisateurCredit;
	}

	public void setUtilisateurCredit(Utilisateur utilisateurCredit) {
		this.utilisateurCredit = utilisateurCredit;
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
