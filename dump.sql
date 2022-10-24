CREATE DATABASE  IF NOT EXISTS `paymybuddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `paymybuddy`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: test
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditbanque`
--

LOCK TABLES `creditbanque` WRITE;
/*!40000 ALTER TABLE `creditbanque` DISABLE KEYS */;
INSERT INTO `creditbanque` VALUES (1,'123456789','2022-08-11 13:47:48',100,'un@test.com'),(2,'123456789','2022-08-11 13:47:08',200,'deux@test.com'),(3,'13456','2022-09-25 01:19:54',1,'un@test.com'),(4,'13456','2022-09-26 11:06:50',100,'un@test.com');
/*!40000 ALTER TABLE `creditbanque` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debitbanque`
--

LOCK TABLES `debitbanque` WRITE;
/*!40000 ALTER TABLE `debitbanque` DISABLE KEYS */;
INSERT INTO `debitbanque` VALUES (1,'123456789','2022-08-11 13:54:40',10,'un@test.com'),(2,'222222222','2022-08-11 13:54:40',20,'deux@test.com'),(3,'PayMyBuddy','2022-09-26 02:14:52',0.5,'un@test.com'),(4,'PayMyBuddy','2022-09-26 02:20:14',0.5,'un@test.com'),(5,'PayMyBuddy','2022-09-26 02:21:36',0.5,'un@test.com'),(6,'PayMyBuddy','2022-09-26 02:29:26',5,'un@test.com'),(7,'PayMyBuddy','2022-09-26 02:34:09',0.75,'un@test.com'),(8,'PayMyBuddy','2022-09-26 11:07:32',0.5,'un@test.com'),(9,'PayMyBuddy','2022-09-26 11:27:38',0.5,'un@test.com'),(10,'PayMyBuddy','2022-09-27 14:23:41',0.5,'un@test.com'),(11,'PayMyBuddy','2022-09-27 14:24:21',0.05,'un@test.com'),(12,'PayMyBuddy','2022-10-03 11:22:07',0.5,'un@test.com');
/*!40000 ALTER TABLE `debitbanque` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_utilisateurcontact` FOREIGN KEY (`utilisateurcontact`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2022-08-11 13:58:38','1€ de un vers deux',1,'un@test.com','dex@test.com'),(2,'2022-08-11 13:59:03','2€ de un vers deux',2,'deux@test.com','un@test.com'),(3,'2022-08-11 13:59:26','1€ de un vers deux',1,'deux@test.com','un@test.com'),(4,'2022-08-11 13:59:35','2€ de un vers deux',2,'deux@test.com','un@test.com'),(5,'2022-08-12 13:59:35','3€ de un vers deux',3,'deux@test.com','un@test.com'),(6,'2022-08-13 13:59:35','4€ de un vers deux',4,'deux@test.com','un@test.com'),(7,'2022-08-14 13:59:35','5€ de un vers deux',5,'deux@test.com','un@test.com'),(8,'2022-08-11 13:58:38','1€ de un vers deux',1,'deux@test.com','un@test.com'),(9,'2022-08-11 13:59:03','2€ de un vers deux',2,'deux@test.com','un@test.com'),(10,'2022-08-11 13:59:26','1€ de un vers deux',1,'deux@test.com','un@test.com'),(11,'2022-08-11 13:59:35','2€ de un vers deux',2,'deux@test.com','un@test.com'),(12,'2022-08-12 13:59:35','3€ de un vers deux',3,'deux@test.com','un@test.com'),(13,'2022-08-13 13:59:35','4€ de un vers deux',4,'deux@test.com','un@test.com'),(14,'2022-08-14 13:59:35','5€ de un vers deux',5,'deux@test.com','un@test.com'),(15,'2022-08-11 13:58:38','1€ de un vers deux',1,'deux@test.com','un@test.com'),(16,'2022-08-11 13:59:03','2€ de un vers deux',2,'deux@test.com','un@test.com'),(17,'2022-08-11 13:59:26','1€ de un vers deux',1,'deux@test.com','un@test.com'),(18,'2022-08-11 13:59:35','2€ de un vers deux',2,'deux@test.com','un@test.com'),(19,'2022-08-12 13:59:35','3€ de un vers deux',3,'deux@test.com','un@test.com'),(20,'2022-08-13 13:59:35','4€ de un vers deux',4,'deux@test.com','un@test.com'),(21,'2022-08-14 13:59:35','5€ de un vers deux',5,'deux@test.com','un@test.com'),(22,'2022-08-11 13:58:38','1€ de un vers deux',1,'deux@test.com','un@test.com'),(23,'2022-08-11 13:59:03','2€ de un vers deux',2,'deux@test.com','un@test.com'),(24,'2022-08-11 13:59:26','1€ de un vers deux',1,'deux@test.com','un@test.com'),(25,'2022-08-11 13:59:35','2€ de un vers deux',2,'deux@test.com','un@test.com'),(26,'2022-08-12 13:59:35','3€ de un vers deux',3,'deux@test.com','un@test.com'),(27,'2022-08-13 13:59:35','4€ de un vers deux',4,'deux@test.com','un@test.com'),(28,'2022-08-14 13:59:35','5€ de un vers deux',5,'deux@test.com','un@test.com'),(29,'2022-08-11 13:58:38','1€ de un vers deux',1,'deux@test.com','un@test.com'),(30,'2022-08-11 13:59:03','2€ de un vers deux',2,'deux@test.com','un@test.com'),(31,'2022-08-11 13:59:26','1€ de un vers deux',1,'deux@test.com','un@test.com'),(32,'2022-08-11 13:59:35','2€ de un vers deux',2,'deux@test.com','un@test.com'),(33,'2022-08-12 13:59:35','3€ de un vers deux',3,'deux@test.com','un@test.com'),(34,'2022-08-13 13:59:35','4€ de un vers deux',4,'deux@test.com','un@test.com'),(35,'2022-08-14 13:59:35','5€ de un vers deux',5,'deux@test.com','un@test.com'),(36,'2022-08-11 13:58:38','1€ de un vers deux',1,'deux@test.com','un@test.com'),(37,'2022-08-11 13:59:03','2€ de un vers deux',2,'deux@test.com','un@test.com'),(38,'2022-08-11 13:59:26','1€ de un vers deux',1,'deux@test.com','un@test.com'),(39,'2022-08-11 13:59:35','2€ de un vers deux',2,'deux@test.com','un@test.com'),(40,'2022-08-12 13:59:35','3€ de un vers deux',3,'deux@test.com','un@test.com'),(41,'2022-08-13 13:59:35','4€ de un vers deux',4,'deux@test.com','un@test.com'),(42,'2022-08-14 13:59:35','5€ de un vers deux',5,'deux@test.com','un@test.com'),(43,'2022-09-26 02:29:26','',100,'movom@yahoo.fr','un@test.com'),(44,'2022-09-26 02:34:09','',15,'deux@test.com','un@test.com'),(45,'2022-09-26 11:07:32','',100,'quatre@test.com','un@test.com'),(46,'2022-09-26 11:27:38','',100,'deux@test.com','un@test.com'),(47,'2022-09-27 14:23:41','test envoi avec une desription qui tue sa race',100,'movom@yahoo.fr','un@test.com'),(48,'2022-09-27 14:24:21','test envoi avec une description qui tue sa race ... et puuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuus',10,'quatre@test.com','un@test.com'),(49,'2022-10-03 11:22:07','test du matin',100,'quatre@test.com','un@test.com');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES ('456n@yahoo.fr','123','dsfq',0),('cinq@test.com','5','cinq',5000),('deux@test.com','2','deux',1100),('dfhghjndn@yahoo.fr','1','test4564',0),('dfhndn@yahoo.fr','dghdhnn','test',0),('dfhndn@yahoo.frkl','1','hjkjh',0),('dfhndn@yahoo.frzert','1','testazerezt',0),('dfhndnry@yahoo.fr','1','testaze',0),('dfhndnsdfg@yahoo.fr','1','dfshj',0),('ey@ghd.test','123','ghej',0),('movom@yahoo.fr','123','test',999.5),('quatre@test.com','4','quatre',888.95),('sdfgsg@yahoo.fr','sdfs','test',0),('sdgsd@yahoo.fr','dfgdg','test',0),('six@test.com','azer','test',0),('trois@test.com','3','trois',920.5),('un@test.com','1','un',688.45),('un@test.comey','1','testaze12',0),('un@test.comghjk','1','ghjkk',0),('un@test.comp','1','o$o$',0),('vcbcv@yahoo.fr','cvbb','test',0);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

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
  KEY `FK8p5n65bqtiuai2bxa2hk57b3v` (`contact_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur_utilisateur`
--

LOCK TABLES `utilisateur_utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur_utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur_utilisateur` VALUES ('un@test.com','cinq@test.com'),('un@test.com','deux@test.com'),('un@test.com','movom@yahoo.fr'),('un@test.com','quatre@test.com'),('un@test.com','trois@test.com');
/*!40000 ALTER TABLE `utilisateur_utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-18 14:27:18
