package com.formation.payMyBuddy.service.implementation;

import java.security.Key;
import java.sql.Timestamp;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.payMyBuddy.model.CreditBanque;
import com.formation.payMyBuddy.model.DebitBanque;
import com.formation.payMyBuddy.model.Utilisateur;
import com.formation.payMyBuddy.repository.ICreditBanqueRepository;
import com.formation.payMyBuddy.repository.IDebitBanqueRepository;
import com.formation.payMyBuddy.repository.IUtilisateurRepository;
import com.formation.payMyBuddy.service.IUtilisateurService;

@Service
public class UtilisateurServiceImplementation implements IUtilisateurService {

	@Autowired
	private IUtilisateurRepository utilisateurRepo;
	@Autowired
	private ICreditBanqueRepository creditBanqueRepo;
	@Autowired
	private IDebitBanqueRepository debitBanqueRepo;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Optional<Utilisateur> getUtilisateurByEmail(String email) {
		return utilisateurRepo.findById(email);
	}

	public String getNom(String email) {
		Optional<Utilisateur> optUtilisateur = getUtilisateurByEmail(email);
		String nom = optUtilisateur.get().getNom();
		return nom;
	}

	@Transactional
	public String modifierUtilisateur(String email, Utilisateur utilisateur, String compteBancaire, String creditDebit,
			float montant) {
		String message = "";
		Optional<Utilisateur> optUtilisateur = getUtilisateurByEmail(email);
		Utilisateur utilisateurAModifier = optUtilisateur.get();
		if (montant != 0) { // operation bancaire
			message = operationBancaire(compteBancaire, creditDebit, montant, utilisateurAModifier);
		}
		try {
			utilisateur.setMotDePasse(encrypte(utilisateur.getMotDePasse()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!utilisateur.getMotDePasse().equals(utilisateurAModifier.getMotDePasse())) {
			utilisateurAModifier.setMotDePasse(utilisateur.getMotDePasse());
			utilisateurRepo.save(utilisateurAModifier);
			message = message + "Mot de passe changé, ";
			logger.info(message);
		}
		if (!utilisateur.getNom().equals(utilisateurAModifier.getNom())) {
			utilisateurAModifier.setNom(utilisateur.getNom());
			utilisateurRepo.save(utilisateurAModifier);
			message = message + "Nom changé";
			logger.info(message);
		}
		return message;
	}

	@Transactional
	private String operationBancaire(String compteBancaire, String creditDebit, float montant,
			Utilisateur utilisateurAModifier) {
		if (compteBancaire.equals("")) {
			String message = "Renseignez le compte bancaire !";
			logger.info(message);
			return message;
		}
		if (creditDebit.equals("credit")) {
			CreditBanque credit = new CreditBanque();
			credit.setCompteBancaire(compteBancaire);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			credit.setDate(timestamp);
			credit.setMontant(montant);
			credit.setUtilisateurCredit(utilisateurAModifier);
			creditBanqueRepo.save(credit);

			utilisateurAModifier.setSolde(utilisateurAModifier.getSolde() + montant);
			utilisateurRepo.save(utilisateurAModifier);
			String message = "Compte Bancaire " + compteBancaire + " crédité de : " + montant + ", ";
			logger.info(message);
			return message;
		} else {
			if (montant > utilisateurAModifier.getSolde()) {
				String message = "Montant du débit supérieur au solde, opération impossible !";
				logger.info(message);
				return message;
			}
			DebitBanque debit = new DebitBanque();
			debit.setCompteBancaire(compteBancaire);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			debit.setDate(timestamp);
			debit.setMontant(montant);
			debit.setUtilisateurDebit(utilisateurAModifier);
			debitBanqueRepo.save(debit);

			utilisateurAModifier.setSolde(utilisateurAModifier.getSolde() - montant);
			utilisateurRepo.save(utilisateurAModifier);
			String message = "Compte Bancaire " + compteBancaire + " débité de : " + montant + ", ";
			logger.info(message);
			return message;
		}
	}

	public String encrypte(String texteEnClair) throws Exception {
		Cipher cipher = Cipher.getInstance("RC4");
		Key cle = new SecretKeySpec("12345678".getBytes(), "RC4");
		cipher.init(Cipher.ENCRYPT_MODE, cle);
		byte[] texteCrypte = cipher.doFinal(texteEnClair.getBytes());
		return new String(texteCrypte);
	}
}
