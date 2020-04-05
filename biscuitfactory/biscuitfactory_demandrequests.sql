-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: biscuitfactory
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `demandrequests`
--

DROP TABLE IF EXISTS `demandrequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `demandrequests` (
  `demandReqId` int(11) NOT NULL AUTO_INCREMENT,
  `productdetails` text NOT NULL,
  `demadReason` text NOT NULL,
  `salesMsgId` int(11) NOT NULL,
  `reqDate` date NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`demandReqId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demandrequests`
--

LOCK TABLES `demandrequests` WRITE;
/*!40000 ALTER TABLE `demandrequests` DISABLE KEYS */;
INSERT INTO `demandrequests` VALUES (15,'LemmonPuff : 100 , ChocoCream : 30 , sdsdsd : 34 , wfdfdf : 3 , LemmonPuff : 30 , LemmonPuff : 12 , ChocoCreame : 100 , LemmoPuff : 122 , LemmonPuff : 12 , CreamCracker : 200 , LemmonPuff : 100 , ButterPuff : 200 , CurryPuff : 300 , LemmonPuff : 200 , ','EFEFE',2,'2020-03-11','false'),(16,'LemmonPuff : 100 , ChocoCream : 30 , sdsdsd : 34 , wfdfdf : 3 , LemmonPuff : 30 , LemmonPuff : 12 , ChocoCreame : 100 , LemmoPuff : 122 , LemmonPuff : 12 , CreamCracker : 200 , LemmonPuff : 100 , ButterPuff : 200 , CurryPuff : 300 , LemmonPuff : 200 , LemmoPuff : 20 , ChocoCreame : 100 , ','adfwdfsd rfgwer wrg',2,'2020-03-25','false'),(17,'LemmonPuff : 100 , ChocoCream : 30 , sdsdsd : 34 , wfdfdf : 3 , LemmonPuff : 30 , LemmonPuff : 12 , ChocoCreame : 100 , LemmoPuff : 122 , LemmonPuff : 12 , CreamCracker : 200 , LemmonPuff : 100 , ButterPuff : 200 , CurryPuff : 300 , LemmonPuff : 200 , LemmoPuff : 20 , ChocoCreame : 100 , ChocoCream : 140 , ','sfsfgrg',2,'2020-03-11','false'),(18,'LemmonPuff : 100 , ChocoCream : 30 , sdsdsd : 34 , wfdfdf : 3 , LemmonPuff : 30 , LemmonPuff : 12 , ChocoCreame : 100 , LemmoPuff : 122 , LemmonPuff : 12 , CreamCracker : 200 , LemmonPuff : 100 , ButterPuff : 200 , CurryPuff : 300 , LemmonPuff : 200 , LemmoPuff : 20 , ChocoCreame : 100 , ChocoCream : 140 , efdfdd : 232323 , LemmonPuff : 150 , ChocoCreame : 230 , ','svsf sffs sgfgfdgjhdghhdg',2,'2020-03-10','false'),(19,'LemmonPuff : 100 , ChocoCream : 30 , sdsdsd : 34 , wfdfdf : 3 , LemmonPuff : 30 , LemmonPuff : 12 , ChocoCreame : 100 , LemmoPuff : 122 , LemmonPuff : 12 , CreamCracker : 200 , LemmonPuff : 100 , ButterPuff : 200 , CurryPuff : 300 , LemmonPuff : 200 , LemmoPuff : 20 , ChocoCreame : 100 , ChocoCream : 140 , efdfdd : 232323 , LemmonPuff : 150 , ChocoCreame : 230 , LemmonPuff : 120 , ','dsdsfvs',2,'2020-03-31','false'),(20,'LemmonPuff : 100 , ChocoCream : 30 , sdsdsd : 34 , wfdfdf : 3 , LemmonPuff : 30 , LemmonPuff : 12 , ChocoCreame : 100 , LemmoPuff : 122 , LemmonPuff : 12 , CreamCracker : 200 , LemmonPuff : 100 , ButterPuff : 200 , CurryPuff : 300 , LemmonPuff : 200 , LemmoPuff : 20 , ChocoCreame : 100 , ChocoCream : 140 , efdfdd : 232323 , LemmonPuff : 150 , ChocoCreame : 230 , LemmonPuff : 120 , lemmokgyu : 23 , ','uijggj gjhgjyh',3,'2020-04-14','false');
/*!40000 ALTER TABLE `demandrequests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-05 18:42:28
