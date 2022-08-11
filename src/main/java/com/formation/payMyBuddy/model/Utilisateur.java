package com.formation.payMyBuddy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@Column(name = "email", length=100)
	private String email;

	@Column(name = "motdepasse")
	private String motDePasse;

	@Column(name = "nom")
	private String nom;

	@Column(name = "solde")
	private float solde;

	 //lien avec la table connexion
	@ManyToMany(cascade = {CascadeType.ALL, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "utilisateur_utilisateur", 
		joinColumns = @JoinColumn(name = "utilisateur_email"), 
		inverseJoinColumns = @JoinColumn(name = "contact_email"))
	List<Utilisateur> connexions = new ArrayList<>();

	// lien avec la table creditBanque
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "emailcredit")
	//@JoinTable(name = "utilisateur_creditbanque", 
	//joinColumns = @JoinColumn(name = "emailutilisateur")) 
	//inverseJoinColumns = @JoinColumn(name = "idcredit"))
	List<CreditBanque> creditsBanque = new ArrayList<>();

	// lien avec la table debitBanque
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "emaildebit")
	//@JoinTable(name = "utilisateur_debitbanque", 
	//joinColumns = @JoinColumn(name = "email")) 
	//inverseJoinColumns = @JoinColumn(name = "iddebit"))
	List<DebitBanque> debitsBanque = new ArrayList<>();

	// lien avec la table transaction
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "emailtransaction")
	//@JoinTable(name = "utilisateur_transaction", 
	//joinColumns = @JoinColumn(name = "email")) 
	//inverseJoinColumns = @JoinColumn(name = "idtransaction"))
	List<Transaction> transactions = new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public List<Utilisateur> getConnexions() {
		return connexions;
	}

	public void setConnexions(List<Utilisateur> connexions) {
		this.connexions = connexions;
	}

	public List<CreditBanque> getCreditsBanque() {
		return creditsBanque;
	}

	public void setCreditsBanque(List<CreditBanque> creditsBanque) {
		this.creditsBanque = creditsBanque;
	}

	public List<DebitBanque> getDebitsBanque() {
		return debitsBanque;
	}

	public void setDebitsBanque(List<DebitBanque> debitsBanque) {
		this.debitsBanque = debitsBanque;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	
}
