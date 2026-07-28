-- MySQL dump 10.13  Distrib 8.0.46, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: hadidy
-- ------------------------------------------------------
-- Server version	9.7.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'e746f936-7b93-11f1-b8b9-fa1931bbe283:1-128';

--
-- Table structure for table `diet_plan`
--

DROP TABLE IF EXISTS `diet_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diet_plan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `profile_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrhgc8cjews9497kv8kfpu8uky` (`profile_id`),
  CONSTRAINT `FKrhgc8cjews9497kv8kfpu8uky` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `reps` int DEFAULT NULL,
  `sets` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `workout_day_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoyf1w4sdslgjy36nagind7yjl` (`workout_day_id`),
  CONSTRAINT `FKoyf1w4sdslgjy36nagind7yjl` FOREIGN KEY (`workout_day_id`) REFERENCES `workout_day` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal` (
  `calories` double DEFAULT NULL,
  `carbs` double DEFAULT NULL,
  `fats` double DEFAULT NULL,
  `protein` double DEFAULT NULL,
  `diet_plan_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `recipe` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj517cn5htuei6et8o3ecd1pwx` (`diet_plan_id`),
  CONSTRAINT `FKj517cn5htuei6et8o3ecd1pwx` FOREIGN KEY (`diet_plan_id`) REFERENCES `diet_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `bmi` double DEFAULT NULL,
  `end_subscribe` date DEFAULT NULL,
  `gym_price` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `start_subscribe` date DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `profile_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gmail` varchar(255) DEFAULT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  UNIQUE KEY `UKc1dkiawnlj6uoe6fnlwd6j83j` (`user_id`),
  CONSTRAINT `FKawh070wpue34wqvytjqr4hj5e` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `progress_tracking`
--

DROP TABLE IF EXISTS `progress_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progress_tracking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `height` double DEFAULT NULL,
  `is_public` bit(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `taken_data` date DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhxxojjuut6ijswim8ner1uf5p` (`user_id`),
  CONSTRAINT `FKhxxojjuut6ijswim8ner1uf5p` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplement`
--

DROP TABLE IF EXISTS `supplement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplement` (
  `price` double DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `profile_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfi4f680xpmbq4g322pfwvru5q` (`profile_id`),
  CONSTRAINT `FKfi4f680xpmbq4g322pfwvru5q` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workout_day`
--

DROP TABLE IF EXISTS `workout_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_day` (
  `expected_time` double DEFAULT NULL,
  `total_exercises` int DEFAULT NULL,
  `total_repeat` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `workout_plan_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsgaupql56jotq0brl5o9nlbc7` (`workout_plan_id`),
  CONSTRAINT `FKsgaupql56jotq0brl5o9nlbc7` FOREIGN KEY (`workout_plan_id`) REFERENCES `workout_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workout_plan`
--

DROP TABLE IF EXISTS `workout_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_plan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `profile_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlk40s7nthfthn53eelnfsdkas` (`profile_id`),
  CONSTRAINT `FKlk40s7nthfthn53eelnfsdkas` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-28 20:48:55
