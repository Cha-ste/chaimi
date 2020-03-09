/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : chaimi

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-03-09 09:32:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `img` varchar(64) DEFAULT NULL COMMENT '图片地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `reserve1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `rank` int(8) DEFAULT NULL COMMENT '排序',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `parent` int(11) DEFAULT NULL COMMENT '父级id',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态（0-不显示，1-显示）',
  `reserve1` varchar(64) DEFAULT NULL COMMENT '预留字段',
  `reserve2` varchar(64) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标题',
  `headline` varchar(32) DEFAULT NULL,
  `subhead` varchar(255) DEFAULT NULL COMMENT '副标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `discount` tinyint(4) DEFAULT NULL COMMENT '折扣（百分比）',
  `category_id` int(11) DEFAULT NULL COMMENT '分类',
  `goods_item_id` int(11) DEFAULT NULL COMMENT '默认SKU',
  `if_putaway` tinyint(2) DEFAULT NULL COMMENT '是否上下架（0-否，1-是）',
  `primary_image` varchar(255) DEFAULT NULL COMMENT '主图',
  `theme_image` varchar(255) DEFAULT NULL COMMENT '主题图',
  `carousel_image` varchar(320) DEFAULT NULL COMMENT '轮播图（;分隔）',
  `detail_image` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL COMMENT '标签（;分隔）',
  `specification_id` varchar(64) DEFAULT NULL COMMENT '规格（;分隔）',
  `visible_grid_id` int(11) DEFAULT NULL COMMENT '可视规格',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `if_test` tinyint(2) DEFAULT NULL COMMENT '是否测试（0-否，1-是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for goods_item
-- ----------------------------
DROP TABLE IF EXISTS `goods_item`;
CREATE TABLE `goods_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `headline` varchar(64) DEFAULT NULL COMMENT '标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `discount` tinyint(4) DEFAULT NULL COMMENT '折扣',
  `discount_type` char(20) DEFAULT NULL COMMENT '打折类型',
  `discount_price` decimal(10,2) DEFAULT NULL COMMENT '折后价',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `good_id` int(11) DEFAULT NULL COMMENT '所属商品',
  `if_putaway` tinyint(2) DEFAULT NULL COMMENT '是否上下架（0-否，1-是）',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `specification_list` varchar(255) DEFAULT NULL COMMENT 'specification列表（格式：specification_id-specification_item_id;即，规格-规格值；）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for grid
-- ----------------------------
DROP TABLE IF EXISTS `grid`;
CREATE TABLE `grid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '六宫格名称',
  `title` varchar(64) DEFAULT NULL COMMENT '六宫格标题',
  `category_id` int(11) DEFAULT NULL COMMENT '所属分类',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `reserve` varchar(32) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='六宫格表';

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator` varchar(20) DEFAULT NULL COMMENT '操作者',
  `business` varchar(20) DEFAULT NULL COMMENT '业务名称',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `reserve` varchar(20) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for specification
-- ----------------------------
DROP TABLE IF EXISTS `specification`;
CREATE TABLE `specification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `remark` varchar(64) DEFAULT NULL COMMENT '描述',
  `unit` char(20) DEFAULT NULL COMMENT '单位',
  `if_standard` tinyint(2) DEFAULT NULL COMMENT '是否标准 0-否，1-是',
  `reserve` varchar(63) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for specification_item
-- ----------------------------
DROP TABLE IF EXISTS `specification_item`;
CREATE TABLE `specification_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specification_id` int(11) DEFAULT NULL COMMENT '规格',
  `name` varchar(32) DEFAULT NULL COMMENT '规格值名称',
  `spend` varchar(32) DEFAULT NULL COMMENT '扩展',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(32) NOT NULL COMMENT '手机号码',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `sex` bigint(2) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '简介',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
