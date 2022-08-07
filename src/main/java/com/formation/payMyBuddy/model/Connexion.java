package com.formation.payMyBuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "connexion")
@IdClass(ConnexionId.class)
public class Connexion {
	
	@Id
	@Column(name = "emailutilisateur")
	private String emailUtilisateur;
	
	@Id
	@Column(name = "emailcontact")
	private String emailContact;

	public String getEmailutilisateur() {
		return emailUtilisateur;
	}

	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}

	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}

	public String getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
	}

	
}
