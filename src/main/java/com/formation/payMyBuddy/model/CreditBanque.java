package com.formation.payMyBuddy.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "creditbanque")
public class CreditBanque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcredit")
	private int idCredit;
	
	@Column(name = "emailcredit", length=100)
	private String emailCredit;
	
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

	public String getEmailCredit() {
		return emailCredit;
	}

	public void setEmailCredit(String emailCredit) {
		this.emailCredit = emailCredit;
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
