INSERT INTO `utilisateur` (`email`,`motdepasse`,`nom`,`solde`) VALUES ('un@test.com','1','un',1000);
INSERT INTO `utilisateur` (`email`,`motdepasse`,`nom`,`solde`) VALUES ('deux@test.com','2','deux',2000);
INSERT INTO `utilisateur` (`email`,`motdepasse`,`nom`,`solde`) VALUES ('trois@test.com','3','trois',3000);
INSERT INTO `utilisateur` (`email`,`motdepasse`,`nom`,`solde`) VALUES ('quatre@test.com','4','quatre',4000);
INSERT INTO `utilisateur` (`email`,`motdepasse`,`nom`,`solde`) VALUES ('cinq@test.com','5','cinq',5000);
INSERT INTO `creditbanque` (`idcredit`,`utilisateurcredit`,`comptebancaire`,`montant`,`date`) VALUES (1,'un@test.com','123456789',100,'2022-08-11 13:47:48');
INSERT INTO `creditbanque` (`idcredit`,`utilisateurcredit`,`comptebancaire`,`montant`,`date`) VALUES (2,'deux@test.com','123456789',200,'2022-08-11 13:47:08');
INSERT INTO `debitbanque` (`iddebit`,`utilisateurdebit`,`comptebancaire`,`montant`,`date`) VALUES (1,'un@test.com','123456789',10,'2022-08-11 13:54:40');
INSERT INTO `debitbanque` (`iddebit`,`utilisateurdebit`,`comptebancaire`,`montant`,`date`) VALUES (2,'deux@test.com','222222222',20,'2022-08-11 13:54:40');
INSERT INTO `transaction` (`idtransaction`,`utilisateurtransaction`,`utilisateurcontact`,`montant`,`date`,`description`) VALUES (1,'un@test.com','deux@test.com',1,'2022-08-11 13:58:38','1€ de un vers deux');
INSERT INTO `transaction` (`idtransaction`,`utilisateurtransaction`,`utilisateurcontact`,`montant`,`date`,`description`) VALUES (2,'un@test.com','deux@test.com',2,'2022-08-11 13:59:03','2€ de un vers deux');
INSERT INTO `transaction` (`idtransaction`,`utilisateurtransaction`,`utilisateurcontact`,`montant`,`date`,`description`) VALUES (3,'un@test.com','deux@test.com',1,'2022-08-11 13:59:26','1€ de un vers deux');
INSERT INTO `transaction` (`idtransaction`,`utilisateurtransaction`,`utilisateurcontact`,`montant`,`date`,`description`) VALUES (4,'un@test.com','deux@test.com',2,'2022-08-11 13:59:35','2€ de un vers deux');
INSERT INTO `utilisateur_utilisateur` (`utilisateur_email`,`contact_email`) VALUES ('un@test.com','deux@test.com');
INSERT INTO `utilisateur_utilisateur` (`utilisateur_email`,`contact_email`) VALUES ('un@test.com','trois@test.com');
INSERT INTO `utilisateur_utilisateur` (`utilisateur_email`,`contact_email`) VALUES ('un@test.com','cinq@test.com');