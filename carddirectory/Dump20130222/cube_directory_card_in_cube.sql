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
-- Table structure for table `card_in_cube`
--

DROP TABLE IF EXISTS `card_in_cube`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_in_cube` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cardId` bigint(20) NOT NULL,
  `concreteCard_id` bigint(20) DEFAULT NULL,
  `cubeId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cubeId` (`cubeId`,`cardId`),
  KEY `FK17E6F0E0ED48BB1` (`concreteCard_id`),
  KEY `FK17E6F0E092FB6F99` (`cubeId`),
  KEY `FK17E6F0E0B4758F2` (`cardId`),
  CONSTRAINT `FK17E6F0E092FB6F99` FOREIGN KEY (`cubeId`) REFERENCES `cube` (`id`),
  CONSTRAINT `FK17E6F0E0B4758F2` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`),
  CONSTRAINT `FK17E6F0E0ED48BB1` FOREIGN KEY (`concreteCard_id`) REFERENCES `card_model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=594 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_in_cube`
--

LOCK TABLES `card_in_cube` WRITE;
/*!40000 ALTER TABLE `card_in_cube` DISABLE KEYS */;
INSERT INTO `card_in_cube` VALUES (1,231,NULL,1),(2,238,NULL,1),(3,247,NULL,1),(4,339,NULL,1),(5,575,NULL,1),(6,581,NULL,1),(7,623,NULL,1),(8,651,NULL,1),(9,662,NULL,1),(10,672,NULL,1),(11,713,NULL,1),(12,748,NULL,1),(13,757,NULL,1),(14,783,NULL,1),(15,784,NULL,1),(16,785,NULL,1),(17,788,NULL,1),(18,793,NULL,1),(19,823,NULL,1),(20,837,NULL,1),(21,839,NULL,1),(22,864,NULL,1),(23,869,NULL,1),(24,889,NULL,1),(25,912,NULL,1),(26,916,NULL,1),(27,942,NULL,1),(28,968,NULL,1),(29,1011,NULL,1),(30,1139,NULL,1),(31,1183,NULL,1),(32,1313,NULL,1),(33,1333,NULL,1),(34,1456,NULL,1),(35,1462,NULL,1),(36,1514,NULL,1),(37,1607,NULL,1),(38,1626,NULL,1),(39,1710,NULL,1),(40,1760,NULL,1),(41,1774,NULL,1),(42,1779,NULL,1),(43,1783,NULL,1),(44,1802,NULL,1),(45,1823,NULL,1),(46,1828,NULL,1),(47,1838,NULL,1),(48,1840,NULL,1),(49,1843,NULL,1),(50,1851,NULL,1),(51,1852,NULL,1),(52,1854,NULL,1),(53,1865,NULL,1),(54,1868,NULL,1),(55,1876,NULL,1),(56,1878,NULL,1),(57,1888,NULL,1),(58,1894,NULL,1),(59,1895,NULL,1),(60,1903,NULL,1),(61,1914,NULL,1),(62,1942,NULL,1),(63,1943,NULL,1),(64,1946,NULL,1),(65,1947,NULL,1),(66,1967,NULL,1),(67,1991,NULL,1),(68,1995,NULL,1),(69,1998,NULL,1),(70,2004,NULL,1),(71,2006,NULL,1),(72,2010,NULL,1),(73,2019,NULL,1),(74,2034,NULL,1),(75,2036,NULL,1),(76,2044,NULL,1),(77,2046,NULL,1),(78,2070,NULL,1),(79,2082,NULL,1),(80,2100,NULL,1),(81,2119,NULL,1),(82,2129,NULL,1),(83,2149,NULL,1),(84,2155,NULL,1),(85,2190,NULL,1),(86,2216,NULL,1),(87,2264,NULL,1),(88,2270,NULL,1),(89,2273,NULL,1),(90,2294,NULL,1),(91,2323,NULL,1),(92,2343,NULL,1),(93,2345,NULL,1),(94,2350,NULL,1),(95,2360,NULL,1),(96,2364,NULL,1),(97,2403,NULL,1),(98,2422,NULL,1),(99,2442,NULL,1),(100,2493,NULL,1),(101,2494,NULL,1),(102,2495,NULL,1),(103,2497,NULL,1),(104,2501,NULL,1),(105,2505,NULL,1),(106,2521,NULL,1),(107,2526,NULL,1),(108,2532,NULL,1),(109,2543,NULL,1),(110,2561,NULL,1),(111,2563,NULL,1),(112,2567,NULL,1),(113,2569,NULL,1),(114,2570,NULL,1),(115,2576,NULL,1),(116,2577,NULL,1),(117,2592,NULL,1),(118,2630,NULL,1),(119,2633,NULL,1),(120,2650,NULL,1),(121,2666,NULL,1),(122,2668,NULL,1),(123,2709,NULL,1),(124,2731,NULL,1),(125,2768,NULL,1),(126,2773,NULL,1),(127,2812,NULL,1),(128,2825,NULL,1),(129,2842,NULL,1),(130,2857,NULL,1),(131,2860,NULL,1),(132,2894,NULL,1),(133,2921,NULL,1),(134,2937,NULL,1),(135,2941,NULL,1),(136,2952,NULL,1),(137,2954,NULL,1),(138,2968,NULL,1),(139,2994,NULL,1),(140,3021,NULL,1),(141,3027,NULL,1),(142,3029,NULL,1),(143,3052,NULL,1),(144,3072,NULL,1),(145,3095,NULL,1),(146,3118,NULL,1),(147,3145,NULL,1),(148,3180,NULL,1),(149,3185,NULL,1),(150,3186,NULL,1),(151,3189,NULL,1),(152,3194,NULL,1),(153,3196,NULL,1),(154,3207,NULL,1),(155,3234,NULL,1),(156,3249,NULL,1),(157,3254,NULL,1),(158,3256,NULL,1),(159,3257,NULL,1),(160,3302,NULL,1),(161,3306,NULL,1),(162,3317,NULL,1),(163,3361,NULL,1),(164,3377,NULL,1),(165,3380,NULL,1),(166,3398,NULL,1),(167,3411,NULL,1),(168,3424,NULL,1),(169,3452,NULL,1),(170,3453,NULL,1),(171,3454,NULL,1),(172,3455,NULL,1),(173,3456,NULL,1),(174,3615,NULL,1),(175,3658,NULL,1),(176,3665,NULL,1),(177,3707,NULL,1),(178,3717,NULL,1),(179,3750,NULL,1),(180,3784,NULL,1),(181,3807,NULL,1),(182,3829,NULL,1),(183,3840,NULL,1),(184,3843,NULL,1),(185,3856,NULL,1),(186,3865,NULL,1),(187,3872,NULL,1),(188,3882,NULL,1),(189,3888,NULL,1),(190,3892,NULL,1),(191,3922,NULL,1),(192,3932,NULL,1),(193,3934,NULL,1),(194,3944,NULL,1),(195,3966,NULL,1),(196,3973,NULL,1),(197,3999,NULL,1),(198,4007,NULL,1),(199,4013,NULL,1),(200,4020,NULL,1),(201,4025,NULL,1),(202,4056,NULL,1),(203,4059,NULL,1),(204,4076,NULL,1),(205,4078,NULL,1),(206,4084,NULL,1),(207,4092,NULL,1),(208,4108,NULL,1),(209,4114,NULL,1),(210,4167,NULL,1),(211,4172,NULL,1),(212,4175,NULL,1),(213,4216,NULL,1),(214,4231,NULL,1),(215,4336,NULL,1),(216,4376,NULL,1),(217,4425,NULL,1),(218,4505,NULL,1),(219,4558,NULL,1),(220,4588,NULL,1),(221,4633,NULL,1),(222,4708,NULL,1),(223,4710,NULL,1),(224,4788,NULL,1),(225,4880,NULL,1),(226,4911,NULL,1),(227,4922,NULL,1),(228,4937,NULL,1),(229,4981,NULL,1),(230,4992,NULL,1),(231,5020,NULL,1),(232,5026,NULL,1),(233,5027,NULL,1),(234,5031,NULL,1),(235,5036,NULL,1),(236,5038,NULL,1),(237,5070,NULL,1),(238,5115,NULL,1),(239,5148,NULL,1),(240,5159,NULL,1),(241,5160,NULL,1),(242,5164,NULL,1),(243,5187,NULL,1),(244,5189,NULL,1),(245,5192,NULL,1),(246,5195,NULL,1),(247,5196,NULL,1),(248,5198,NULL,1),(249,5217,NULL,1),(250,5241,NULL,1),(251,5262,NULL,1),(252,5280,NULL,1),(253,5316,NULL,1),(254,5324,NULL,1),(255,5352,NULL,1),(256,5360,NULL,1),(257,5362,NULL,1),(258,5372,NULL,1),(259,5406,NULL,1),(260,5420,NULL,1),(261,5446,NULL,1),(262,5454,NULL,1),(263,5459,NULL,1),(264,5461,NULL,1),(265,5469,NULL,1),(266,5474,NULL,1),(267,5475,NULL,1),(268,5477,NULL,1),(269,5478,NULL,1),(270,5479,NULL,1),(271,5483,NULL,1),(272,5673,NULL,1),(273,5697,NULL,1),(274,5725,NULL,1),(275,5760,NULL,1),(276,5888,NULL,1),(277,5896,NULL,1),(278,6005,NULL,1),(279,6054,NULL,1),(280,6068,NULL,1),(281,6142,NULL,1),(282,6169,NULL,1),(283,6187,NULL,1),(284,6235,NULL,1),(285,6257,NULL,1),(286,6277,NULL,1),(287,6339,NULL,1),(288,6345,NULL,1),(289,6391,NULL,1),(290,6404,NULL,1),(291,6419,NULL,1),(292,6428,NULL,1),(293,6439,NULL,1),(294,6444,NULL,1),(295,6459,NULL,1),(296,6471,NULL,1),(297,6544,NULL,1),(298,6547,NULL,1),(299,6566,NULL,1),(300,6568,NULL,1),(301,6577,NULL,1),(302,6584,NULL,1),(303,6590,NULL,1),(304,6623,NULL,1),(305,6632,NULL,1),(306,6634,NULL,1),(307,6656,NULL,1),(308,6658,NULL,1),(309,6660,NULL,1),(310,6676,NULL,1),(311,6725,NULL,1),(312,6729,NULL,1),(313,6775,NULL,1),(314,6780,NULL,1),(315,6815,NULL,1),(316,6880,NULL,1),(317,7039,NULL,1),(318,7188,NULL,1),(319,7575,NULL,1),(320,7593,NULL,1),(321,7739,NULL,1),(322,7786,NULL,1),(323,7874,NULL,1),(324,7875,NULL,1),(325,7883,NULL,1),(326,7966,NULL,1),(327,8060,NULL,1),(328,8061,NULL,1),(329,8256,NULL,1),(330,8350,NULL,1),(331,8756,NULL,1),(332,8855,NULL,1),(333,9149,NULL,1),(334,9209,NULL,1),(335,9353,NULL,1),(336,9384,NULL,1),(337,9399,NULL,1),(338,9448,NULL,1),(339,9474,NULL,1),(340,9510,NULL,1),(341,9552,NULL,1),(342,9712,NULL,1),(343,9728,NULL,1),(344,9737,NULL,1),(345,9864,NULL,1),(346,9923,NULL,1),(347,9990,NULL,1),(348,10003,NULL,1),(349,10013,NULL,1),(350,10097,NULL,1),(351,10128,NULL,1),(352,10145,NULL,1),(353,10153,NULL,1),(354,10201,NULL,1),(355,10443,NULL,1),(356,10627,NULL,1),(357,10642,NULL,1),(358,11086,NULL,1),(359,11258,NULL,1),(360,11383,NULL,1),(361,11576,NULL,1),(362,11631,NULL,1),(363,11632,NULL,1),(364,11635,NULL,1),(365,11646,NULL,1),(366,11655,NULL,1),(367,11660,NULL,1),(368,11661,NULL,1),(369,11676,NULL,1),(370,11678,NULL,1),(371,11679,NULL,1),(372,11693,NULL,1),(373,11698,NULL,1),(374,11704,NULL,1),(375,11724,NULL,1),(376,11736,NULL,1),(377,11753,NULL,1),(378,11761,NULL,1),(379,11773,NULL,1),(380,11777,NULL,1),(381,11781,NULL,1),(382,11789,NULL,1),(383,11807,NULL,1),(384,11826,NULL,1),(385,11840,NULL,1),(386,11848,NULL,1),(387,11895,NULL,1),(388,11953,NULL,1),(389,11983,NULL,1),(390,12017,NULL,1),(391,12040,NULL,1),(392,12052,NULL,1),(393,12061,NULL,1),(394,12066,NULL,1),(395,12080,NULL,1),(396,12106,NULL,1),(397,12111,NULL,1),(398,12117,NULL,1),(399,12225,NULL,1),(400,12226,NULL,1),(401,12232,NULL,1),(402,12233,NULL,1),(403,12248,NULL,1),(404,12269,NULL,1),(512,11990,NULL,1),(513,9226,NULL,1),(514,9907,NULL,1),(515,1026,NULL,1),(516,460,NULL,1),(517,454,NULL,1),(518,12069,NULL,1),(519,8120,NULL,1),(520,11872,NULL,1),(521,4689,NULL,1),(522,596,NULL,1),(523,4133,NULL,1),(524,1336,NULL,1),(525,11719,NULL,1),(526,11977,NULL,1),(527,11711,NULL,1),(528,1162,NULL,1),(529,1131,NULL,1),(530,1310,NULL,1),(531,10435,NULL,1),(532,1941,NULL,1),(533,1295,NULL,1),(534,9870,NULL,1),(535,4561,NULL,1),(536,5898,NULL,1),(537,11948,NULL,1),(538,11830,NULL,1),(539,9733,NULL,1),(540,11245,NULL,1),(541,7493,NULL,1),(542,6494,NULL,1),(543,1545,NULL,1),(544,2229,NULL,1),(545,708,NULL,1),(546,495,NULL,1),(547,9259,NULL,1),(548,1089,NULL,1),(549,1698,NULL,1),(550,1495,NULL,1),(551,670,NULL,1),(552,1098,NULL,1),(553,1250,NULL,1),(554,4859,NULL,1),(555,2088,NULL,1),(556,1185,NULL,1),(557,2164,NULL,1),(558,1412,NULL,1),(559,10410,NULL,1),(560,1409,NULL,1),(561,6260,NULL,1),(562,1811,NULL,1),(563,1807,NULL,1),(564,1428,NULL,1),(565,1426,NULL,1),(566,6700,NULL,1),(567,1188,NULL,1),(568,1076,NULL,1),(569,1424,NULL,1),(570,1408,NULL,1),(571,1437,NULL,1),(572,1203,NULL,1),(573,220,NULL,1),(574,214,NULL,1),(575,73,NULL,1),(576,172,NULL,1),(577,243,NULL,1),(578,241,NULL,1),(579,86,NULL,1),(580,96,NULL,1),(581,226,NULL,1),(582,221,NULL,1),(583,177,NULL,1),(584,17,NULL,1),(585,120,NULL,1),(586,82,NULL,1),(587,141,NULL,1),(588,98,NULL,1),(589,143,NULL,1),(590,83,NULL,1),(591,223,NULL,1),(592,194,NULL,1),(593,158,NULL,1);
/*!40000 ALTER TABLE `card_in_cube` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-02-22 15:13:00