-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: card_directory
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wizards_set`
--

DROP TABLE IF EXISTS `wizards_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wizards_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mcshort` varchar(7) COLLATE utf8_polish_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` int(11) NOT NULL,
  `minPrice` float DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12991 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cube`
--

DROP TABLE IF EXISTS `cube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cube` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card_in_set`
--

DROP TABLE IF EXISTS `card_in_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_in_set` (
  `rarity` varchar(31) COLLATE utf8_polish_ci DEFAULT NULL,
  `card_id` bigint(20) NOT NULL DEFAULT '0',
  `set_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`card_id`,`set_id`),
  KEY `FK198BCE57301A11E3` (`set_id`),
  KEY `FK198BCE577DCFB171` (`card_id`),
  CONSTRAINT `FK198BCE577DCFB171` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FK198BCE57301A11E3` FOREIGN KEY (`set_id`) REFERENCES `wizards_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card_model`
--

DROP TABLE IF EXISTS `card_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `borderType` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `foil` tinyint(1) DEFAULT NULL,
  `imageLink` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `markedWOTC` tinyint(1) DEFAULT NULL,
  `mcLink` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `printing` varchar(63) COLLATE utf8_polish_ci DEFAULT NULL,
  `quality` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `signed` tinyint(1) DEFAULT NULL,
  `cardInSet_card_id` bigint(20) NOT NULL,
  `cardInSet_set_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mcLink` (`mcLink`),
  KEY `FK3ADA4D3AA6BB3578` (`cardInSet_card_id`,`cardInSet_set_id`),
  CONSTRAINT `FK3ADA4D3AA6BB3578` FOREIGN KEY (`cardInSet_card_id`, `cardInSet_set_id`) REFERENCES `card_in_set` (`card_id`, `set_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `model_price`
--

DROP TABLE IF EXISTS `model_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `high` float DEFAULT NULL,
  `low` float DEFAULT NULL,
  `mid` float DEFAULT NULL,
  `cardModel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31674CB3E206562D` (`cardModel_id`),
  CONSTRAINT `FK31674CB3E206562D` FOREIGN KEY (`cardModel_id`) REFERENCES `card_model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cube_card_use`
--

DROP TABLE IF EXISTS `cube_card_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cube_card_use` (
  `version` int(11) NOT NULL,
  `cardUse` int(11) NOT NULL,
  `card_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`card_id`,`version`),
  KEY `FK264EC0A27DCFB171` (`card_id`),
  CONSTRAINT `FK264EC0A27DCFB171` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card_in_cube`
--

DROP TABLE IF EXISTS `card_in_cube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_in_cube` (
  `card_id` bigint(20) NOT NULL DEFAULT '0',
  `cube_id` bigint(20) NOT NULL DEFAULT '0',
  `concreteCard_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`card_id`,`cube_id`),
  KEY `FK17E6F0E025B11CEE` (`cube_id`),
  KEY `FK17E6F0E0ED48BB1` (`concreteCard_id`),
  KEY `FK17E6F0E07DCFB171` (`card_id`),
  CONSTRAINT `FK17E6F0E07DCFB171` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FK17E6F0E025B11CEE` FOREIGN KEY (`cube_id`) REFERENCES `cube` (`id`),
  CONSTRAINT `FK17E6F0E0ED48BB1` FOREIGN KEY (`concreteCard_id`) REFERENCES `card_model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-05 22:17:57
