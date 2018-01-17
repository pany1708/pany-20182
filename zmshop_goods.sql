/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_goods
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_goods` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_goods`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `version` int(11) NOT NULL COMMENT '版本号',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) DEFAULT NULL COMMENT '更新人',
  `del_flag` bit(1) NOT NULL COMMENT '是否删除',
  `uuid` char(20) DEFAULT NULL COMMENT '业务编号',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `sn` varchar(12) DEFAULT NULL COMMENT '商品编号',
  `internal_sn` varchar(50) DEFAULT NULL COMMENT '商品内部款号',
  `goods_type` int(2) DEFAULT NULL COMMENT '商品分类',
  `style_type` int(2) DEFAULT NULL COMMENT '款式分类',
  `standard_price` decimal(21,2) DEFAULT NULL COMMENT '标准价格',
  `added_price` decimal(21,2) DEFAULT NULL COMMENT '固定附加价',
  `desinger` varchar(50) DEFAULT NULL COMMENT '设计师名称',
  `desinger_id` varchar(50) DEFAULT NULL COMMENT '设计师ID',
  `status` int(1) DEFAULT '0' COMMENT '上架状态：0：未上架，1：已上架，2：延迟上架 ',
  `keyword1` varchar(20) DEFAULT NULL COMMENT '热搜关键词1',
  `keyword2` varchar(20) DEFAULT NULL COMMENT '热搜关键词2',
  `keyword3` varchar(20) DEFAULT NULL COMMENT '热搜关键词3',
  `sizes` longtext COMMENT '码数/关键部位参数json方式存储',
  `goods_attribute` longtext COMMENT '商品属性json方式存储',
  `goods_image` longtext COMMENT '商品图片json方式存储',
  `part_info` longtext COMMENT '商品的部件json方式存储',
  `materiel_info` longtext COMMENT '商品的物料面料json方式存储',
  `accessories_info` longtext COMMENT '商品的物料辅料json方式存储',
  `goods_details` longtext COMMENT '商品详情',
  `cover` varchar(500) DEFAULT NULL COMMENT '封面图',
  `clicks` bigint(20) DEFAULT '0' COMMENT '点击量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_Uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`create_date`,`modify_date`,`version`,`creator`,`modifier`,`del_flag`,`uuid`,`goods_name`,`sn`,`internal_sn`,`goods_type`,`style_type`,`standard_price`,`added_price`,`desinger`,`desinger_id`,`status`,`keyword1`,`keyword2`,`keyword3`,`sizes`,`goods_attribute`,`goods_image`,`part_info`,`materiel_info`,`accessories_info`,`goods_details`,`cover`,`clicks`) values (4,'2018-01-17 15:54:52','2018-01-17 15:54:52',1,'97071061572518195',NULL,'\0','10834048042823319598',NULL,'SP2018011720',NULL,2,1,NULL,NULL,'ALong1','97071061572518195',0,NULL,NULL,NULL,NULL,NULL,NULL,'[{\"typeName\":\"领子\",\"detail\":{\"image\":\"http://192.168.1.217/group1/M00/00/16/wKgB21kmLouAZ7PUAAOKXrrgzm0343.png\",\"name\":\"领1\"}}]','[{\"groupName\":\"面料A\",\"groupUuid\":\"213sfd\",\"details\":[{\"price\":0,\"name\":\"面料测试1\",\"uuid\":\"456123798\",\"colors\":[\"242,232,218\"]}]}]','[{\"groupName\":\"袖子纽扣\",\"groupUuid\":\"123456789\",\"details\":[{\"price\":0,\"name\":\"辅料测试1\",\"uuid\":\"string\",\"colors\":[\"244,245,240\"]}]}]',NULL,'http://192.168.1.217/group1/M00/00/16/wKgB21kmLouAZ7PUAAOKXrrgzm0343.png',0);

/*Table structure for table `goods_fitting` */

DROP TABLE IF EXISTS `goods_fitting`;

CREATE TABLE `goods_fitting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `members_uuid` char(20) DEFAULT NULL,
  `figure_uuid` char(20) DEFAULT NULL,
  `goods_uuid` char(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `modifier` varchar(20) DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='试穿记录';

/*Data for the table `goods_fitting` */

insert  into `goods_fitting`(`id`,`uuid`,`members_uuid`,`figure_uuid`,`goods_uuid`,`create_date`,`modify_date`,`version`,`creator`,`modifier`,`del_flag`) values (8,'10834048042823319586','97071061572518195','97189110090962291','97100764777807878','2018-01-11 17:18:00','2018-01-12 16:19:05',0,'97071061572518195','97071061572518195','\0');

/*Table structure for table `goods_officially` */

DROP TABLE IF EXISTS `goods_officially`;

CREATE TABLE `goods_officially` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `creator` varchar(25) NOT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `uuid` char(20) DEFAULT NULL,
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `goods_feature` varchar(255) DEFAULT NULL COMMENT '商品卖点',
  `standard_price` decimal(21,6) DEFAULT NULL COMMENT '标准价格',
  `accessories_info` longtext,
  `opus_sn` varchar(20) DEFAULT NULL COMMENT '关联作品编号',
  `desinger` varchar(50) DEFAULT NULL COMMENT '设计师名称',
  `return_point` int(7) DEFAULT NULL COMMENT '返还积分',
  `put_on_method` int(11) DEFAULT NULL COMMENT '上架方式 0:及时 1:定时',
  `put_on_time` datetime DEFAULT NULL COMMENT '定时上架时间',
  `status` int(4) DEFAULT NULL COMMENT '上架状态：0：未上架，1：已上架，2：延迟上架',
  `goods_category_uuid` varchar(50) DEFAULT NULL COMMENT '商品品类uuid',
  `goods_style_uuid` varchar(50) DEFAULT NULL COMMENT '商品风格uuid',
  `goods_season_uuid` varchar(50) DEFAULT NULL COMMENT '商品季节uuid',
  `goods_tags` longtext COMMENT '商品标签json方式存储',
  `cover` varchar(500) DEFAULT NULL,
  `goods_image` longtext COMMENT '商品图片json方式存储',
  `part_info` longtext COMMENT '商品的部件json方式存储',
  `materiel_info` longtext COMMENT '商品的物料json方式存储',
  `goods_details` longtext COMMENT '商品详情',
  `opus_uuid` char(20) NOT NULL COMMENT '作品表的业务编号',
  `del_flag` bit(1) NOT NULL COMMENT '是否删除',
  `sn` varchar(255) DEFAULT NULL,
  `goods_uuid` char(20) DEFAULT NULL,
  `clicks` bigint(20) DEFAULT NULL,
  `goods_materiel_uuid` char(20) DEFAULT NULL,
  `age_segment_uuid` char(20) DEFAULT NULL,
  `attribute` varchar(255) DEFAULT NULL,
  `goods_category_type` int(11) DEFAULT NULL COMMENT '商品类型 1 上衣 2 裤装 3 裙装 4 套装',
  `file_url` varchar(255) DEFAULT NULL COMMENT '模型文件地址',
  PRIMARY KEY (`id`),
  KEY `uuid_index` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `goods_officially` */

/*Table structure for table `goods_parameter` */

DROP TABLE IF EXISTS `goods_parameter`;

CREATE TABLE `goods_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_uuid` char(20) DEFAULT NULL COMMENT '商品业务主键',
  `class_uuid` char(20) DEFAULT NULL COMMENT '商品类目业务主键',
  `paramenter_uuid` char(20) DEFAULT NULL COMMENT '参数值业务主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商品参数表';

/*Data for the table `goods_parameter` */

/*Table structure for table `report_goods_data` */

DROP TABLE IF EXISTS `report_goods_data`;

CREATE TABLE `report_goods_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键 ',
  `creator` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '商品数量',
  `data_type` int(11) DEFAULT NULL COMMENT '数据类型：0 上架商品总数量 1上衣商品总数量 2裤装商品总数量 3群装商品总数量 4套装商品总数量',
  `time_type` int(11) DEFAULT NULL COMMENT '时间类型：1 昨天 2过去一周 3 过去十五天 4 过去一个月',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2204 DEFAULT CHARSET=utf8;

/*Data for the table `report_goods_data` */

insert  into `report_goods_data`(`id`,`uuid`,`creator`,`create_date`,`del_flag`,`version`,`num`,`data_type`,`time_type`) values (2156,'10689932845268336841',NULL,'2018-01-16 03:00:00','\0',NULL,0,1,4),(2158,'10689932845268336842',NULL,'2018-01-16 03:00:00','\0',NULL,0,4,4),(2160,'10689932845268336843',NULL,'2018-01-16 03:00:00','\0',NULL,0,3,1),(2162,'10689932845268336844',NULL,'2018-01-16 03:00:00','\0',NULL,0,2,2),(2164,'10689932845268336845',NULL,'2018-01-16 03:00:00','\0',NULL,0,4,1),(2166,'10689932845268336846',NULL,'2018-01-16 03:00:00','\0',NULL,0,4,2),(2168,'10689932845268336847',NULL,'2018-01-16 03:00:00','\0',NULL,0,1,1),(2172,'10689932845268336849',NULL,'2018-01-16 03:00:00','\0',NULL,0,3,4),(2178,'10689932845268336852',NULL,'2018-01-16 03:00:00','\0',NULL,0,3,3),(2180,'10689932845268336859',NULL,'2018-01-16 03:00:00','\0',NULL,0,3,2),(2182,'10689932845268336860',NULL,'2018-01-16 03:00:00','\0',NULL,0,2,4),(2184,'10689932845268336861',NULL,'2018-01-16 03:00:00','\0',NULL,0,2,3),(2188,'10689932845268336863',NULL,'2018-01-16 03:00:00','\0',NULL,0,1,2),(2190,'10689932845268336864',NULL,'2018-01-16 03:00:00','\0',NULL,0,4,3),(2192,'10689932845268336865',NULL,'2018-01-16 03:00:00','\0',NULL,0,1,3),(2194,'10689932845268336866',NULL,'2018-01-16 03:00:00','\0',NULL,0,2,1),(2196,'10689932845268336887',NULL,'2018-01-17 03:00:00','\0',NULL,0,0,1),(2198,'10689932845268336888',NULL,'2018-01-17 03:00:00','\0',NULL,0,0,2),(2200,'10689932845268336889',NULL,'2018-01-17 03:00:00','\0',NULL,0,0,3),(2202,'10689932845268336890',NULL,'2018-01-17 03:00:00','\0',NULL,0,0,4);

/*Table structure for table `report_onsale_goods` */

DROP TABLE IF EXISTS `report_onsale_goods`;

CREATE TABLE `report_onsale_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键 ',
  `creator` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '商品数量',
  `priceRange` varchar(25) DEFAULT NULL COMMENT '价格区间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=674 DEFAULT CHARSET=utf8;

/*Data for the table `report_onsale_goods` */

insert  into `report_onsale_goods`(`id`,`uuid`,`creator`,`create_date`,`del_flag`,`version`,`num`,`priceRange`) values (662,'10689932845268336891',NULL,'2018-01-17 03:00:26','\0',1,0,'0~1000'),(664,'10689932845268336892',NULL,'2018-01-17 03:00:26','\0',1,0,'1001~3000'),(666,'10689932845268336893',NULL,'2018-01-17 03:00:26','\0',1,0,'3001~5000'),(668,'10689932845268336894',NULL,'2018-01-17 03:00:26','\0',1,0,'5001~8000'),(670,'10689932845268336895',NULL,'2018-01-17 03:00:26','\0',1,0,'8001~10000'),(672,'10689932845268336896',NULL,'2018-01-17 03:00:26','\0',1,0,'10000以上');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
