/*
SQLyog Community
MySQL - 5.7.18-log : Database - ys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ys`;

/*Table structure for table `tb_download_url` */

DROP TABLE IF EXISTS `tb_download_url`;

CREATE TABLE `tb_download_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vod_id` bigint(20) NOT NULL,
  `source_name` varchar(200) DEFAULT NULL,
  `download_url` varchar(500) DEFAULT NULL,
  `magnet_url` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `tb_series` */

DROP TABLE IF EXISTS `tb_series`;

CREATE TABLE `tb_series` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `tb_vod` */

DROP TABLE IF EXISTS `tb_vod`;

CREATE TABLE `tb_vod` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `name` varchar(300) DEFAULT NULL,
  `post_url` varchar(500) DEFAULT NULL,
  `content` text,
  `screenshot_url` varchar(500) DEFAULT NULL,
  `series_id` bigint(20) DEFAULT NULL,
  `vod_type` int(2) DEFAULT NULL,
  `country_type` int(2) DEFAULT NULL,
  `publish_date` date DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
