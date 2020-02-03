-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: livep08
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
-- Table structure for table `itens_resgates`
--

DROP TABLE IF EXISTS `itens_resgates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itens_resgates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_resgate` int(11) NOT NULL,
  `id_item` int(11) NOT NULL COMMENT 'Foreign Key para o banco de itens referenciando a tabela de itens o ID',
  `id_tipo_item` int(11) DEFAULT NULL COMMENT 'Foreign Key para o banco de itens referenciando a tabela tipo_itens e o campo ID caso necessario',
  `qtde` int(11) NOT NULL,
  `data_agendamento` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data do agendamento do home office ou folga',
  PRIMARY KEY (`id`),
  KEY `id_resgate` (`id_resgate`),
  CONSTRAINT `itens_resgates_ibfk_1` FOREIGN KEY (`id_resgate`) REFERENCES `resgates` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_resgates`
--

LOCK TABLES `itens_resgates` WRITE;
/*!40000 ALTER TABLE `itens_resgates` DISABLE KEYS */;
INSERT INTO `itens_resgates` VALUES (1,1,1,1,5,'2020-02-03 17:02:30'),(2,1,2,2,5,'2020-02-03 17:02:30'),(3,1,2,3,5,'2020-02-03 17:02:30');
/*!40000 ALTER TABLE `itens_resgates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resgates`
--

DROP TABLE IF EXISTS `resgates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resgates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL COMMENT 'Foreign Key para o banco de usuarios referenciando o ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resgates`
--

LOCK TABLES `resgates` WRITE;
/*!40000 ALTER TABLE `resgates` DISABLE KEYS */;
INSERT INTO `resgates` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `resgates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_requerimentos`
--

DROP TABLE IF EXISTS `status_requerimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_requerimentos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_resgate` int(11) DEFAULT NULL,
  `id_transferencia` int(11) DEFAULT NULL,
  `id_status` int(11) NOT NULL COMMENT 'Valores padrão 0: em analise, 1: aprovado, 2: a caminho, 3: entregue, 4: cancelado, 5: Reprovado',
  `data_status` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_resgate` (`id_resgate`),
  KEY `id_transferencia` (`id_transferencia`),
  CONSTRAINT `status_requerimentos_ibfk_1` FOREIGN KEY (`id_resgate`) REFERENCES `resgates` (`id`),
  CONSTRAINT `status_requerimentos_ibfk_2` FOREIGN KEY (`id_transferencia`) REFERENCES `transferencias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Essa tabela irá receber ou uma foreign key para resgate ou uma para transferencia, não podendo receber as duas. Essa tabeal será para historico';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_requerimentos`
--

LOCK TABLES `status_requerimentos` WRITE;
/*!40000 ALTER TABLE `status_requerimentos` DISABLE KEYS */;
INSERT INTO `status_requerimentos` VALUES (1,1,1,0,'2020-02-03 20:13:56'),(2,1,2,1,'2020-02-03 20:13:56'),(3,1,3,2,'2020-02-03 20:13:56'),(4,1,4,3,'2020-02-03 20:13:56'),(5,1,5,4,'2020-02-03 20:13:56'),(6,1,6,5,'2020-02-03 20:13:56'),(7,1,7,0,'2020-02-03 20:13:56'),(8,1,8,1,'2020-02-03 20:13:56'),(9,1,9,2,'2020-02-03 20:13:56'),(10,1,10,3,'2020-02-03 20:13:56'),(11,1,11,4,'2020-02-03 20:13:56');
/*!40000 ALTER TABLE `status_requerimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencias`
--

DROP TABLE IF EXISTS `transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferencias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_origem` int(11) NOT NULL COMMENT 'Foreign Key para o banco de usuarios referenciando o ID',
  `usuario_destino` int(11) NOT NULL COMMENT 'Foreign Key para o banco de usuarios referenciando o ID',
  `valor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencias`
--

LOCK TABLES `transferencias` WRITE;
/*!40000 ALTER TABLE `transferencias` DISABLE KEYS */;
INSERT INTO `transferencias` VALUES (1,1,2,10),(2,2,3,10),(3,3,4,15),(4,4,5,15),(5,5,6,20),(6,6,7,25),(7,7,8,25),(8,8,9,30),(9,9,10,30),(10,10,11,30),(11,10,1,20);
/*!40000 ALTER TABLE `transferencias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-03 17:33:11
