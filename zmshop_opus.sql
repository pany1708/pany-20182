/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_opus
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_opus` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_opus`;

/*Table structure for table `opus` */

DROP TABLE IF EXISTS `opus`;

CREATE TABLE `opus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `opus_name` varchar(60) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '作品名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '设计灵感',
  `sn` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '作品编码',
  `opus_image` longtext COMMENT '效果图',
  `modle_path` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '模型文件路径',
  `opus_status` int(4) DEFAULT NULL COMMENT '作品状态 0:待发布;1:已发布;2:已驳回;3:下架',
  `is_show` bit(1) DEFAULT NULL COMMENT '是否显示\n            b''1'' 显示\n            b''0'' 屏蔽 ',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标记',
  `uuid` char(20) DEFAULT NULL COMMENT '业务编号',
  `member_uuid` char(20) DEFAULT NULL COMMENT '会员业务主键',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `opus_material_info` varchar(255) DEFAULT NULL COMMENT '作品面料',
  `opus_parts_info` varchar(255) DEFAULT NULL COMMENT '作品部件',
  `opus_accessories_info` varchar(255) DEFAULT NULL COMMENT '作品辅料',
  `member_nick` varchar(255) DEFAULT NULL COMMENT '会员名称',
  `season` varchar(25) DEFAULT NULL COMMENT '季节',
  `class_category_type` varchar(100) DEFAULT NULL COMMENT '所属分类',
  `class_category_name` varchar(100) DEFAULT NULL COMMENT '所属分类名称',
  `standard_price` decimal(21,2) DEFAULT NULL COMMENT '标准价格',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uuid` (`uuid`) USING BTREE,
  UNIQUE KEY `un_sn` (`sn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=812 DEFAULT CHARSET=utf8 COMMENT='作品表';

/*Data for the table `opus` */

/*Table structure for table `opus_accessories` */

DROP TABLE IF EXISTS `opus_accessories`;

CREATE TABLE `opus_accessories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opus_uuid` char(17) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '作品表的业务主键',
  `accessories_uuid` char(17) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '面料表的业务主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='作品与辅料的中间表';

/*Data for the table `opus_accessories` */

/*Table structure for table `opus_material` */

DROP TABLE IF EXISTS `opus_material`;

CREATE TABLE `opus_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opus_uuid` char(17) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '作品表的业务主键',
  `material_uuid` char(17) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '面料表的业务主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='作品与面料的中间表';

/*Data for the table `opus_material` */

/*Table structure for table `opus_part` */

DROP TABLE IF EXISTS `opus_part`;

CREATE TABLE `opus_part` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opus_uuid` char(17) CHARACTER SET utf8mb4 NOT NULL COMMENT '作品表的业务主键',
  `part_sub_uuid` char(17) CHARACTER SET utf8mb4 NOT NULL COMMENT '部件表的业务主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='作品与部件的中间表';

/*Data for the table `opus_part` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
