/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_review
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_review` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_review`;

/*Table structure for table `buyers_show` */

DROP TABLE IF EXISTS `buyers_show`;

CREATE TABLE `buyers_show` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键 ',
  `order_sn` varchar(50) DEFAULT NULL COMMENT '订单号',
  `order_uuid` char(20) DEFAULT NULL COMMENT '订单uuid',
  `goods_uuid` char(20) DEFAULT NULL COMMENT '商品uuid',
  `member_name` varchar(255) DEFAULT NULL COMMENT '会员',
  `member_uuid` char(20) DEFAULT NULL COMMENT '会员uuid',
  `content` text COMMENT '评论内容',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `anonymous_flag` bit(1) DEFAULT NULL COMMENT '是否匿名评论',
  `ip` char(15) DEFAULT NULL COMMENT 'ip地址',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `status` bit(1) DEFAULT b'1' COMMENT '状态：0隐藏1显示',
  PRIMARY KEY (`id`),
  KEY `index_googsUuid` (`goods_uuid`) USING BTREE,
  KEY `index_buyerUuid` (`member_uuid`) USING BTREE,
  KEY `idx_status` (`status`),
  KEY `idx_del_flag` (`del_flag`),
  KEY `idx_create_date` (`create_date`),
  KEY `idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=400923 DEFAULT CHARSET=utf8;

/*Data for the table `buyers_show` */

/*Table structure for table `buyers_show_img` */

DROP TABLE IF EXISTS `buyers_show_img`;

CREATE TABLE `buyers_show_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `buyers_uuid` char(20) DEFAULT NULL COMMENT '买家秀UUID',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_buyers_uuid` (`buyers_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=931334 DEFAULT CHARSET=utf8 COMMENT='买家秀图片表';

/*Data for the table `buyers_show_img` */

/*Table structure for table `likes` */

DROP TABLE IF EXISTS `likes`;

CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `modeifier` varchar(255) DEFAULT NULL COMMENT '修改者',
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标示',
  `moment_uuid` char(20) DEFAULT NULL COMMENT '动态的业务主键',
  `member_uuid` char(20) DEFAULT NULL COMMENT '会员业务主键',
  `type` int(1) DEFAULT NULL COMMENT '点赞类型(0:动态 1:评论,2:买家秀)',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `member_uuid_index` (`member_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

/*Data for the table `likes` */

insert  into `likes`(`id`,`create_date`,`modify_date`,`version`,`creator`,`modeifier`,`uuid`,`del_flag`,`moment_uuid`,`member_uuid`,`type`,`status`) values (254,'2018-01-10 14:56:39','2018-01-10 14:56:39',1,'Creator','Modeifier','10689932845268336644','\0','97203244123555165','97071061572518195',2,NULL),(260,'2018-01-10 15:08:13','2018-01-10 15:08:13',1,'Creator','Modeifier','10689932845268336647','\0','97203244123555166','97071061572518195',2,NULL),(274,'2018-01-10 15:52:30','2018-01-10 15:52:30',1,'Creator','Modeifier','10689932845268336654','\0','97203244123555170','97071061572518195',2,NULL),(276,'2018-01-10 16:01:57','2018-01-10 16:01:57',1,'Creator','Modeifier','10689932845268336655','\0','97203244123555159','97071061572518195',2,NULL);

/*Table structure for table `moment_comment` */

DROP TABLE IF EXISTS `moment_comment`;

CREATE TABLE `moment_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `modeifier` varchar(255) DEFAULT NULL COMMENT '修改者',
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标示',
  `parent_uuid` char(20) DEFAULT '0' COMMENT '父类评论业务主键',
  `parent_nick` varchar(255) DEFAULT NULL COMMENT '被评论者昵称',
  `moment_uuid` char(20) DEFAULT NULL COMMENT '动态的业务主键',
  `member_head` varchar(255) DEFAULT NULL COMMENT '评论者头像',
  `member_nick` varchar(255) DEFAULT NULL COMMENT '评论者昵称',
  `member_uuid` char(20) DEFAULT NULL COMMENT '评论人业务主键',
  `like_amount` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `context` varchar(255) DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`),
  KEY `moment_uuid_index` (`moment_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COMMENT='动态评论表';

/*Data for the table `moment_comment` */

/*Table structure for table `moments` */

DROP TABLE IF EXISTS `moments`;

CREATE TABLE `moments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(255) DEFAULT NULL COMMENT '修改者',
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标示',
  `image` longtext COMMENT '动态图片',
  `context` varchar(255) DEFAULT NULL COMMENT '动态的内容',
  `member_uuid` char(20) NOT NULL COMMENT '会员的业务主键',
  `comment_amount` bigint(20) DEFAULT NULL COMMENT '评论数量',
  `like_amount` bigint(20) DEFAULT NULL COMMENT '点赞点赞数量',
  `member_nick` varchar(255) DEFAULT NULL COMMENT '会员昵称 ',
  `member_head` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `type` int(1) DEFAULT NULL COMMENT '动态类型 0:图片 1:视频',
  `video` varchar(255) DEFAULT NULL COMMENT '视频',
  `review` int(1) DEFAULT '0' COMMENT '审核状态 0:未审核 1:不通过 2:已通过',
  `reason` varchar(255) DEFAULT NULL COMMENT '原因',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_index` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COMMENT='动态表';

/*Data for the table `moments` */

/*Table structure for table `reply_comment` */

DROP TABLE IF EXISTS `reply_comment`;

CREATE TABLE `reply_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `buyers_uuid` char(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `reply_comment` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
