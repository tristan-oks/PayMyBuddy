CREATE DATABASE  IF NOT EXISTS `paymybuddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `paymybuddy`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: paymybuddy
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `creditbanque`
--

DROP TABLE IF EXISTS `creditbanque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditbanque` (
  `idcredit` int NOT NULL AUTO_INCREMENT,
  `comptebancaire` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `montant` float DEFAULT NULL,
  `utilisateurcredit` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idcredit`),
  KEY `FK_utilisateurcredit` (`utilisateurcredit`),
  CONSTRAINT `FK_utilisateurcredit` FOREIGN KEY (`utilisateurcredit`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `debitbanque`
--

DROP TABLE IF EXISTS `debitbanque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debitbanque` (
  `iddebit` int NOT NULL AUTO_INCREMENT,
  `comptebancaire` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `montant` float DEFAULT NULL,
  `utilisateurdebit` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`iddebit`),
  KEY `FK_utilisateurdebit` (`utilisateurdebit`),
  CONSTRAINT `FK_utilisateurdebit` FOREIGN KEY (`utilisateurdebit`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `idtransaction` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `montant` float DEFAULT NULL,
  `utilisateurcontact` varchar(100) DEFAULT NULL,
  `utilisateurtransaction` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idtransaction`),
  KEY `FK5u5i8xqs0knd2o1qh7mix78ts` (`utilisateurtransaction`),
  KEY `FK_utilisateurcontact` (`utilisateurcontact`),
  CONSTRAINT `FK_utilisateurcontact` FOREIGN KEY (`utilisateurcontact`) REFERENCES `utilisateur` (`email`),
  CONSTRAINT `FK_utilisateurtransaction` FOREIGN KEY (`utilisateurtransaction`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `email` varchar(100) NOT NULL,
  `motdepasse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `solde` float DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utilisateur_utilisateur`
--

DROP TABLE IF EXISTS `utilisateur_utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur_utilisateur` (
  `utilisateur_email` varchar(100) NOT NULL,
  `contact_email` varchar(100) NOT NULL,
  PRIMARY KEY (`utilisateur_email`,`contact_email`),
  KEY `FK8p5n65bqtiuai2bxa2hk57b3v` (`contact_email`),
  CONSTRAINT `FK_contactemail` FOREIGN KEY (`contact_email`) REFERENCES `utilisateur` (`email`),
  CONSTRAINT `FK_utilisateuremail` FOREIGN KEY (`utilisateur_email`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-31 23:47:53
