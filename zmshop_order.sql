/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_order
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_order` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_order`;

/*Table structure for table `after_sale_schedule` */

DROP TABLE IF EXISTS `after_sale_schedule`;

CREATE TABLE `after_sale_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `goods_uuid` varchar(50) DEFAULT NULL COMMENT '商品ID',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0:发起售后 1:-审核通过 2:厂家确认收货,待生产 3:生产中 4:已包装发货，等待收货 5:已收货 6:已评价',
  `memo` longtext,
  `sale_service_uuid` char(20) DEFAULT NULL COMMENT '售后UUID',
  `order_sn` varchar(50) DEFAULT NULL COMMENT '订单号',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='售后进度';

/*Data for the table `after_sale_schedule` */

/*Table structure for table `after_sale_service` */

DROP TABLE IF EXISTS `after_sale_service`;

CREATE TABLE `after_sale_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `order_sn` varchar(50) DEFAULT NULL COMMENT '订单号',
  `goods_uuid` char(20) DEFAULT NULL COMMENT '商品uuid',
  `member_uuid` char(20) DEFAULT NULL COMMENT '会员uuid',
  `member_name` varchar(255) DEFAULT NULL COMMENT '会员',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0:发起售后 1:-审核通过 2:厂家确认收货,待生产 3:生产中 4:已包装发货，等待收货 5:已收货 6:已评价',
  `apply_service_type` int(1) DEFAULT NULL COMMENT '申请服务 0:换货 1:仅退货 2:退货退款',
  `apply_service_name` varchar(255) DEFAULT NULL COMMENT '申请服务名称',
  `exchange_reason` varchar(255) DEFAULT NULL COMMENT '换货原因',
  `memo` longtext COMMENT '换货说明',
  `img` longtext COMMENT '图片',
  `version` int(11) DEFAULT NULL,
  `shipping_number` varchar(50) DEFAULT NULL COMMENT '运单号',
  `auditing_flag` bit(1) DEFAULT b'0' COMMENT '是否拒绝售后',
  `reject_reason` longtext COMMENT '审核不通过原因',
  `refunds_addr_uuid` char(20) DEFAULT NULL COMMENT '售后地址',
  PRIMARY KEY (`id`),
  KEY `index_ordersn` (`order_sn`) USING BTREE,
  KEY `member_uuid_index` (`member_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='订单售后表';

/*Data for the table `after_sale_service` */

/*Table structure for table `event_publish` */

DROP TABLE IF EXISTS `event_publish`;

CREATE TABLE `event_publish` (
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
) ENGINE=InnoDB AUTO_INCREMENT=67999 DEFAULT CHARSET=utf8 COMMENT='事件发布表';

/*Data for the table `event_publish` */

/*Table structure for table `invoice_info` */

DROP TABLE IF EXISTS `invoice_info`;

CREATE TABLE `invoice_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `invoice_type` int(1) DEFAULT NULL COMMENT '发票类型 0: 个人 1公司',
  `invoice_title` varchar(255) DEFAULT NULL COMMENT '发票title',
  `order_sn` varchar(25) DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '发票状态(0申请中 1完成)',
  `invoice_no` varchar(100) DEFAULT NULL COMMENT '发票编号',
  `billing_time` datetime DEFAULT NULL COMMENT '开票时间',
  PRIMARY KEY (`id`),
  KEY `invoice_no_index` (`invoice_no`) USING BTREE,
  KEY `order_sn_index` (`order_sn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `invoice_info` */

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '更改时间',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `is_delivery` bit(1) NOT NULL COMMENT '是否需要物流',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `price` decimal(21,6) NOT NULL COMMENT '商品价格',
  `quantity` int(11) NOT NULL COMMENT '商品数量',
  `sn` varchar(255) NOT NULL COMMENT '子订单编号',
  `specifications` longtext COMMENT '商品定制信息',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '商品定制信息',
  `type` int(11) NOT NULL COMMENT '订单类型 0:普通订单 1:积分兑换订单',
  `order_uuid` char(20) NOT NULL COMMENT '订单业务主键',
  `product_uuid` char(20) DEFAULT NULL COMMENT '商品业务主键',
  `del_flag` bit(1) NOT NULL COMMENT '是否删除',
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `creator` varchar(25) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `figure_uuid` char(20) DEFAULT NULL COMMENT '体型表的业务主键',
  `payment_date` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_date` datetime DEFAULT NULL COMMENT '发货时间',
  `complete_date` datetime DEFAULT NULL COMMENT '完成日期',
  `expire` datetime DEFAULT NULL COMMENT '过期时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '附言',
  `offset_amount` decimal(10,0) DEFAULT NULL COMMENT '调整金额',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `refund_amount` decimal(10,0) DEFAULT NULL COMMENT '退款金额',
  `returned_quantity` int(11) DEFAULT NULL COMMENT '已退货数量',
  `shipped_quantity` int(11) DEFAULT NULL COMMENT '已发货数量',
  `shipping_method_name` varchar(50) DEFAULT NULL COMMENT '配送方式名称',
  `shipping_number` varchar(50) DEFAULT NULL COMMENT '运单号',
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态 0:代付款 1:生产中 2:待发货 3:待签收 4:待评价 5:已取消 6:待退单 7:已完成',
  `member_name` varchar(255) DEFAULT NULL COMMENT '下单用户',
  `member_uuid` char(20) DEFAULT NULL,
  `payment_method_uuid` char(20) DEFAULT NULL COMMENT '支付方式业务主键',
  `shipping_method_uuid` char(20) DEFAULT NULL COMMENT '配送方式业务主键',
  `payment_method_type` int(11) DEFAULT NULL COMMENT '支付方式类型 0:支付宝 1:微信 2:银联 3:QQ钱包 4:余额',
  `is_invoice` bit(1) DEFAULT NULL COMMENT '是否需要发票',
  `invoice_type` int(1) DEFAULT NULL COMMENT '发票类型 0: 个人 1公司',
  `invoice_title` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '订单金额',
  `ip` varchar(50) DEFAULT NULL COMMENT '下单IP',
  `order_address` varchar(255) DEFAULT NULL COMMENT '下单地区',
  `area_name` varchar(255) DEFAULT NULL COMMENT '地区名称',
  `area_uuid` int(11) DEFAULT NULL COMMENT '地区业务主键',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `province_uuid` int(11) DEFAULT NULL COMMENT '省的业务主键',
  `province_name` varchar(255) DEFAULT NULL COMMENT '省的名称',
  `city_uuid` int(11) DEFAULT NULL COMMENT '市的业务主键',
  `city_name` varchar(255) DEFAULT NULL COMMENT '市的名称',
  PRIMARY KEY (`id`),
  KEY `index_sn` (`sn`) USING BTREE,
  KEY `index_productUuid` (`product_uuid`) USING BTREE,
  KEY `idx_order_uuid` (`order_uuid`),
  KEY `idx_del_flag` (`del_flag`),
  KEY `idx_member_uuid` (`member_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=352077 DEFAULT CHARSET=utf8;

/*Data for the table `order_item` */

insert  into `order_item`(`id`,`create_date`,`modify_date`,`version`,`is_delivery`,`name`,`price`,`quantity`,`sn`,`specifications`,`thumbnail`,`type`,`order_uuid`,`product_uuid`,`del_flag`,`uuid`,`creator`,`modifier`,`figure_uuid`,`payment_date`,`delivery_date`,`complete_date`,`expire`,`memo`,`offset_amount`,`phone`,`refund_amount`,`returned_quantity`,`shipped_quantity`,`shipping_method_name`,`shipping_number`,`status`,`member_name`,`member_uuid`,`payment_method_uuid`,`shipping_method_uuid`,`payment_method_type`,`is_invoice`,`invoice_type`,`invoice_title`,`amount`,`ip`,`order_address`,`area_name`,`area_uuid`,`address`,`province_uuid`,`province_name`,`city_uuid`,`city_name`) values (352076,'2017-11-16 15:29:43','2017-11-16 15:29:43',0,'','真丝修身m衣裙衣衣','0.010000',1,'20171116509647471','','',0,'97397638321010071','97100764777807878','\0','97397638321010081','97187413512544424','','test123','2018-01-16 18:17:04','2018-01-16 18:17:07','2018-01-16 18:17:09','0000-00-00 00:00:00','测试啊测试啊',NULL,'13916445873','0',0,0,'','','0','16ca12a2-7763-4390-b287-5d639a1b848e','97071061572518195','','',NULL,'\0',NULL,'','0','','','',NULL,'',NULL,'',NULL,'');

/*Table structure for table `order_log` */

DROP TABLE IF EXISTS `order_log`;

CREATE TABLE `order_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) NOT NULL COMMENT '版本号',
  `content` varchar(255) DEFAULT NULL COMMENT '详细原因',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `operator` varchar(255) DEFAULT NULL COMMENT '操作人',
  `type` int(11) NOT NULL COMMENT '操作类型',
  `order_sn` varchar(255) DEFAULT NULL COMMENT '订单号',
  `order_uuid` char(20) NOT NULL COMMENT '订单业务id',
  `del_flag` bit(1) NOT NULL COMMENT '删除标示',
  `uuid` char(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `creator` varchar(25) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`),
  KEY `idx_order_uuid` (`order_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1416142 DEFAULT CHARSET=utf8 COMMENT='订单日志';

/*Data for the table `order_log` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '更新时间',
  `payment_date` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_date` datetime DEFAULT NULL COMMENT '发货时间',
  `version` int(11) NOT NULL COMMENT '版本号',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `amount` decimal(21,6) NOT NULL COMMENT '订单金额',
  `area_name` varchar(255) DEFAULT NULL COMMENT '地区名称',
  `complete_date` datetime DEFAULT NULL COMMENT '完成日期',
  `consignee` varchar(100) DEFAULT NULL COMMENT '收货人',
  `coupon_discount` decimal(21,6) NOT NULL COMMENT '优惠券折扣',
  `exchange_point` int(11) NOT NULL COMMENT '兑换积分',
  `expire` datetime DEFAULT NULL COMMENT '过期时间',
  `fee` decimal(21,6) NOT NULL COMMENT '支付手续费',
  `freight` decimal(21,6) NOT NULL COMMENT '运费',
  `is_invoice` bit(1) DEFAULT NULL COMMENT '是否需要发票',
  `invoice_content` varchar(255) DEFAULT NULL COMMENT '发票内容',
  `invoice_title` varchar(100) DEFAULT NULL COMMENT '发票抬头',
  `is_exchange_point` bit(1) NOT NULL COMMENT '是否已兑换积分',
  `is_use_coupon_code` bit(1) NOT NULL COMMENT '是否使用优惠码',
  `memo` varchar(255) DEFAULT NULL COMMENT '附言',
  `offset_amount` decimal(21,6) NOT NULL COMMENT '调整金额',
  `payment_method_type` int(11) DEFAULT NULL COMMENT '支付方式类型 0:支付宝 1:微信 2:银联',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `price` decimal(21,6) NOT NULL COMMENT '商品价格',
  `quantity` int(11) NOT NULL COMMENT '商品数量',
  `refund_amount` decimal(21,6) NOT NULL COMMENT '退款金额',
  `returned_quantity` int(11) NOT NULL COMMENT '已退货数量 ',
  `shipped_quantity` int(11) NOT NULL COMMENT '已发货数量',
  `shipping_method_name` varchar(50) DEFAULT NULL COMMENT '配送方式名称',
  `shipping_number` varchar(50) DEFAULT NULL COMMENT '运单号',
  `sn` varchar(50) NOT NULL COMMENT '订单序列号',
  `status` int(11) NOT NULL COMMENT '订单状态 0:代付款 1:生产中 2:待发货 3:待签收 4:待评价 5:已取消 6:待退单 7:已完成',
  `tax` decimal(21,6) NOT NULL COMMENT '税金',
  `type` int(11) NOT NULL COMMENT '订单类型 0:普通订单 1:积分兑换订单',
  `zip_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `area_uuid` int(11) DEFAULT NULL COMMENT '地区业务主键',
  `coupon_uuid` char(20) DEFAULT NULL COMMENT '优惠券业务主键',
  `member_name` varchar(255) DEFAULT NULL COMMENT '下单用户',
  `member_uuid` char(20) NOT NULL COMMENT '会员业务主键',
  `payment_method_uuid` char(20) DEFAULT NULL COMMENT '支付方式业务主键',
  `shipping_method_uuid` char(20) DEFAULT NULL COMMENT '配送方式业务主键',
  `del_flag` bit(1) NOT NULL COMMENT '是否删除',
  `uuid` char(20) DEFAULT NULL COMMENT '业务主键',
  `figure_uuid` char(20) DEFAULT NULL COMMENT '体型表的业务主键',
  `creator` varchar(25) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(25) DEFAULT NULL COMMENT '最后修改人',
  `province_uuid` int(20) DEFAULT NULL COMMENT '省的业务主键',
  `province_name` varchar(50) DEFAULT NULL COMMENT '省的名称',
  `city_uuid` int(20) DEFAULT NULL COMMENT '市的业务主键',
  `city_name` varchar(50) DEFAULT NULL COMMENT '市的名称',
  `invoice_type` int(1) DEFAULT NULL COMMENT '发票类型 0: 个人 1公司 3其他',
  PRIMARY KEY (`id`),
  KEY `uni_order_sn` (`sn`),
  KEY `idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=352159 DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `orders` */

insert  into `orders`(`id`,`create_date`,`modify_date`,`payment_date`,`delivery_date`,`version`,`address`,`amount`,`area_name`,`complete_date`,`consignee`,`coupon_discount`,`exchange_point`,`expire`,`fee`,`freight`,`is_invoice`,`invoice_content`,`invoice_title`,`is_exchange_point`,`is_use_coupon_code`,`memo`,`offset_amount`,`payment_method_type`,`phone`,`price`,`quantity`,`refund_amount`,`returned_quantity`,`shipped_quantity`,`shipping_method_name`,`shipping_number`,`sn`,`status`,`tax`,`type`,`zip_code`,`area_uuid`,`coupon_uuid`,`member_name`,`member_uuid`,`payment_method_uuid`,`shipping_method_uuid`,`del_flag`,`uuid`,`figure_uuid`,`creator`,`modifier`,`province_uuid`,`province_name`,`city_uuid`,`city_name`,`invoice_type`) values (352158,'2017-11-16 15:29:42','2017-11-16 15:29:42','2018-01-16 18:19:00','2018-01-16 18:19:16',0,'航盛科技大厦2楼','0.010000','北京市东城区','2018-01-16 18:19:05','徐敏','0.000000',0,'2017-11-16 17:29:43','0.000000','0.000000','\0','','','\0','\0','','0.000000',NULL,'13916445873','0.010000',1,'0.000000',0,0,'','','20171116509613531',0,'0.000000',0,'',2,'','16ca12a2-7763-4390-b287-5d639a1b848e','97071061572518195','','','\0','97397638321010071','','97187413512544424','',1,'北京市',1,'北京市',NULL);

/*Table structure for table `report_order_area` */

DROP TABLE IF EXISTS `report_order_area`;

CREATE TABLE `report_order_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键 ',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1' COMMENT '状态：0隐藏1显示',
  `num` int(11) DEFAULT NULL COMMENT '购买量',
  `province` varchar(25) DEFAULT NULL COMMENT '省份id',
  PRIMARY KEY (`id`),
  KEY `Index_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `report_order_area` */

/*Table structure for table `report_repeat_buy_rate` */

DROP TABLE IF EXISTS `report_repeat_buy_rate`;

CREATE TABLE `report_repeat_buy_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(20) NOT NULL COMMENT '业务主键 ',
  `creator` varchar(25) DEFAULT NULL,
  `modifier` varchar(25) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `del_flag` bit(1) DEFAULT b'0',
  `version` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1' COMMENT '状态：0隐藏1显示',
  `rate` decimal(10,2) DEFAULT NULL COMMENT '重复购买率',
  PRIMARY KEY (`id`),
  KEY `Index_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

/*Data for the table `report_repeat_buy_rate` */

insert  into `report_repeat_buy_rate`(`id`,`uuid`,`creator`,`modifier`,`create_date`,`modify_date`,`del_flag`,`version`,`status`,`rate`) values (140,'10689932845268336876',NULL,NULL,'2018-01-17 02:00:02',NULL,'\0',1,'','0.00');

/*Table structure for table `sn` */

DROP TABLE IF EXISTS `sn`;

CREATE TABLE `sn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `version` bigint(20) NOT NULL,
  `last_value` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_sn_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `sn` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
