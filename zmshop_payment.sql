/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_payment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_payment` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_payment`;

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `creator` varchar(25) NOT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `account` varchar(255) DEFAULT NULL,
  `amount` decimal(21,6) NOT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `fee` decimal(21,6) NOT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `method` int(11) NOT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `payer` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `sn` varchar(255) NOT NULL,
  `order_uuid` char(20) NOT NULL,
  `del_flag` bit(1) NOT NULL DEFAULT b'0',
  `uuid` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uni_payment_sn` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `payment` */

/*Table structure for table `payment_config` */

DROP TABLE IF EXISTS `payment_config`;

CREATE TABLE `payment_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(25) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '修改人',
  `name` varchar(255) DEFAULT NULL COMMENT '支付宝/微信/银联',
  `del_flag` bit(1) NOT NULL DEFAULT b'0',
  `type` int(1) DEFAULT NULL COMMENT '1：支付宝，2：微信，3：银联',
  `mch_id` varchar(64) DEFAULT NULL COMMENT '商户号（商户在平台注册的商户号）',
  `app_id` varchar(255) DEFAULT NULL COMMENT '应用id',
  `app_key` varchar(255) DEFAULT NULL COMMENT '安全私钥',
  `app_secret` varchar(255) DEFAULT NULL COMMENT 'appid对应的接口密码，接口调用授权',
  `is_used` bit(1) DEFAULT NULL COMMENT '是否启用',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='支付平台参数设置';

/*Data for the table `payment_config` */

/*Table structure for table `payment_log` */

DROP TABLE IF EXISTS `payment_log`;

CREATE TABLE `payment_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `creator` varchar(25) NOT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `version` int(11) NOT NULL,
  `amount` decimal(21,6) NOT NULL,
  `fee` decimal(21,6) NOT NULL,
  `payment_plugin_id` varchar(255) DEFAULT NULL,
  `payment_plugin_name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `member_uuid` char(20) DEFAULT NULL,
  `orders_uuid` char(20) DEFAULT NULL,
  `del_flag` bit(1) NOT NULL DEFAULT b'0',
  `uuid` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uni_payment_log_sn` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `payment_log` */

/*Table structure for table `payment_method` */

DROP TABLE IF EXISTS `payment_method`;

CREATE TABLE `payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `creator` varchar(25) NOT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `uuid` char(20) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `content` longtext,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `method` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `timeout` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `del_flag` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `payment_method` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
