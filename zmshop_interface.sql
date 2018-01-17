/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.20-log : Database - zmshop_interface
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zmshop_interface` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zmshop_interface`;

/*Table structure for table `colour_ifi` */

DROP TABLE IF EXISTS `colour_ifi`;

CREATE TABLE `colour_ifi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` varchar(200) NOT NULL COMMENT '颜色编码',
  `name` varchar(250) NOT NULL COMMENT '颜色名称',
  `barcode` varchar(200) DEFAULT '' COMMENT '条码编码',
  `image_src` varchar(500) DEFAULT '' COMMENT '颜色图片',
  `rgb` varchar(20) DEFAULT NULL,
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `updater_id` varchar(150) DEFAULT '' COMMENT '更新系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46422 DEFAULT CHARSET=utf8 COMMENT='颜色资料接口表';

/*Data for the table `colour_ifi` */

/*Table structure for table `component_type_ifi` */

DROP TABLE IF EXISTS `component_type_ifi`;

CREATE TABLE `component_type_ifi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `cipp_recoed_id` int(11) DEFAULT NULL COMMENT '层级id字段，对应parent_id使用',
  `code` varchar(200) NOT NULL COMMENT '类别编码',
  `name` varchar(250) NOT NULL COMMENT '类别名称',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除、10同步失败',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级物料类别ID',
  `updater_id` varchar(50) DEFAULT NULL COMMENT '更新系统 来源系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `leaf` varchar(100) DEFAULT '' COMMENT '是否叶子',
  `path` varchar(500) DEFAULT '' COMMENT '路径(所有层级ID 组合 非必填)',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `code_idx` (`code`) USING BTREE,
  KEY `name_idx` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='部件类别';

/*Data for the table `component_type_ifi` */

/*Table structure for table `ifi_colgroup_info` */

DROP TABLE IF EXISTS `ifi_colgroup_info`;

CREATE TABLE `ifi_colgroup_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` varchar(50) NOT NULL COMMENT '颜色组编码',
  `name` varchar(50) NOT NULL COMMENT '颜色组名称',
  `sort_id` int(11) DEFAULT NULL COMMENT '排序ID',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 删除 1 启用 有效 2 禁用',
  `updater_id` varchar(50) DEFAULT NULL COMMENT '更新系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) NOT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `if_status` tinyint(1) NOT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 COMMENT='颜色组接口表';

/*Data for the table `ifi_colgroup_info` */

/*Table structure for table `ifi_craft_data` */

DROP TABLE IF EXISTS `ifi_craft_data`;

CREATE TABLE `ifi_craft_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `_id` int(11) NOT NULL COMMENT '对应parent_id',
  `code` varchar(50) DEFAULT '' COMMENT '编码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `prefix` varchar(32) DEFAULT NULL COMMENT '编码前缀',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态:0删除、1启用、2禁用',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级ID',
  `updater_id` varchar(50) DEFAULT NULL COMMENT '更新系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) NOT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `if_status` tinyint(1) NOT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `leaf` varchar(100) NOT NULL DEFAULT '' COMMENT '是否叶子:Y是N否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8 COMMENT='工艺数据库接口表';

/*Data for the table `ifi_craft_data` */

/*Table structure for table `ifi_craft_data_dtl` */

DROP TABLE IF EXISTS `ifi_craft_data_dtl`;

CREATE TABLE `ifi_craft_data_dtl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `craft_id` int(11) DEFAULT NULL COMMENT '工艺主表ID',
  `craft_code` varchar(32) DEFAULT '' COMMENT '工艺主表code',
  `craft_type` varchar(32) DEFAULT NULL COMMENT '工艺类型',
  `craft_handle` varchar(32) DEFAULT NULL COMMENT '工艺操作',
  `craft_info` varchar(500) DEFAULT NULL COMMENT '工艺描述',
  `use_frequency` varchar(32) DEFAULT NULL COMMENT '使用频次',
  `translate_rule` varchar(32) DEFAULT NULL COMMENT '翻译规则',
  `device_type` int(32) DEFAULT NULL COMMENT '车种类型',
  `example_image` varchar(200) DEFAULT NULL COMMENT '范例图示',
  `example_video` varchar(200) DEFAULT NULL COMMENT '范例视频',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态:0删除、1启用、2禁用',
  `updater_id` varchar(150) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) NOT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `if_status` tinyint(1) NOT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=582 DEFAULT CHARSET=utf8 COMMENT='工艺数据库明细';

/*Data for the table `ifi_craft_data_dtl` */

/*Table structure for table `ifi_material_type` */

DROP TABLE IF EXISTS `ifi_material_type`;

CREATE TABLE `ifi_material_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `cipp_recoed_id` int(11) DEFAULT NULL COMMENT '层级id字段，对应parent_id使用',
  `code` varchar(50) NOT NULL COMMENT '类别编码',
  `name` varchar(50) NOT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 删除 1 启用 有效 2 禁用',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级物料类别ID',
  `updater_id` varchar(50) DEFAULT NULL COMMENT '更新系统 来源系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) NOT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `if_status` tinyint(1) NOT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `leaf` varchar(100) DEFAULT NULL COMMENT '是否叶子',
  `path` varchar(500) DEFAULT NULL COMMENT '路径(所有层级ID 组合 非必填)',
  PRIMARY KEY (`id`),
  KEY `code_idx` (`code`) USING BTREE,
  KEY `name_idx` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=627 DEFAULT CHARSET=utf8 COMMENT='物料类别';

/*Data for the table `ifi_material_type` */

/*Table structure for table `ifi_measure_unit` */

DROP TABLE IF EXISTS `ifi_measure_unit`;

CREATE TABLE `ifi_measure_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` varchar(50) NOT NULL COMMENT '编码',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort_id` int(11) DEFAULT NULL COMMENT '排序ID',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 删除 1 启用 有效 2 禁用',
  `updater_id` varchar(50) DEFAULT NULL COMMENT '更新系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) NOT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `if_status` tinyint(1) NOT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='计量单位';

/*Data for the table `ifi_measure_unit` */

/*Table structure for table `ifo_order_detail_bom` */

DROP TABLE IF EXISTS `ifo_order_detail_bom`;

CREATE TABLE `ifo_order_detail_bom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_item_sn` varchar(50) DEFAULT NULL COMMENT '子订单号',
  `style_code` varchar(100) DEFAULT NULL COMMENT '款式编码',
  `component_type_code` varchar(100) DEFAULT NULL COMMENT '部件类别编码',
  `component_type_name` varchar(100) DEFAULT NULL COMMENT '部件类别名称',
  `parts_code` varchar(200) DEFAULT NULL COMMENT '零部件编码',
  `parts_name` varchar(200) DEFAULT NULL COMMENT '零部件名称',
  `material_type_code` varchar(200) DEFAULT NULL COMMENT '物料类别',
  `material_code` varchar(200) DEFAULT NULL COMMENT '物料编码',
  `material_name` varchar(200) DEFAULT NULL COMMENT '物料名称',
  `quantity` decimal(8,4) DEFAULT NULL COMMENT '数量',
  `instance_type` varchar(2) DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL COMMENT '数量单位',
  `pieces_code` varchar(100) DEFAULT NULL COMMENT '裁片编码',
  `pieces_path` varchar(255) DEFAULT NULL COMMENT '裁片路径',
  `pieces_name` varchar(32) DEFAULT NULL COMMENT '裁片名称',
  `fact_flag` tinyint(1) DEFAULT NULL COMMENT '是否需要实样',
  `parts_pattern_path` varchar(255) DEFAULT NULL COMMENT '零部件纸样图路径',
  `parts_path` varchar(255) DEFAULT NULL COMMENT '零部件文件路径',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_id` varchar(50) DEFAULT NULL COMMENT '更新系统',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细BOM接口表';

/*Data for the table `ifo_order_detail_bom` */

/*Table structure for table `ifo_order_info` */

DROP TABLE IF EXISTS `ifo_order_info`;

CREATE TABLE `ifo_order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(50) DEFAULT NULL COMMENT '主订单编号',
  `member_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `create_order_date` datetime DEFAULT NULL COMMENT '下单时间',
  `delivery` datetime DEFAULT NULL COMMENT '承诺交期',
  `amount` decimal(21,6) DEFAULT NULL COMMENT '总价',
  `freight` decimal(21,6) DEFAULT NULL COMMENT '运费',
  `is_invoice` tinyint(1) DEFAULT NULL COMMENT '是否开据发票, 1:开，0：未开',
  `invoice_type` tinyint(1) DEFAULT NULL COMMENT '发票类型 1:个人，2：公司, 3：其他',
  `invoice_title` varchar(100) DEFAULT NULL COMMENT '发票抬头',
  `payment_method_type` tinyint(1) DEFAULT NULL COMMENT '支付方式 0:支付宝 1:微信 2:银联',
  `payment_status` tinyint(1) DEFAULT NULL COMMENT '支付状态 0未付款、1已付款',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `province_uuid` int(11) DEFAULT NULL COMMENT '省',
  `province_name` varchar(20) DEFAULT NULL COMMENT '省名称',
  `city_uuid` int(11) DEFAULT NULL COMMENT '市',
  `city_name` varchar(20) DEFAULT NULL COMMENT '市名称',
  `area_uuid` int(11) DEFAULT NULL COMMENT '区',
  `area_name` varchar(20) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL COMMENT '街道',
  `detail_address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `zip_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `consignee` varchar(100) DEFAULT NULL COMMENT '收货人',
  `memo` varchar(255) DEFAULT NULL COMMENT '客户备注',
  `modify_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(255) DEFAULT NULL COMMENT '更新系统',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  `create_time` datetime DEFAULT NULL COMMENT '插入接口表时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='运营主订单接口表';

/*Data for the table `ifo_order_info` */

/*Table structure for table `ifo_order_info_detail` */

DROP TABLE IF EXISTS `ifo_order_info_detail`;

CREATE TABLE `ifo_order_info_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_item_sn` varchar(50) DEFAULT NULL COMMENT '子订单编码',
  `order_sn` varchar(50) DEFAULT NULL COMMENT '主订单编号',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` decimal(21,6) DEFAULT NULL COMMENT '单价',
  `style_code` varchar(100) DEFAULT NULL COMMENT '款式编码',
  `style_name` varchar(255) DEFAULT NULL COMMENT '款式名称',
  `style_type_code` varchar(100) DEFAULT NULL COMMENT '款式分类编码',
  `style_type_name` varchar(255) DEFAULT NULL COMMENT '款式分类名称',
  `st_flag` varchar(1) DEFAULT NULL COMMENT '特殊工艺标识',
  `st_codes` varchar(255) DEFAULT NULL COMMENT '特殊工艺花样编码',
  `sy_file_path` varchar(500) DEFAULT NULL COMMENT '实样文件地址',
  `file_path` varchar(1000) DEFAULT NULL COMMENT '主设计文件路径',
  `designer_code` varchar(17) DEFAULT NULL COMMENT '设计师编码',
  `designer_name` varchar(100) DEFAULT NULL COMMENT '设计师名称',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_id` varchar(50) DEFAULT NULL,
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营子订单接口表';

/*Data for the table `ifo_order_info_detail` */

/*Table structure for table `ifo_order_stitching_info` */

DROP TABLE IF EXISTS `ifo_order_stitching_info`;

CREATE TABLE `ifo_order_stitching_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stitching_code` varchar(100) DEFAULT NULL COMMENT '缝合工艺编码',
  `stitching_desc` varchar(255) DEFAULT NULL COMMENT '工艺描述',
  `pieces_codes` varchar(255) DEFAULT NULL COMMENT '对应裁片编码',
  `parts_codes` varchar(255) DEFAULT NULL COMMENT '对应零部件编码',
  `standard_codes` varchar(255) DEFAULT NULL COMMENT '对应的标准工艺编码',
  `style_code` varchar(100) DEFAULT NULL COMMENT '款式编码',
  `order_item_sn` varchar(50) DEFAULT NULL COMMENT '订单号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_id` varchar(50) DEFAULT NULL COMMENT '更新系统 来源系统',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='缝合关系接口表';

/*Data for the table `ifo_order_stitching_info` */

/*Table structure for table `ifo_order_style_file_info` */

DROP TABLE IF EXISTS `ifo_order_style_file_info`;

CREATE TABLE `ifo_order_style_file_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号id',
  `order_item_sn` varchar(50) DEFAULT NULL COMMENT '子订单号',
  `style_code` varchar(100) DEFAULT NULL COMMENT '款式编码',
  `material_code` varchar(100) DEFAULT NULL COMMENT '物料编码',
  `breadth` decimal(21,6) DEFAULT NULL COMMENT '幅宽',
  `quantity` decimal(8,4) DEFAULT NULL COMMENT '数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '数量单位',
  `acreage` decimal(21,6) DEFAULT NULL COMMENT '面积',
  `cc_plt_path` varchar(255) DEFAULT NULL,
  `plt_path` varchar(255) DEFAULT NULL COMMENT '纸样PLT文件路径',
  `nc_path` varchar(255) DEFAULT NULL COMMENT '裁床NC文件路径',
  `is_robot` varchar(1) DEFAULT NULL COMMENT '是否机器裁剪：0：否，1：是',
  `is_need_material` tinyint(1) DEFAULT NULL COMMENT '是否耗材：0：否，1：是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  `update_id` varchar(50) DEFAULT NULL COMMENT '更新系统',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='款式文件接口表';

/*Data for the table `ifo_order_style_file_info` */

/*Table structure for table `ifo_process_info` */

DROP TABLE IF EXISTS `ifo_process_info`;

CREATE TABLE `ifo_process_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) DEFAULT NULL,
  `order_item_sn` varchar(50) DEFAULT NULL COMMENT '子订单号',
  `style_code` varchar(100) DEFAULT NULL COMMENT '款式编码',
  `style_type_code` varchar(100) DEFAULT NULL COMMENT '款式分类',
  `process_memo` varchar(255) DEFAULT NULL COMMENT '工序描述',
  `process_no` int(11) DEFAULT NULL COMMENT '顺序号',
  `equipment_type` varchar(100) DEFAULT NULL COMMENT '车种类型',
  `component_type_code` varchar(100) DEFAULT NULL COMMENT '部件分类编码',
  `parts_type_code` varchar(100) DEFAULT NULL COMMENT '零件分类编码',
  `stitching_codes` varchar(100) DEFAULT NULL COMMENT '对应的缝合工艺编码',
  `pieces_codes` varchar(255) DEFAULT NULL COMMENT '对应的裁片信息 多个以“，”号隔开',
  `component_parts_codes` varchar(50) DEFAULT NULL COMMENT '零部件编号 多个以“，”号隔开',
  `pieces_parts_type` varchar(50) DEFAULT NULL COMMENT '1 裁片缝合 2 零部件组装缝合',
  `pri_st` decimal(11,2) DEFAULT NULL COMMENT '标准工价',
  `labor_st` decimal(11,2) DEFAULT NULL COMMENT '标准工时',
  `create_time` datetime DEFAULT NULL,
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='缝制工艺工序接口表';

/*Data for the table `ifo_process_info` */

/*Table structure for table `material_f_ifo` */

DROP TABLE IF EXISTS `material_f_ifo`;

CREATE TABLE `material_f_ifo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(200) DEFAULT NULL COMMENT '辅料编号',
  `name` varchar(200) DEFAULT NULL COMMENT '辅料名称',
  `type` varchar(100) DEFAULT NULL COMMENT '辅料类型',
  `unit` varchar(32) DEFAULT NULL COMMENT '计量单位',
  `color_code` varchar(500) DEFAULT '' COMMENT '颜色编码',
  `color_name` varchar(32) DEFAULT NULL COMMENT '颜色名称',
  `image_src` varchar(500) DEFAULT NULL COMMENT '辅料图片',
  `price` decimal(32,0) DEFAULT NULL COMMENT '价格(非必填)',
  `specs` varchar(32) DEFAULT NULL COMMENT '规格(例：线0.5mm)',
  `shrink` tinyint(1) DEFAULT NULL COMMENT '是否缩水',
  `weight` float(11,2) DEFAULT NULL COMMENT '克重',
  `updater_id` varchar(20) DEFAULT NULL COMMENT '更新系统 来源系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT NULL COMMENT '分布式字段',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `supplier_remark` varchar(500) DEFAULT NULL COMMENT '供应商信息备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='辅料资料';

/*Data for the table `material_f_ifo` */

/*Table structure for table `material_m_ifo` */

DROP TABLE IF EXISTS `material_m_ifo`;

CREATE TABLE `material_m_ifo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(200) DEFAULT NULL COMMENT '面料编号',
  `name` varchar(200) DEFAULT NULL COMMENT '面料名称',
  `type` varchar(100) DEFAULT NULL COMMENT '面料类型',
  `unit` varchar(32) DEFAULT NULL COMMENT '计量单位',
  `price` decimal(32,0) DEFAULT NULL COMMENT '价格(非必填)',
  `color_code` varchar(32) DEFAULT NULL COMMENT '颜色编号',
  `color_name` varchar(32) DEFAULT '' COMMENT '颜色名称',
  `image_src` varchar(500) DEFAULT NULL COMMENT '面料图片',
  `breadth` decimal(21,6) DEFAULT NULL COMMENT '幅宽',
  `shrink` tinyint(1) DEFAULT NULL COMMENT '是否缩水： 0 否，1 是',
  `safe_inventory` int(11) DEFAULT NULL COMMENT '安全库存',
  `weight` float(11,2) DEFAULT NULL COMMENT '克重',
  `specs` varchar(32) DEFAULT '' COMMENT '规格(例：线0.5mm)',
  `updater_id` varchar(20) DEFAULT NULL COMMENT '更新系统 来源系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT NULL COMMENT '分布式字段',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除，10同步失败',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `supplier_remark` varchar(500) DEFAULT NULL COMMENT '供应商信息备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='面料资料';

/*Data for the table `material_m_ifo` */

/*Table structure for table `parts_type_ifi` */

DROP TABLE IF EXISTS `parts_type_ifi`;

CREATE TABLE `parts_type_ifi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `cipp_recoed_id` int(11) DEFAULT NULL COMMENT '层级id字段，对应parent_id使用',
  `code` varchar(200) NOT NULL DEFAULT '' COMMENT '类别编码',
  `name` varchar(250) DEFAULT '' COMMENT '类别名称',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除、10同步失败',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级物料类别ID',
  `updater_id` varchar(50) DEFAULT NULL COMMENT '更新系统 来源系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `leaf` varchar(100) DEFAULT '' COMMENT '是否叶子',
  `path` varchar(500) DEFAULT '' COMMENT '路径(所有层级ID 组合 非必填)',
  `oper_st` tinyint(1) DEFAULT NULL COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `code_idx` (`code`) USING BTREE,
  KEY `name_idx` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1273 DEFAULT CHARSET=utf8 COMMENT='零件类别';

/*Data for the table `parts_type_ifi` */

/*Table structure for table `style_type_ifi` */

DROP TABLE IF EXISTS `style_type_ifi`;

CREATE TABLE `style_type_ifi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `cipp_recoed_id` int(11) DEFAULT NULL COMMENT '层级id字段，对应parent_id使用',
  `code` varchar(200) NOT NULL COMMENT '类别编码',
  `name` varchar(250) NOT NULL COMMENT '类别名称',
  `if_status` tinyint(1) DEFAULT NULL COMMENT '状态 0初始、4同步中、8同步完成可删除、10同步失败',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级物料类别ID',
  `updater_id` varchar(50) DEFAULT NULL COMMENT '更新系统 来源系统',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `leaf` varchar(100) DEFAULT NULL COMMENT '是否叶子',
  `path` varchar(500) DEFAULT NULL COMMENT '路径(所有层级ID 组合 非必填)',
  `oper_st` tinyint(1) DEFAULT '0' COMMENT '操作区分，0新增，4修改，8删除',
  `dist_id` varchar(30) DEFAULT '' COMMENT '分布式字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `code_idx` (`code`) USING BTREE,
  KEY `name_idx` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='款式类别';

/*Data for the table `style_type_ifi` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
