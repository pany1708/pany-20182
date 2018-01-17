/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_cart
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_cart` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_cart`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `expire` datetime NOT NULL,
  `member_uuid` char(20) NOT NULL COMMENT '会员的业务编号',
  `del_flag` bit(1) NOT NULL COMMENT '是否删除',
  `uuid` char(20) NOT NULL COMMENT '业务主键',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(20) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `member_uuid_idx` (`member_uuid`),
  KEY `uuid_idx` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=8474 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`id`,`create_date`,`modify_date`,`version`,`expire`,`member_uuid`,`del_flag`,`uuid`,`creator`,`modifier`) values (8471,'2017-09-20 14:34:27','2017-09-20 14:34:27',0,'2017-09-27 14:34:27','97071061572518195','\0','97293067846095549','','');

/*Table structure for table `cart_item` */

DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `quantity` int(11) NOT NULL COMMENT '数量',
  `cart_uuid` char(20) DEFAULT NULL COMMENT '购物车表的业务主键',
  `goods_uuid` char(20) NOT NULL COMMENT '商品表的业务主键',
  `del_flag` bit(1) NOT NULL COMMENT '是否删除',
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  PRIMARY KEY (`id`),
  KEY `cart_uuid_idx` (`cart_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=10437 DEFAULT CHARSET=utf8;

/*Data for the table `cart_item` */

insert  into `cart_item`(`id`,`create_date`,`modify_date`,`version`,`creator`,`modifier`,`quantity`,`cart_uuid`,`goods_uuid`,`del_flag`,`uuid`) values (10331,'2017-09-20 14:34:27','2017-10-23 15:04:30',3,'','',7,'97293067846095549','97100764777807878','\0','97293067846095550'),(10436,'2017-11-14 17:45:11','2017-11-14 17:45:11',0,'','',2,'97293067846095549','97100764777808924','\0','97397638320080482');

/*Table structure for table `event_process` */

DROP TABLE IF EXISTS `event_process`;

CREATE TABLE `event_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `event_status` varchar(20) DEFAULT NULL COMMENT '事件状态 待发布(NEW)  已发布(PUBLISHED)',
  `event_type` varchar(20) DEFAULT NULL COMMENT '事件类型 更新购物车(CART)',
  `payload` longtext COMMENT '事件内容 json格式',
  `sharding` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待处理的事件';

/*Data for the table `event_process` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
