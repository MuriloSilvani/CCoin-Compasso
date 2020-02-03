CREATE DATABASE  IF NOT EXISTS `liveb09` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `liveb09`;
-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: liveb09
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
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'Programador'),(2,'Analista de sistema'),(3,'Arquiteto de software'),(4,'Product Owner'),(5,'Gerente de Projeto');
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveis_acesso`
--

DROP TABLE IF EXISTS `niveis_acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveis_acesso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveis_acesso`
--

LOCK TABLES `niveis_acesso` WRITE;
/*!40000 ALTER TABLE `niveis_acesso` DISABLE KEYS */;
INSERT INTO `niveis_acesso` VALUES (1,'Administrador'),(2,'Usuário');
/*!40000 ALTER TABLE `niveis_acesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidades`
--

DROP TABLE IF EXISTS `unidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uf` varchar(2) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `endereco` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidades`
--

LOCK TABLES `unidades` WRITE;
/*!40000 ALTER TABLE `unidades` DISABLE KEYS */;
INSERT INTO `unidades` VALUES (1,'SC','Chapecó','Rua barão do rio branco, 4002E, Centro'),(2,'RS','Porto Alegre','Rua gaivota azul, 57D, Sala 404, Centro');
/*!40000 ALTER TABLE `unidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `nivel_acesso` int(11) NOT NULL,
  `cargo` int(11) NOT NULL,
  `unidade` int(11) NOT NULL,
  `credito` int(11) DEFAULT '0',
  `responsavel_unidade` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email` (`email`),
  KEY `nivel_acesso` (`nivel_acesso`),
  KEY `cargo` (`cargo`),
  KEY `unidade` (`unidade`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`nivel_acesso`) REFERENCES `niveis_acesso` (`id`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`cargo`) REFERENCES `cargos` (`id`),
  CONSTRAINT `usuarios_ibfk_3` FOREIGN KEY (`unidade`) REFERENCES `unidades` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'vitor','vitor@compasso.com.br','123','Vitor Apolinário',1,1,1,0,0),(2,'thiago','thiago@compasso.com.br','123','Thiago Andrade',1,1,1,0,0),(3,'paula','paula@compasso.com.br','123','Paula Greiner',1,2,1,0,0),(4,'murilo','murilo@compasso.com.br','123','Murilo Silvani',2,4,2,0,0),(5,'leonardo','leonardo@compasso.com.br','123','Leonardo Pieta',1,3,1,0,0),(6,'giohanna','giohanna@compasso.com.br','123','Giohanna',2,1,2,0,0),(7,'nicolas','nicolas@compasso.com.br','123','Nicolas',2,3,2,0,0),(8,'jonathan','jonathan@compasso.com.br','123','Jonathan',1,2,1,0,0),(9,'matheus','matheus@compasso.com.br','123','Matheus',1,3,1,0,0),(10,'yuri','yuri@compasso.com.br','123','Yuri',1,1,1,0,0),(11,'guilherme','guilherme@compasso.com.br','123','Guilherme Henrique Piasson',2,5,1,0,1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-31 16:58:30
