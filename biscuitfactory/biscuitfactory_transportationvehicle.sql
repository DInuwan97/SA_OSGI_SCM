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
-- Table structure for table `transportationvehicle`
--

DROP TABLE IF EXISTS `transportationvehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transportationvehicle` (
  `idtransportationVehicle` int(11) NOT NULL AUTO_INCREMENT,
  `plateNumber` varchar(45) DEFAULT NULL,
  `driver` varchar(45) DEFAULT NULL,
  `availability` varchar(45) DEFAULT 'Available',
  PRIMARY KEY (`idtransportationVehicle`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportationvehicle`
--

LOCK TABLES `transportationvehicle` WRITE;
/*!40000 ALTER TABLE `transportationvehicle` DISABLE KEYS */;
INSERT INTO `transportationvehicle` VALUES (21,'FR5436','Upul','Available'),(22,'GH6793','Kamal','Available'),(23,'TH5678','Farheen','Unavailable'),(24,'ffaafa','feafe','Available'),(25,'sdsa','wadw','Available'),(26,'fafda','dada','Available'),(27,'AD1234','Kamal Perera','Available'),(28,'faeafd','sasd','Available'),(29,'cdccxzc','dasd','Available'),(30,'ASDF','sgsdfsdf','Available');
/*!40000 ALTER TABLE `transportationvehicle` ENABLE KEYS */;
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
