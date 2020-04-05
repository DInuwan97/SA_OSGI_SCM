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
-- Table structure for table `manufacturedetails`
--

DROP TABLE IF EXISTS `manufacturedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manufacturedetails` (
  `manufactureId` int(11) NOT NULL AUTO_INCREMENT,
  `manufactureDate` varchar(50) NOT NULL,
  `expireDate` varchar(50) NOT NULL,
  `biscutName` varchar(100) NOT NULL,
  `materials` text NOT NULL,
  `noOfMachines` int(11) NOT NULL,
  `noOfEmployees` int(11) NOT NULL,
  `manaufactAmount` int(11) NOT NULL,
  `demandReqId` int(11) NOT NULL,
  PRIMARY KEY (`manufactureId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturedetails`
--

LOCK TABLES `manufacturedetails` WRITE;
/*!40000 ALTER TABLE `manufacturedetails` DISABLE KEYS */;
INSERT INTO `manufacturedetails` VALUES (30,'2020-03-13','2020-03-19','ChocoCream','Water,Milk,Cocoa',20,12,200,10),(31,'2020-03-13','2020-03-20','LemmonPuff','Lemmon,Water,Sugar',12,10,250,15),(32,'2020-03-15','2020-03-18','LemmonPuff','Water',10,12,200,17),(33,'2020-03-19','2020-03-19','ChocoCream','Milk,Cocoa',12,10,120,16),(34,'2020-03-31','2020-06-30','ChocoCream','Water,Milk',129,122,120,19),(35,'2020-04-04','2020-04-11','LemmonPuff','Water,Milk',120,12,1200,15);
/*!40000 ALTER TABLE `manufacturedetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-05 18:42:30
