-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: futbol
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
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `idequipo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `estadio` varchar(255) NOT NULL,
  `pais` varchar(255) NOT NULL,
  PRIMARY KEY (`idequipo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (1,'Real Madrid FC','Santiago de Bernabeu','España'),(2,'Barcelona C.F','Camp Nou','España'),(3,'Paris Saint-Germain Football Club','Parc des Princes','Francia'),(4,'Olympique de Lyon','Parc Olympique Lyonnais','Francia'),(5,'Club Atlético de Madrid','Cívitas Metropolitano','España'),(6,'Manchester United','Old Trafford','Reino Unido'),(7,' Chelsea','Stamford Bridge','Reino Unido'),(8,'Bayern','Allianz Arena','Alemania'),(9,'Borussia Dortmund','Signal Iduna Park','Alemania'),(10,'Una pandilla de renacuajos','Estadio secreto','Estados Unidos de América'),(12,'Real Sociedad','Estadio Reale(Anoeta)','España');
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores` (
  `idjugador` bigint NOT NULL AUTO_INCREMENT,
  `idequipo` int DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `primerapellido` varchar(255) NOT NULL,
  `segundoapellido` varchar(255) DEFAULT NULL,
  `edad` int NOT NULL,
  `posicion` varchar(255) DEFAULT NULL,
  `document` varchar(20) NOT NULL,
  PRIMARY KEY (`idjugador`),
  KEY `FK_equipo_jugador` (`idequipo`),
  CONSTRAINT `FK_equipo_jugador` FOREIGN KEY (`idequipo`) REFERENCES `equipos` (`idequipo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` VALUES (1,8,'Bryan','Zaragoza','',27,'Defensa','81956647B'),(2,NULL,'Marcos','Llorente','',29,'CentroCampista','51888845V'),(3,5,'Horațiu','Moldovan','',26,'Portero','Z6967196H'),(4,5,'Antoine','Griezmann','',32,'Delantero','Z4079155H'),(5,1,'Daniel','Carvajal','Ramos',32,'Defensa','09205535S'),(6,NULL,'Robin','Le Normand','',27,'Defensa','Y3268254Z'),(7,NULL,'Lionel Andrés','Messi','Cuccitini',37,'Delantero','Z9770067V'),(8,NULL,'Alvaro','Blanco','Sangines',30,'Centro','06190849P');
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-21 11:11:42
