/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_user`;

/*Table structure for table `attention` */

DROP TABLE IF EXISTS `attention`;

CREATE TABLE `attention` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_uuid` char(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '会员业务主键',
  `attention_uuid` char(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '关注对象（会员）的业务主键',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `creator` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `modifier` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `version` int(11) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `queryIndex` (`member_uuid`,`attention_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='我的关注';

/*Data for the table `attention` */

/*Table structure for table `figure` */

DROP TABLE IF EXISTS `figure`;

CREATE TABLE `figure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_uuid` char(20) DEFAULT NULL COMMENT '会员业务主键',
  `create_date` datetime NOT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `creator` varchar(25) NOT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `uuid` char(20) DEFAULT NULL,
  `family_relation` varchar(100) DEFAULT NULL COMMENT '与会员的关系',
  `del_flag` bit(1) DEFAULT NULL COMMENT '是否删除',
  `model_path` longtext COMMENT '模型地址',
  `figure_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `model_name` varchar(255) DEFAULT NULL COMMENT '模型名称',
  `model_image` varchar(255) DEFAULT NULL COMMENT '图片文件',
  `is_default` bit(1) DEFAULT b'0' COMMENT '是否默认',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐，数据加密秘钥',
  `status` int(1) DEFAULT NULL COMMENT '状态(0：扫描中，1：构建中，2：已构建)',
  PRIMARY KEY (`id`),
  KEY `idx_memberUuid` (`member_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='体型数据表';

/*Data for the table `figure` */

insert  into `figure`(`id`,`member_uuid`,`create_date`,`modify_date`,`version`,`creator`,`modifier`,`uuid`,`family_relation`,`del_flag`,`model_path`,`figure_name`,`model_name`,`model_image`,`is_default`,`salt`,`status`) values (1,'97071061572518195','2017-09-05 15:38:22','2017-09-05 15:38:26',0,'modifier','modifier','97189110090962291','朋友','\0','{\"bd\":\"{\\\"fileUrl\\\":\\\"http://192.168.1.217/group1/M00/00/36/wKgB21nw48-AZF1RAADDQDI744c8847.bd\\\",\\\"encryptKey\\\":\\\"E4R63BA1\\\"}\",\"obj\":\"{\\\"fileUrl\\\":\\\"http://192.168.1.217/group1/M00/00/36/wKgB21nw5AaAE3jKABK2gDrXWRc959.obj\\\",\\\"encryptKey\\\":\\\"V5j7m07L\\\"}\",\"dm\":\"{\\\"encryptKey\\\":\\\"F4X2GVx3\\\",\\\"fileUrl\\\":\\\"http://192.168.1.217/group1/M00/00/41/wKgB21op93WAcY1hABkLUNTOa3g1331.dm\\\"}\"}','陈老板a','陈老板a','http://192.168.1.217/group1/M00/00/36/wKgB21nw5jmAYA4IABfgJjpDwyM005.png','','',2),(2,'97071061572518195','2017-09-12 10:51:31','2017-09-12 10:51:31',0,'creator','modifier','97189110090962292','朋友','\0','{\"bd\":\"{\\\"fileUrl\\\":\\\"http://192.168.1.217/group1/M00/00/36/wKgB21nw48-AZF1RAADDQDI744c8847.bd\\\",\\\"encryptKey\\\":\\\"E4R63BA1\\\"}\",\"obj\":\"{\\\"fileUrl\\\":\\\"http://192.168.1.217/group1/M00/00/36/wKgB21nw5AaAE3jKABK2gDrXWRc959.obj\\\",\\\"encryptKey\\\":\\\"V5j7m07L\\\"}\",\"dm\":\"{\\\"encryptKey\\\":\\\"F4X2GVx3\\\",\\\"fileUrl\\\":\\\"http://192.168.1.217/group1/M00/00/41/wKgB21op93WAcY1hABkLUNTOa3g1331.dm\\\"}\"}','陈老板b','陈老板b','http://192.168.1.217/group1/M00/00/36/wKgB21nw5jmAYA4IABfgJjpDwyM005.png','','',2);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(21) NOT NULL COMMENT '业务主键',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '是否删除(0:否，1：是)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '更新时间',
  `creator` varchar(25) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `version` int(11) DEFAULT '1' COMMENT '版本',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '密码',
  `payment_password` varchar(50) DEFAULT NULL COMMENT '支付密码',
  `paymen_salt` varchar(50) DEFAULT NULL COMMENT '支付密码加密钥',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender` int(4) DEFAULT '0' COMMENT '性别',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `area_ids` char(12) DEFAULT NULL COMMENT '地址主键拼接',
  `area_name` varchar(255) DEFAULT NULL COMMENT '地区名称',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `locked_date` datetime DEFAULT NULL COMMENT '锁定时长',
  `login_date` datetime DEFAULT NULL COMMENT '登录日期',
  `login_failure_count` int(11) DEFAULT '0' COMMENT '登录失败次数',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录IP',
  `login_plugin_id` varchar(255) DEFAULT NULL COMMENT '第三方登录插件',
  `integral` int(11) DEFAULT '0' COMMENT '积分',
  `register_ip` varchar(255) DEFAULT NULL COMMENT '注册IP',
  `is_enabled` bit(1) DEFAULT b'0' COMMENT '是否禁用',
  `is_locked` bit(1) DEFAULT b'0' COMMENT '是否锁定',
  `is_desinger` bit(1) DEFAULT b'0' COMMENT '是否为设计师',
  `token` varchar(255) DEFAULT NULL COMMENT '令牌',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `head_image` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `bar_codes` varchar(255) DEFAULT NULL COMMENT '二维码',
  `constellation` varchar(4) DEFAULT NULL COMMENT '星座',
  `occupation` varchar(25) DEFAULT NULL COMMENT '职业',
  `style` varchar(50) DEFAULT NULL COMMENT '着装风格',
  `skin_color` varchar(255) DEFAULT NULL COMMENT '肤色',
  `face` varchar(255) DEFAULT NULL COMMENT '脸型',
  `signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `job` varchar(255) DEFAULT NULL COMMENT '工作',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uuid` (`uuid`),
  KEY `uni_member_username` (`user_name`),
  KEY `index_member_phone` (`phone`) USING BTREE,
  KEY `idx_age` (`birth`)
) ENGINE=InnoDB AUTO_INCREMENT=170008 DEFAULT CHARSET=utf8 COMMENT='会员表';

/*Data for the table `member` */

insert  into `member`(`id`,`uuid`,`del_flag`,`create_date`,`modify_date`,`creator`,`modifier`,`version`,`user_name`,`pass_word`,`payment_password`,`paymen_salt`,`salt`,`nick_name`,`gender`,`address`,`area_ids`,`area_name`,`birth`,`email`,`phone`,`zip_code`,`locked_date`,`login_date`,`login_failure_count`,`login_ip`,`login_plugin_id`,`integral`,`register_ip`,`is_enabled`,`is_locked`,`is_desinger`,`token`,`last_login_date`,`head_image`,`bar_codes`,`constellation`,`occupation`,`style`,`skin_color`,`face`,`signature`,`job`) values (4,'97071061572518195','\0','2017-04-18 15:24:07','2017-12-15 14:47:32','system','97137901346756187',NULL,'ALong1','38d86cd0ad9f1f724d76de3970707c90','5b83a25b59d7551b04ae6e4a22acbd89','1501127061198','1501127032766','小呀嘛小二郎呀',NULL,'23号','3','北京市西城区','1999-01-29 08:00:00','szhe@email.com','18682358815','','2017-12-13 17:09:15','2018-01-10 14:28:25',0,'','',1000,'string','\0','\0','\0','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlxdWVfbmFtZSI6IjE4NjgyMzU4ODE1IiwiaXNzIjoicmVzdGFwaXVzZXIiLCJhdWQiOiIwOThmNmJjZDQ2MjFkMzczY2FkZTRlODMyNjI3YjRmNiIsImV4cCI6MTUxNjc4NDc2OSwibmJmIjoxNTE2MDY0NzY5fQ.rIR6hm5Qs-PYn_TSsSljZ0SoyYEz6J58yT9zuTO-SVc','2018-01-17 16:47:57','http://192.168.1.217/group1/M00/00/16/wKgB21kREwaAas_zAAWj7KzQNsY721.png','http://192.168.1.217/group1/M00/00/15/wKgB21jztL-AITUGAAvqH_kipG8997.jpg','','自由职业','[\"OL\",\"朋克\",\"民族\",\"甜美\"]','小麦色','方形脸','不以物喜，不以己悲','');

/*Table structure for table `member_bank_card` */

DROP TABLE IF EXISTS `member_bank_card`;

CREATE TABLE `member_bank_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `members_uuid` char(20) DEFAULT NULL COMMENT '会员uuid',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `card_no` varchar(25) DEFAULT NULL COMMENT '卡号',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `bank_code` varchar(25) DEFAULT NULL COMMENT '银行代码',
  `cardholder` varchar(255) DEFAULT NULL COMMENT '持卡人',
  `identity_card` varchar(20) DEFAULT NULL COMMENT '身份证',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bankcard_uuid_unique` (`uuid`) USING BTREE,
  KEY `bankcard_members_uuid_index` (`members_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='会员绑定银行卡';

/*Data for the table `member_bank_card` */

/*Table structure for table `member_favorite` */

DROP TABLE IF EXISTS `member_favorite`;

CREATE TABLE `member_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` char(20) NOT NULL COMMENT '业务主键',
  `favorite_members_uuid` char(20) NOT NULL COMMENT '会员的业务主键',
  `favorite_goods_uuid` char(20) NOT NULL COMMENT '商品的业务主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(20) DEFAULT NULL COMMENT '更新人',
  `del_flag` bit(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `mf_queryIndex` (`favorite_members_uuid`,`favorite_goods_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91188 DEFAULT CHARSET=utf8;

/*Data for the table `member_favorite` */

/*Table structure for table `member_footmark` */

DROP TABLE IF EXISTS `member_footmark`;

CREATE TABLE `member_footmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` char(20) NOT NULL COMMENT '业务主键',
  `member_uuid` char(20) DEFAULT NULL COMMENT '会员业务主键',
  `product_uuid` char(20) DEFAULT NULL COMMENT '商品业务主建',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `modifier` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uuid` (`uuid`) USING BTREE,
  KEY `queryIndex` (`member_uuid`,`product_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='我的足迹';

/*Data for the table `member_footmark` */

/*Table structure for table `receiver` */

DROP TABLE IF EXISTS `receiver`;

CREATE TABLE `receiver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `area_name` varchar(255) DEFAULT NULL COMMENT '地区',
  `consignee` varchar(255) DEFAULT NULL COMMENT '联系人',
  `is_default` bit(1) DEFAULT b'0' COMMENT '是否默认',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `area_id` int(11) DEFAULT NULL COMMENT '地区主键',
  `member_uuid` char(20) DEFAULT NULL COMMENT '会员的业务编号',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '是否删除(0 否：1是)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uuid` (`uuid`) USING BTREE,
  KEY `idx_memberUuid` (`member_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=184588 DEFAULT CHARSET=utf8 COMMENT='会员收货地址';

/*Data for the table `receiver` */

/*Table structure for table `report_age_group_buy` */

DROP TABLE IF EXISTS `report_age_group_buy`;

CREATE TABLE `report_age_group_buy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键 ',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1' COMMENT '状态：0隐藏1显示',
  `money` decimal(10,0) DEFAULT NULL COMMENT '总金额',
  `age_group` varchar(25) DEFAULT NULL COMMENT '年龄段',
  PRIMARY KEY (`id`),
  KEY `Index_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=932 DEFAULT CHARSET=utf8;

/*Data for the table `report_age_group_buy` */

insert  into `report_age_group_buy`(`id`,`uuid`,`creator`,`modifier`,`create_date`,`modify_date`,`del_flag`,`version`,`status`,`money`,`age_group`) values (918,'10689932845268336869',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'','0','小于17'),(920,'10689932845268336870',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'','0','18-23'),(922,'10689932845268336871',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'','0','24-29'),(924,'10689932845268336872',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'','0','30-39'),(926,'10689932845268336873',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'','0','40-50'),(928,'10689932845268336874',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'','0','51-60'),(930,'10689932845268336875',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'','0','大于60');

/*Table structure for table `report_user_data` */

DROP TABLE IF EXISTS `report_user_data`;

CREATE TABLE `report_user_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键 ',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1' COMMENT '状态：0隐藏1显示',
  `number` int(11) DEFAULT NULL COMMENT '统计数量',
  `data_type` int(11) DEFAULT NULL COMMENT '数据类型：0:全站日均PV 1:全站日均UV 2;全站注册用户 3全站登录用户 4全站试穿用户 5全站下单用户 6全站购买用户',
  `time_type` int(11) DEFAULT NULL COMMENT '时间类型：1 昨天 2过去一周 3 过去十五天 4 过去一个月',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1632 DEFAULT CHARSET=utf8;

/*Data for the table `report_user_data` */

insert  into `report_user_data`(`id`,`uuid`,`creator`,`modifier`,`create_date`,`modify_date`,`del_flag`,`version`,`status`,`number`,`data_type`,`time_type`) values (1608,'10689932845268336867',NULL,NULL,'2018-01-17 02:00:00',NULL,'\0',1,'',0,6,3),(1610,'10689932845268336868',NULL,NULL,'2018-01-17 02:00:02',NULL,'\0',1,'',0,6,4),(1612,'10689932845268336877',NULL,NULL,'2018-01-17 02:00:02',NULL,'\0',1,'',0,2,1),(1614,'10689932845268336878',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,2,2),(1616,'10689932845268336879',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,2,3),(1618,'10689932845268336880',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,2,4),(1620,'10689932845268336881',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,5,1),(1622,'10689932845268336882',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,5,2),(1624,'10689932845268336883',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,5,3),(1626,'10689932845268336884',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,5,4),(1628,'10689932845268336885',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,6,1),(1630,'10689932845268336886',NULL,NULL,'2018-01-17 02:00:03',NULL,'\0',1,'',0,6,2);

/*Table structure for table `report_user_register` */

DROP TABLE IF EXISTS `report_user_register`;

CREATE TABLE `report_user_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键 ',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1' COMMENT '状态：0隐藏1显示',
  `number` int(11) DEFAULT NULL COMMENT '注册数量',
  `register_date` date DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `report_user_register` */

/*Table structure for table `show_videos` */

DROP TABLE IF EXISTS `show_videos`;

CREATE TABLE `show_videos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL COMMENT 'uuid',
  `member_uuid` char(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `video_url` char(10) DEFAULT NULL COMMENT '走秀视频文件地址',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='走秀视频';

/*Data for the table `show_videos` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
