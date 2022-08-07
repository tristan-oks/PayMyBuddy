package com.formation.payMyBuddy.model;

import java.io.Serializable;

public class ConnexionId implements Serializable {
	
	private String emailUtilisateur;
	private String emailContact;
	
	public ConnexionId (String emailutilisateur, String emailcontact) {
		this.emailUtilisateur = emailutilisateur;
		this.emailContact = emailcontact;
	}

}
