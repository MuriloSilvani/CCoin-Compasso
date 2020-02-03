-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: livep07
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoque` (
  `id_item` int(11) NOT NULL,
  `id_tipo_item` int(11) NOT NULL,
  `qtde_reservado` int(11) DEFAULT '0',
  `qtde_disponivel` int(11) NOT NULL,
  KEY `id_item` (`id_item`),
  KEY `id_tipo_item` (`id_tipo_item`),
  CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`id_item`) REFERENCES `itens` (`id`),
  CONSTRAINT `estoque_ibfk_2` FOREIGN KEY (`id_tipo_item`) REFERENCES `tipos_itens` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabala excluisava para itens fisicos, que precisam de estoque';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (1,1,2,10),(2,2,0,10),(2,3,0,10),(2,4,0,10),(3,5,0,10),(4,6,0,10),(2,7,0,10),(2,8,0,10),(2,9,0,10);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens`
--

DROP TABLE IF EXISTS `itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens`
--

LOCK TABLES `itens` WRITE;
/*!40000 ALTER TABLE `itens` DISABLE KEYS */;
INSERT INTO `itens` VALUES (1,'Caneca'),(2,'Camiseta'),(3,'Dia de folga'),(4,'Headphone');
/*!40000 ALTER TABLE `itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_itens`
--

DROP TABLE IF EXISTS `tipos_itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_itens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Essa tabela é para diferenciar os tamanhos das camisas, os tipos das agendas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_itens`
--

LOCK TABLES `tipos_itens` WRITE;
/*!40000 ALTER TABLE `tipos_itens` DISABLE KEYS */;
INSERT INTO `tipos_itens` VALUES (1,'Caneca Compasso Branca'),(2,'Camiseta Compasso Azul P'),(3,'Camiseta Compasso Azul M'),(4,'Camiseta Compasso Azul G'),(5,'Folga'),(6,'Headphone Compasso'),(7,'Camiseta Compasso Branca P'),(8,'Camiseta Compasso Branca M'),(9,'Camiseta Compasso Branca G');
/*!40000 ALTER TABLE `tipos_itens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-03 17:32:09
