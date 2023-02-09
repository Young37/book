-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: book
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `addr_num` bigint NOT NULL AUTO_INCREMENT,
  `user_num` bigint NOT NULL,
  `zip_code` varchar(30) DEFAULT NULL,
  `default_addr` varchar(60) DEFAULT NULL,
  `detail_addr` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`addr_num`),
  KEY `FK_user_TO_address_1` (`user_num`),
  CONSTRAINT `FK_user_TO_address_1` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,'c','a','b'),(2,2,'c','a','b'),(3,3,'c','a','b');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_num` bigint NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) DEFAULT NULL,
  `book_stock` int DEFAULT NULL,
  `book_price` int DEFAULT NULL,
  `book_point_rate` int DEFAULT NULL,
  PRIMARY KEY (`book_num`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'바람',200,2000,NULL),(2,'독백',2000,30000,NULL),(3,'나무',200,35000,NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_cart`
--

DROP TABLE IF EXISTS `book_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_cart` (
  `book_cart_num` bigint NOT NULL AUTO_INCREMENT,
  `book_num` bigint NOT NULL,
  `cart_num` bigint NOT NULL,
  `book_cart_amount` int DEFAULT NULL,
  `book_cart_price` int DEFAULT NULL,
  PRIMARY KEY (`book_cart_num`),
  KEY `FK_book_TO_book_cart_1` (`book_num`),
  KEY `FK_cart_TO_book_cart_1` (`cart_num`),
  CONSTRAINT `FK_book_TO_book_cart_1` FOREIGN KEY (`book_num`) REFERENCES `book` (`book_num`),
  CONSTRAINT `FK_cart_TO_book_cart_1` FOREIGN KEY (`cart_num`) REFERENCES `cart` (`cart_num`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_cart`
--

LOCK TABLES `book_cart` WRITE;
/*!40000 ALTER TABLE `book_cart` DISABLE KEYS */;
INSERT INTO `book_cart` VALUES (1,1,1,3,6000);
/*!40000 ALTER TABLE `book_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_month_sell_count`
--

DROP TABLE IF EXISTS `book_month_sell_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_month_sell_count` (
  `sell_year_month` int NOT NULL,
  `book_num` bigint NOT NULL,
  `sell_count` int NOT NULL,
  PRIMARY KEY (`sell_year_month`,`book_num`),
  KEY `fk_best_sellor_book1_idx` (`book_num`),
  CONSTRAINT `fk_best_sellor_book1` FOREIGN KEY (`book_num`) REFERENCES `book` (`book_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_month_sell_count`
--

LOCK TABLES `book_month_sell_count` WRITE;
/*!40000 ALTER TABLE `book_month_sell_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_month_sell_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_order`
--

DROP TABLE IF EXISTS `book_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_order` (
  `book_order_num` bigint NOT NULL AUTO_INCREMENT,
  `order_num` bigint NOT NULL,
  `book_num` bigint NOT NULL,
  `book_order_amount` int DEFAULT NULL,
  `book_order_price` int DEFAULT NULL,
  PRIMARY KEY (`book_order_num`),
  KEY `FK_order_TO_book_order_1` (`order_num`),
  KEY `FK_book_TO_book_order_1` (`book_num`),
  CONSTRAINT `FK_book_TO_book_order_1` FOREIGN KEY (`book_num`) REFERENCES `book` (`book_num`),
  CONSTRAINT `FK_order_TO_book_order_1` FOREIGN KEY (`order_num`) REFERENCES `order` (`order_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_order`
--

LOCK TABLES `book_order` WRITE;
/*!40000 ALTER TABLE `book_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `card_num` bigint NOT NULL,
  `user_num` bigint NOT NULL,
  `card_valid_date` datetime DEFAULT NULL,
  `card_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`card_num`),
  KEY `FK_user_TO_card_1` (`user_num`),
  CONSTRAINT `FK_user_TO_card_1` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_num` bigint NOT NULL AUTO_INCREMENT,
  `user_num` bigint NOT NULL,
  `cart_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_num`),
  KEY `FK_user_TO_cart_1` (`user_num`),
  CONSTRAINT `FK_user_TO_cart_1` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,'2021-11-29 21:17:47');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_num` bigint NOT NULL AUTO_INCREMENT,
  `user_num` bigint NOT NULL,
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `order_total` int DEFAULT NULL,
  `order_zip_code` varchar(30) DEFAULT NULL,
  `order_default_addr` varchar(60) DEFAULT NULL,
  `order_detail_addr` varchar(60) DEFAULT NULL,
  `order_card_num` bigint DEFAULT NULL,
  `order_card_valid_date` datetime DEFAULT NULL,
  `order_card_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_num`),
  KEY `FK_user_TO_order_1` (`user_num`),
  CONSTRAINT `FK_user_TO_order_1` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_num` bigint NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `id` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'어드민','admin','123'),(2,'플라이','fly','123'),(3,'영훈','yh','123'),(4,'aa','a','aa');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-27 16:27:07
