CREATE DATABASE  IF NOT EXISTS `cube_directory` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
USE `cube_directory`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: cube_directory
-- ------------------------------------------------------
-- Server version	5.5.28

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
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wizards_set`
--

LOCK TABLES `wizards_set` WRITE;
/*!40000 ALTER TABLE `wizards_set` DISABLE KEYS */;
INSERT INTO `wizards_set` VALUES (1,'rtr','Return to Ravnica'),(2,'avr','Avacyn Restored'),(3,'dka','Dark Ascension'),(4,'isd','Innistrad'),(5,'nph','New Phyrexia'),(6,'mbs','Mirrodin Besieged'),(7,'som','Scars of Mirrodin'),(8,'roe','Rise of the Eldrazi'),(9,'wwk','Worldwake'),(10,'zen','Zendikar'),(11,'arb','Alara Reborn'),(12,'cfx','Conflux'),(13,'ala','Shards of Alara'),(14,'eve','Eventide'),(15,'shm','Shadowmoor'),(16,'mt','Morningtide'),(17,'lw','Lorwyn'),(18,'fut','Future Sight'),(19,'pc','Planar Chaos'),(20,'ts','Time Spiral'),(21,'tsts','Time Spiral \"Timeshifted\"'),(22,'cs','Coldsnap'),(23,'ai','Alliances'),(24,'ia','Ice Age'),(25,'di','Dissension'),(26,'gp','Guildpact'),(27,'rav','Ravnica: City of Guilds'),(28,'sok','Saviors of Kamigawa'),(29,'bok','Betrayers of Kamigawa'),(30,'chk','Champions of Kamigawa'),(31,'5dn','Fifth Dawn'),(32,'ds','Darksteel'),(33,'mi','Mirrodin'),(34,'sc','Scourge'),(35,'le','Legions'),(36,'on','Onslaught'),(37,'ju','Judgment'),(38,'tr','Torment'),(39,'od','Odyssey'),(40,'ap','Apocalypse'),(41,'ps','Planeshift'),(42,'in','Invasion'),(43,'pr','Prophecy'),(44,'ne','Nemesis'),(45,'mm','Mercadian Masques'),(46,'ud','Urza\'s Destiny'),(47,'ul','Urza\'s Legacy'),(48,'us','Urza\'s Saga'),(49,'ex','Exodus'),(50,'sh','Stronghold'),(51,'tp','Tempest'),(52,'wl','Weatherlight'),(53,'vi','Visions'),(54,'mr','Mirage'),(55,'hl','Homelands'),(56,'fe','Fallen Empires'),(57,'dk','The Dark'),(58,'lg','Legends'),(59,'aq','Antiquities'),(60,'an','Arabian Nights'),(61,'m13','Magic 2013'),(62,'m12','Magic 2012'),(63,'m11','Magic 2011'),(64,'m10','Magic 2010'),(65,'10e','Tenth Edition'),(66,'9e','Ninth Edition'),(67,'8e','Eighth Edition'),(68,'7e','Seventh Edition'),(69,'6e','Classic Sixth Edition'),(70,'5e','Fifth Edition'),(71,'4e','Fourth Edition'),(72,'rv','Revised Edition'),(73,'un','Unlimited Edition'),(74,'be','Limited Edition Beta'),(75,'al','Limited Edition Alpha'),(76,'pd3','Premium Deck Series: Graveborn'),(77,'pd2','Premium Deck Series: Fire and Lightning'),(78,'pds','Premium Deck Series: Slivers'),(79,'dpa','Duels of the Planeswalkers'),(80,'ch','Chronicles'),(81,'pc2','Planechase 2012 Edition'),(82,'cmd','Commander'),(83,'arc','Archenemy'),(84,'pch','Planechase'),(85,'v12','From the Vault: Realms'),(86,'fvl','From the Vault: Legends'),(87,'fvr','From the Vault: Relics'),(88,'fve','From the Vault: Exiled'),(89,'fvd','From the Vault: Dragons'),(90,'ddj','Duel Decks: Izzet vs. Golgari'),(91,'ddi','Duel Decks: Venser vs. Koth'),(92,'ddh','Duel Decks: Ajani vs. Nicol Bolas'),(93,'ddg','Duel Decks: Knights vs. Dragons'),(94,'ddf','Duel Decks: Elspeth vs. Tezzeret'),(95,'pvc','Duel Decks: Phyrexia vs. The Coalition'),(96,'gvl','Duel Decks: Garruk vs. Liliana'),(97,'dvd','Duel Decks: Divine vs. Demonic'),(98,'jvc','Duel Decks: Jace vs. Chandra'),(99,'evg','Duel Decks: Elves vs. Goblins'),(100,'cstd','Coldsnap Theme Decks'),(101,'9eb','Ninth Edition Box Set'),(102,'8eb','Eighth Edition Box Set'),(103,'dm','Deckmasters'),(104,'bd','Beatdown Box Set'),(105,'br','Battle Royale Box Set'),(106,'at','Anthologies'),(107,'mgbc','Multiverse Gift Box Cards'),(108,'uh','Unhinged'),(109,'ug','Unglued'),(110,'uhaa','Unhinged Alternate Foils'),(111,'st2k','Starter 2000'),(112,'st','Starter 1999'),(113,'p3k','Portal Three Kingdoms'),(114,'po2','Portal Second Age'),(115,'po','Portal'),(116,'itp','Introductory Two-Player Set'),(117,'ced','Collector\'s Edition'),(118,'cedi','International Collectors\' Edition'),(119,'15ann','15th Anniversary'),(120,'gpx','Grand Prix'),(121,'pro','Pro Tour'),(122,'mgdc','Magic Game Day Cards'),(123,'wrl','Worlds'),(124,'drc','Dragon Con'),(125,'ptc','Prerelease Events'),(126,'rep','Release Events'),(127,'mlp','Magic: The Gathering Launch Parties'),(128,'sum','Summer of Magic'),(129,'grc','WPN/Gateway'),(130,'cp','Champs'),(131,'thgt','Two-Headed Giant Tournament'),(132,'arena','Arena League'),(133,'fnmp','Friday Night Magic'),(134,'mprp','Magic Player Rewards'),(135,'sus','Super Series'),(136,'hho','Happy Holidays'),(137,'jr','Judge Gift Program'),(138,'pot','Portal Demogame'),(139,'euro','European Land Program'),(140,'guru','Guru'),(141,'apac','Asia Pacific Land Program'),(142,'wotc','WotC Online Store'),(143,'uqc','Celebration Cards'),(144,'mbp','Media Inserts'),(145,'dcilm','Legend Membership'),(295,'gtc','Gatecrash'),(296,'cma','Commander\'s Arsenal');
/*!40000 ALTER TABLE `wizards_set` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-02-22 15:13:10
