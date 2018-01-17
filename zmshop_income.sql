/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_income
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_income` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_income`;

/*Table structure for table `member_income` */

DROP TABLE IF EXISTS `member_income`;

CREATE TABLE `member_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `members_uuid` char(20) DEFAULT NULL COMMENT '会员uuid',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `amount` decimal(10,0) DEFAULT '0' COMMENT '总收益',
  `withdraws` decimal(10,0) DEFAULT '0' COMMENT '总提现',
  `balance` decimal(10,0) DEFAULT '0' COMMENT '可用余额',
  PRIMARY KEY (`id`),
  KEY `idx_memberUuid` (`members_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='我的收益';

/*Data for the table `member_income` */

/*Table structure for table `member_income_detail` */

DROP TABLE IF EXISTS `member_income_detail`;

CREATE TABLE `member_income_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `members_uuid` char(20) DEFAULT NULL COMMENT '会员uuid',
  `goods_uuid` char(20) DEFAULT NULL COMMENT '商品UUID',
  `order_sn` varchar(50) DEFAULT NULL COMMENT '订单号',
  `money` decimal(21,6) DEFAULT NULL COMMENT '金额',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_ordersn` (`order_sn`) USING BTREE,
  KEY `idex_m_g` (`members_uuid`,`goods_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='收益记录';

/*Data for the table `member_income_detail` */

/*Table structure for table `member_withdraws_cash` */

DROP TABLE IF EXISTS `member_withdraws_cash`;

CREATE TABLE `member_withdraws_cash` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uuid` char(20) CHARACTER SET utf8 DEFAULT NULL,
  `members_uuid` char(20) CHARACTER SET utf8 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL COMMENT '提现时间',
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `modifier` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0:申请提现 1:提现中 2:提现成功',
  `bank_card_uuid` char(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '绑定的银行卡uuid',
  `money` decimal(21,6) DEFAULT NULL COMMENT '金额',
  `card_no` varchar(25) CHARACTER SET utf8 DEFAULT NULL COMMENT '银行卡号',
  `order_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '银行或支付宝,微信 返回的流水号',
  PRIMARY KEY (`id`),
  KEY `idx_memberUuid` (`members_uuid`) USING BTREE,
  KEY `idx_bankcarduuid` (`bank_card_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=sjis COMMENT='提现记录';

/*Data for the table `member_withdraws_cash` */

/*Table structure for table `withdraws_cash_log` */

DROP TABLE IF EXISTS `withdraws_cash_log`;

CREATE TABLE `withdraws_cash_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `members_uuid` char(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '详细内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `withdraws_uuid` char(20) DEFAULT NULL COMMENT '提现记录UUID',
  `card_no` varchar(25) DEFAULT NULL COMMENT '银行卡号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='提现记录日志表';

/*Data for the table `withdraws_cash_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
