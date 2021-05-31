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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_email` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `member_nickname` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `member_password` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `member_native_lang` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `member_profile_url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `member_point` int(11) DEFAULT NULL,
  `member_introduce` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `member_type_id` varchar(45) CHARACTER SET utf8mb4 NOT NULL DEFAULT '1',
  `member_grade` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'test@test.com','바르다김선생','937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244','ko','',4900,'','0',0),(2,'english@test.com','englishman','6cda71722e02332b19bb03b234cbcc5a6e84864b79f3a04a7e491db551d465f5','en','',34620,'','0',5),(3,'ingu1234@naver.com','최준','49b7bd43edbac0acc5ca1afbc2f0d919f690141207377c2c2c8ce4dcfc6e6d36','ko','',50,'','0',4),(4,'china@test.com','왕자림','201d05e092b813bebffa3816f266d18a6b930c803271d06112ac1518e9703706','zh','',10,'','0',5),(5,'ljh@naver.com','하이헬로우안녕','24baec588002ec54ec76a915eab6fcf9cbd1d1e9feaf059a8c219d43238975f7','ko','',750,'','0',0),(6,'ssafy@ssafy.com','체크메이트','997b9bcaaea88972d36028a705e9dfa745a17a312ee2f1cb039706988bf5f6eb','ko','',1710,'','0',5),(7,'sysy@ssafy.com','시즈너','ef51306214d9a6361ee1d5b452e6d2bb70dc7ebb85bf9e02c3d4747fb57d6bec','en','',50,'','0',4),(9,'test1@test.com','Mate Lee','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae','en','',0,'','0',0),(10,'test2@test.com','你好朋友','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae','zh','',2600,'','0',0),(11,'china123@gmail.com','chinaMan','201d05e092b813bebffa3816f266d18a6b930c803271d06112ac1518e9703706','zh','',0,'','0',0),(12,'english99@gmail.com','qwerty66','ef51306214d9a6361ee1d5b452e6d2bb70dc7ebb85bf9e02c3d4747fb57d6bec','en','',0,'','0',0),(13,'1234qwer@gmail.com','qwertyman','ef51306214d9a6361ee1d5b452e6d2bb70dc7ebb85bf9e02c3d4747fb57d6bec','zh','',0,'','0',0),(14,'test5@test.com','테스트계정','db86357a9fb9c9afb883eecc3aecf554bdaed118ef2d0f3124d0bcf2a3af9168','ko','',0,'','0',0),(15,'hackers@gmail.com','해커스언어교사','4d4f26369171994f3a46776ee2d88494fb9955800a5bb6261c016c4bb9f30b56','ko','',10,'','0',0),(16,'lab1.ssafy@multicampus.com','싸피랩1','6aba834f044ee35f70a5faa0e2c22b67477b5ac6dcdcd83fc2ce906ba72a8e1d','ko','',0,'','0',0),(17,'bern840901@gmail.com','HBNI','f1cb41a0fa8e492f983b3aedbdc3b5668642d6de07e29fdfa601eac89a0826','zh','',0,'','0',0),(18,'sysy123@ssafy.com','체크메이트짱','ef51306214d9a6361ee1d5b452e6d2bb70dc7ebb85bf9e02c3d4747fb57d6bec','ko','',0,'','0',0),(19,'sunshine4429@gmail.com','JBJB','f20dbd420affac86394d18917f9a76e98fc44cff9ac881f94f532c15099be412','ko','',0,'','0',0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-31 11:01:08
