/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_message
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_message` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_message`;

/*Table structure for table `message_info` */

DROP TABLE IF EXISTS `message_info`;

CREATE TABLE `message_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `push_time` datetime DEFAULT NULL COMMENT '发布时间',
  `message_platform_uuid` char(20) DEFAULT NULL COMMENT '第三短信平台',
  `members_uuid` char(20) DEFAULT NULL COMMENT '发布人',
  `member_name` varchar(255) DEFAULT NULL COMMENT '发布人',
  `push_target` char(20) DEFAULT NULL COMMENT '发布对象 0 全部  其他的是地区uuid',
  `push_status` bit(1) DEFAULT NULL COMMENT '发布状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='消息管理';

/*Data for the table `message_info` */

/*Table structure for table `message_platform` */

DROP TABLE IF EXISTS `message_platform`;

CREATE TABLE `message_platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `platform_name` varchar(255) DEFAULT NULL,
  `platform_desc` varchar(255) DEFAULT NULL,
  `open_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0启用1禁用',
  `queue_name` varchar(255) DEFAULT NULL COMMENT '队列名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='消息平台';

/*Data for the table `message_platform` */

/*Table structure for table `message_push_record` */

DROP TABLE IF EXISTS `message_push_record`;

CREATE TABLE `message_push_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `platform_uuid` char(20) DEFAULT NULL COMMENT '短信平台uuid',
  `status` bit(1) DEFAULT NULL COMMENT '是否发送成功',
  `content` varchar(255) DEFAULT NULL COMMENT '短信内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_push_record` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
