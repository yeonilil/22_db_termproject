-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: workshop
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (0,'안녕하세요','sae__bomi_',1,'sae__bomi_'),(1,'안녕하세요','sae__bomi_',1,'sae__bomi_'),(2,'안녕하세요','sae__bomi_',1,'sae__bomi_'),(3,'우와','sae__bomi_',2,'sae__bomi_'),(4,'와','sae__bomi_',2,'sae__bomi_'),(5,'ㅎㅎ','sae__bomi_',2,'sae__bomi_'),(6,'ㅎㅎㅎ','sae__bomi_',2002,'sae__bomi_'),(7,'안녕하세요','sae__bomi_',2002,'sae__bomi_'),(8,'ㅋㅋㅋㅋㅋㅋ','sae__bomi_',2002,'sae__bomi_'),(9,'ㅎㅎㅎㅎ','sae__bomi_',2002,'sae__bomi_'),(10,'ㅎㅎㅎㅎ','sae__bomi_',2001,'sae__bomi_'),(11,'댓글','sae__bomi_',2001,'sae__bomi_'),(12,'ㅎㅎㅎ','sae__bomi_',2001,'sae__bomi_'),(13,'반갑습니다','sae__bomi_',1,'sae__bomi_');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `your_id` varchar(64) DEFAULT NULL,
  `my_id` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES ('tia510','sae__bomi_'),('yeonilil','sae__bomi_');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `hashtag_id` int NOT NULL,
  `hashtag` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`hashtag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` int NOT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `content` varchar(200) DEFAULT NULL,
  `user_id` varchar(64) NOT NULL,
  `board_id` int DEFAULT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES ('첫번째 게시물입니다','sae__bomi_',1,1),('두번째 게시물입니다','sae_bomi__',1,2),('게시물 업로드!','sae_bomi__',1,3),('채윤 게시물입니당 ','tia510',2,2001),('두번째 채윤 게시물입니다!!!!','tia510',2,2002);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_hashtag`
--

DROP TABLE IF EXISTS `post_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_hashtag` (
  `post_hashtag_id` int NOT NULL,
  `post_id` int DEFAULT NULL,
  `hashtag_id2` int DEFAULT NULL,
  PRIMARY KEY (`post_hashtag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_hashtag`
--

LOCK TABLES `post_hashtag` WRITE;
/*!40000 ALTER TABLE `post_hashtag` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `msg` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('sae__bomi_','1234','01066493855','2002-02-14','kwonsaebom','bomi',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_comment_like`
--

DROP TABLE IF EXISTS `user_comment_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_comment_like` (
  `user_comment_like_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  PRIMARY KEY (`user_comment_like_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comment_like`
--

LOCK TABLES `user_comment_like` WRITE;
/*!40000 ALTER TABLE `user_comment_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_comment_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_comment_tag`
--

DROP TABLE IF EXISTS `user_comment_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_comment_tag` (
  `user_comment_tag_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  PRIMARY KEY (`user_comment_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comment_tag`
--

LOCK TABLES `user_comment_tag` WRITE;
/*!40000 ALTER TABLE `user_comment_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_comment_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_post_like`
--

DROP TABLE IF EXISTS `user_post_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_post_like` (
  `user_post_like_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`user_post_like_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post_like`
--

LOCK TABLES `user_post_like` WRITE;
/*!40000 ALTER TABLE `user_post_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_post_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_post_tag`
--

DROP TABLE IF EXISTS `user_post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_post_tag` (
  `user_post_tag_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`user_post_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post_tag`
--

LOCK TABLES `user_post_tag` WRITE;
/*!40000 ALTER TABLE `user_post_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_post_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-08 13:13:05
