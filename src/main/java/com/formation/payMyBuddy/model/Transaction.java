package com.formation.payMyBuddy.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
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
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtransaction")
	private int idTransaction;
	
	@Column(name = "montant")
	private float montant;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	private Timestamp date;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "utilisateurtransaction")
	private Utilisateur utilisateurTransaction;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "utilisateurcontact")
	private Utilisateur utilisateurContact;

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

	public Utilisateur getUtilisateurTransaction() {
		return utilisateurTransaction;
	}

	public void setUtilisateurTransaction(Utilisateur utilisateurTransaction) {
		this.utilisateurTransaction = utilisateurTransaction;
	}

	public Utilisateur getUtilisateurContact() {
		return utilisateurContact;
	}

	public void setUtilisateurContact(Utilisateur utilisateurContact) {
		this.utilisateurContact = utilisateurContact;
	}

	
}
