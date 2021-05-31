-- MariaDB dump 10.19  Distrib 10.5.9-MariaDB, for Win64 (AMD64)
--
-- Host: k4a106.p.ssafy.io    Database: checkmate
-- ------------------------------------------------------
-- Server version	10.5.9-MariaDB-1:10.5.9+maria~focal

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
-- Table structure for table `correction`
--

DROP TABLE IF EXISTS `correction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `correction` (
  `correction_id` int(11) NOT NULL AUTO_INCREMENT,
  `correction_before` varchar(255) NOT NULL,
  `correction_after` varchar(255) NOT NULL,
  `correction_category` int(11) DEFAULT 0,
  PRIMARY KEY (`correction_id`),
  UNIQUE KEY `correction_id_UNIQUE` (`correction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `correction`
--

LOCK TABLES `correction` WRITE;
/*!40000 ALTER TABLE `correction` DISABLE KEYS */;
INSERT INTO `correction` VALUES (1,'되고 있는 것 같다','된다',1),(2,'생각된다','생각한다',2),(3,'되어지고 있다','된다',2),(4,'보여진다','보인다',2),(5,'로 측정되었다','이었다',2),(6,'발견되었다','있었다',2),(7,'법론','법',3),(8,'학적으로','적으로',3),(9,'는 것으로 보인다','다',4),(10,'데 있어서','데',5),(11,'과의','과',5),(12,'와의','와',5),(13,'로의','로',5),(14,'부터의','부터',5),(15,'되었었다','되었다',6),(16,'되어져 왔다','되었다',6),(17,'았었다','았다',6),(18,'했었더라면','했더라면',6),(19,'을 갖는다','이 있다',6),(20,'를 갖는다','가 있다',6),(21,'혹은','또는',7),(22,'증가할','늘어날',7),(23,'이외에도','이밖에도',7),(24,'유발시키다','일으키다',7),(25,'우월하다','낫다',7),(26,'열등하다','못하다',7),(27,'촉지된다','만져진다',7),(28,'시행하다','하다',7);
/*!40000 ALTER TABLE `correction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-31 11:01:07
