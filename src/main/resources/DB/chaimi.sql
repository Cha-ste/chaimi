/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 8.0.17 : Database - chaimi
*********************************************************************
*/

/*
mysql8.0 => utf8mb4_0900_ai_ci
mysql5.7 => utf8mb4
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chaimi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `chaimi`;

/*Table structure for table `banner` */

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `img` varchar(64) DEFAULT NULL COMMENT '图片地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `reserve1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4;

/*Data for the table `banner` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
     `id`       int(11)     NOT NULL AUTO_INCREMENT,
     `mobile`   varchar(32) NOT NULL COMMENT '手机号码',
     `password` varchar(128) DEFAULT NULL COMMENT '密码',
     `sex`      bigint(2)    DEFAULT NULL,
     `email`    varchar(32)  DEFAULT NULL,
     `remark`   varchar(255) DEFAULT NULL COMMENT '简介',
     `username` varchar(32)  DEFAULT NULL COMMENT '用户名',
     `address`  varchar(255) DEFAULT NULL COMMENT '地址',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '13074488946', '123456', '1', 'xxx@ZH.com', 'step by step', 'ocean', 'GuangDong');

CREATE DATABASE /*!32312 IF NOT EXISTS*/`chaimi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `chaimi`;

/*Table structure for table `logs` */

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `operator` varchar(20) DEFAULT NULL COMMENT '操作者',
    `business` varchar(20) DEFAULT NULL COMMENT '业务名称',
    `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
    `reserve` varchar(20) DEFAULT NULL COMMENT '预留字段',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4;

/*Data for the table `logs` */

insert  into `logs`(`id`,`operator`,`business`,`operate_time`,`reserve`) values (1,'ocean','添加日志','2020-02-27 08:00:00',NULL),(2,'ocean','添加日志2','2020-02-27 08:00:00',NULL);

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