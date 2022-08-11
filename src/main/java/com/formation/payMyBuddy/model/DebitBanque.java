package com.formation.payMyBuddy.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "debitbanque")
public class DebitBanque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddebit")
	private int idDebit;
	
	@Column(name = "emaildebit", length=100)
	private String emailDebit;
	
	@Column(name = "comptebancaire")
	private String compteBancaire;
	
	@Column(name = "montant")
	private float montant;
	
	@Column(name = "date")
	private Timestamp date;

		
	public int getIdDebit() {
		return idDebit;
	}

	public void setIdDebit(int idDebit) {
		this.idDebit = idDebit;
	}

	public String getEmailDebit() {
		return emailDebit;
	}

	public void setEmailDebit(String emailDebit) {
		this.emailDebit = emailDebit;
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
