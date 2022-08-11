package com.formation.payMyBuddy.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtransaction")
	private int idTransaction;
	
	@Column(name = "emailtransaction", length=100)
	private String emailTransaction;
	
	@Column(name = "emailcontact")
	private String emailContact;
	
	@Column(name = "montant")
	private float montant;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	private Timestamp date;

		

	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getEmailTransaction() {
		return emailTransaction;
	}

	public void setEmailTransaction(String emailTransaction) {
		this.emailTransaction = emailTransaction;
	}

	public String getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
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
	
	
	
	
	
	

}
