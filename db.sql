/*
SQLyog Ultimate v11.3 (64 bit)
MySQL - 5.7.32-log : Database - bianlidian
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bianlidian` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bianlidian`;

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='配置文件';

/*Data for the table `config` */

/*Table structure for table `dictionary` */

DROP TABLE IF EXISTS `dictionary`;

CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(200) DEFAULT NULL COMMENT '字段',
  `dic_name` varchar(200) DEFAULT NULL COMMENT '字段名',
  `code_index` int(11) DEFAULT NULL COMMENT '编码',
  `index_name` varchar(200) DEFAULT NULL COMMENT '编码名字  Search111 ',
  `super_id` int(11) DEFAULT NULL COMMENT '父字段id',
  `beizhu` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='字典';

/*Data for the table `dictionary` */

insert  into `dictionary`(`id`,`dic_code`,`dic_name`,`code_index`,`index_name`,`super_id`,`beizhu`,`create_time`) values (1,'shangxia_types','上下架名称',1,'上架',NULL,NULL,'2021-12-29 14:38:26'),(2,'shangxia_types','上下架名称',2,'下架',NULL,NULL,'2021-12-29 14:38:26'),(3,'goods_types','商品类型名称',1,'商品类型1',NULL,NULL,'2021-12-29 14:38:26'),(4,'goods_types','商品类型名称',2,'商品类型2',NULL,NULL,'2021-12-29 14:38:26'),(5,'goods_types','商品类型名称',3,'商品类型3',NULL,NULL,'2021-12-29 14:38:26'),(6,'goods_types','商品类型名称',4,'商品类型4',NULL,NULL,'2021-12-29 14:38:26'),(7,'goods_churu_inout_types','采购类型名称',2,'采购',NULL,NULL,'2021-12-29 14:38:26'),(8,'goods_shouyin_types','收银类型',1,'线下支付',NULL,NULL,'2021-12-29 14:38:26'),(9,'goods_shouyin_types','收银类型',2,'线上支付',NULL,NULL,'2021-12-29 14:38:26'),(10,'gongyingshang_types','供应商类型名称',1,'供应商类型1',NULL,NULL,'2021-12-29 14:38:27'),(11,'gongyingshang_types','供应商类型名称',2,'供应商类型2',NULL,NULL,'2021-12-29 14:38:27'),(12,'gongyingshang_xinyongdengji_types','供应商信用等级类型名称',1,'一级',NULL,NULL,'2021-12-29 14:38:27'),(13,'gongyingshang_xinyongdengji_types','供应商信用等级类型名称',2,'二级',NULL,NULL,'2021-12-29 14:38:27'),(14,'gongyingshang_xinyongdengji_types','供应商信用等级类型名称',3,'三级',NULL,NULL,'2021-12-29 14:38:27'),(15,'gongyingshang_suozaidiqu_types','所在地区类型名称',1,'地区1',NULL,NULL,'2021-12-29 14:38:27'),(16,'gongyingshang_suozaidiqu_types','所在地区类型名称',2,'地区2',NULL,NULL,'2021-12-29 14:38:27'),(17,'gongyingshang_suozaidiqu_types','所在地区类型名称',3,'地区3',NULL,NULL,'2021-12-29 14:38:27'),(18,'sex_types','性别类型名称',1,'男',NULL,NULL,'2021-12-29 15:47:06'),(19,'sex_types','性别类型名称',2,'女',NULL,NULL,'2021-12-29 15:47:06'),(20,'goods_types','商品类型名称',5,'商品类型5',NULL,NULL,'2021-12-30 09:07:48');

/*Table structure for table `gongyingshang` */

DROP TABLE IF EXISTS `gongyingshang`;

CREATE TABLE `gongyingshang` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `gongyingshang_name` varchar(200) DEFAULT NULL COMMENT '供应商名称  Search111 ',
  `gongyingshang_lianxi_name` varchar(200) DEFAULT NULL COMMENT '供应商联系人 Search111 ',
  `gongyingshang_phone` varchar(200) DEFAULT NULL COMMENT '供应商联系人手机号 Search111 ',
  `gongyingshang_types` int(11) DEFAULT NULL COMMENT '供应商类型',
  `gongyingshang_suozaidiqu_types` int(11) DEFAULT NULL COMMENT '所在地区',
  `gongyingshang_xinyongdengji_types` int(11) DEFAULT NULL COMMENT '信用等级 Search111 ',
  `gongyingshang_content` text COMMENT '供应商详情',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='供应商信息';

/*Data for the table `gongyingshang` */

insert  into `gongyingshang`(`id`,`gongyingshang_name`,`gongyingshang_lianxi_name`,`gongyingshang_phone`,`gongyingshang_types`,`gongyingshang_suozaidiqu_types`,`gongyingshang_xinyongdengji_types`,`gongyingshang_content`,`create_time`) values (1,'供应商1','啊1','17703786901',1,2,2,'<p>供应商1的详情</p>','2021-12-29 15:34:31'),(2,'供应商2','啊2','17703786902',1,1,1,'<p>供应商2的详情</p>','2021-12-29 15:35:04'),(3,'供应商3','张3','17703786903',1,2,3,'<p>供应商3的详情</p>','2021-12-30 09:10:16');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `goods_uuid_number` varchar(200) DEFAULT NULL COMMENT '商品唯一编号',
  `goods_name` varchar(200) DEFAULT NULL COMMENT '商品名称  Search111 ',
  `goods_photo` varchar(200) DEFAULT NULL COMMENT '商品照片',
  `goods_types` int(11) DEFAULT NULL COMMENT '商品类型 Search111',
  `goods_kucun_number` int(11) DEFAULT NULL COMMENT '商品库存  Search111',
  `goods_new_money` decimal(10,2) DEFAULT NULL COMMENT '进价',
  `goods_xiaoshou_money` decimal(10,2) DEFAULT NULL COMMENT '售价',
  `shangxia_types` int(11) DEFAULT NULL COMMENT '是否上架 ',
  `goods_delete` int(11) DEFAULT NULL COMMENT '逻辑删除',
  `goods_content` text COMMENT '商品简介 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品';

/*Data for the table `goods` */

insert  into `goods`(`id`,`goods_uuid_number`,`goods_name`,`goods_photo`,`goods_types`,`goods_kucun_number`,`goods_new_money`,`goods_xiaoshou_money`,`shangxia_types`,`goods_delete`,`goods_content`,`create_time`) values (1,'1640761440679','豪风雪花釉茶具','http://localhost:8080/bianlidian/upload/1640761456766.webp',4,279,'10.00','20.00',1,1,'<p>豪风雪花釉茶具的商品简介</p>','2021-12-29 15:04:40'),(2,'1640761485990','青瓷茶具','http://localhost:8080/bianlidian/upload/1640761589539.webp',3,189,'15.00','25.00',2,1,'<p>青瓷茶具的简介</p>','2021-12-29 15:06:46'),(3,'1640826722198','贝壳项链','http://localhost:8080/bianlidian/upload/1640826741225.jpg',5,497,'300.00','400.00',1,1,'<p>贝壳项链的简介</p>','2021-12-30 09:12:42');

/*Table structure for table `goods_churu_inout` */

DROP TABLE IF EXISTS `goods_churu_inout`;

CREATE TABLE `goods_churu_inout` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gongyingshang_id` int(11) DEFAULT NULL COMMENT '供应商',
  `goods_churu_inout_uuid_number` varchar(200) DEFAULT NULL COMMENT '出入库流水号',
  `goods_churu_inout_name` varchar(200) DEFAULT NULL COMMENT '入库名称 Search111 ',
  `goods_churu_inout_types` int(11) DEFAULT NULL COMMENT '出入库类型',
  `goods_churu_inout_content` text COMMENT '备注',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='采购';

/*Data for the table `goods_churu_inout` */

insert  into `goods_churu_inout`(`id`,`gongyingshang_id`,`goods_churu_inout_uuid_number`,`goods_churu_inout_name`,`goods_churu_inout_types`,`goods_churu_inout_content`,`insert_time`,`create_time`) values (1,2,'1640763342383','采购1',2,'','2021-12-29 15:35:42','2021-12-29 15:35:42'),(2,3,'1640826803450','采购2',2,'','2021-12-30 09:13:23','2021-12-30 09:13:23');

/*Table structure for table `goods_churu_inout_list` */

DROP TABLE IF EXISTS `goods_churu_inout_list`;

CREATE TABLE `goods_churu_inout_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_churu_inout_id` int(11) DEFAULT NULL COMMENT '采购',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品',
  `goods_churu_inout_list_number` int(11) DEFAULT NULL COMMENT '操作数量',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='采购详情';

/*Data for the table `goods_churu_inout_list` */

insert  into `goods_churu_inout_list`(`id`,`goods_churu_inout_id`,`goods_id`,`goods_churu_inout_list_number`,`insert_time`,`create_time`) values (1,1,1,3,'2021-12-29 15:35:42','2021-12-29 15:35:42'),(2,1,2,2,'2021-12-29 15:35:42','2021-12-29 15:35:42'),(3,2,1,3,'2021-12-30 09:13:23','2021-12-30 09:13:23'),(4,2,2,2,'2021-12-30 09:13:23','2021-12-30 09:13:23'),(5,2,3,5,'2021-12-30 09:13:23','2021-12-30 09:13:23');

/*Table structure for table `goods_shouyin` */

DROP TABLE IF EXISTS `goods_shouyin`;

CREATE TABLE `goods_shouyin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_shouyin_uuid_number` varchar(200) DEFAULT NULL COMMENT '收银流水号 Search111 ',
  `yuangong_id` int(11) DEFAULT NULL COMMENT '员工',
  `goods_shouyin_true_price` decimal(10,2) DEFAULT NULL COMMENT '实付金额',
  `goods_shouyin_types` int(11) DEFAULT NULL COMMENT '收银类型 Search111 ',
  `goods_shouyin_content` text COMMENT '备注',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '购买时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='收银';

/*Data for the table `goods_shouyin` */

insert  into `goods_shouyin`(`id`,`goods_shouyin_uuid_number`,`yuangong_id`,`goods_shouyin_true_price`,`goods_shouyin_types`,`goods_shouyin_content`,`insert_time`,`create_time`) values (2,'1640763917825',NULL,'110.00',2,'','2021-12-29 15:45:18','2021-12-29 15:45:18'),(3,'1640765169163',1,'115.00',2,'','2021-12-29 16:06:09','2021-12-29 16:06:09'),(4,'1640765194550',1,'225.00',1,'','2021-12-29 16:06:35','2021-12-29 16:06:35'),(5,'1640765217609',2,'60.00',2,'','2021-12-29 16:06:58','2021-12-29 16:06:58'),(6,'1640765236867',2,'155.00',2,'','2021-12-29 16:07:17','2021-12-29 16:07:17'),(7,'1640765257336',2,'60.00',1,'','2021-12-29 16:07:37','2021-12-29 16:07:37'),(8,'1640765169163',1,'115.00',2,'','2021-11-29 16:06:09','2021-11-29 16:06:09'),(9,'1640765194550',1,'225.00',1,'','2021-11-29 16:06:35','2021-11-29 16:06:35'),(10,'1640765217609',2,'60.00',2,'','2021-11-29 16:06:58','2021-11-29 16:06:58'),(11,'1640765236867',2,'155.00',2,'','2021-11-29 16:07:17','2021-11-29 16:07:17'),(12,'1640765257336',2,'60.00',1,'','2021-11-29 16:07:37','2021-11-29 16:07:37'),(13,'1640826890363',NULL,'800.00',2,'','2021-12-30 09:14:50','2021-12-30 09:14:50'),(14,'1640826907799',NULL,'1260.00',1,'','2021-12-30 09:15:08','2021-12-30 09:15:08'),(15,'1640827208329',1,'800.00',2,'','2021-12-30 09:20:08','2021-12-30 09:20:08'),(16,'1640827217891',1,'420.00',1,'','2021-12-30 09:20:18','2021-12-30 09:20:18');

/*Table structure for table `goods_shouyin_list` */

DROP TABLE IF EXISTS `goods_shouyin_list`;

CREATE TABLE `goods_shouyin_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_shouyin_id` int(11) DEFAULT NULL COMMENT '收银',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品',
  `goods_shouyin_list_number` int(11) DEFAULT NULL COMMENT '购买数量',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '购买时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='收银详情';

/*Data for the table `goods_shouyin_list` */

insert  into `goods_shouyin_list`(`id`,`goods_shouyin_id`,`goods_id`,`goods_shouyin_list_number`,`insert_time`,`create_time`) values (3,2,1,3,'2021-12-29 15:45:18','2021-12-29 15:45:18'),(4,2,2,2,'2021-12-29 15:45:18','2021-12-29 15:45:18'),(5,3,1,2,'2021-12-29 16:06:09','2021-12-29 16:06:09'),(6,3,2,3,'2021-12-29 16:06:09','2021-12-29 16:06:09'),(7,4,1,5,'2021-12-29 16:06:35','2021-12-29 16:06:35'),(8,4,2,5,'2021-12-29 16:06:35','2021-12-29 16:06:35'),(9,5,1,3,'2021-12-29 16:06:58','2021-12-29 16:06:58'),(10,6,1,4,'2021-12-29 16:07:17','2021-12-29 16:07:17'),(11,6,2,3,'2021-12-29 16:07:17','2021-12-29 16:07:17'),(12,7,1,3,'2021-12-29 16:07:37','2021-12-29 16:07:37'),(13,8,1,2,'2021-11-29 16:06:09','2021-11-29 16:06:09'),(14,8,2,3,'2021-11-29 16:06:09','2021-11-29 16:06:09'),(15,9,1,5,'2021-11-29 16:06:35','2021-11-29 16:06:35'),(16,9,2,5,'2021-11-29 16:06:35','2021-11-29 16:06:35'),(17,10,1,3,'2021-11-29 16:06:58','2021-11-29 16:06:58'),(18,11,1,4,'2021-11-29 16:07:17','2021-11-29 16:07:17'),(19,11,2,3,'2021-11-29 16:07:17','2021-11-29 16:07:17'),(20,12,1,3,'2021-11-29 16:07:37','2021-11-29 16:07:37'),(21,13,3,2,'2021-12-30 09:14:50','2021-12-30 09:14:50'),(22,14,1,3,'2021-12-30 09:15:08','2021-12-30 09:15:08'),(23,14,3,3,'2021-12-30 09:15:08','2021-12-30 09:15:08'),(24,15,3,2,'2021-12-30 09:20:08','2021-12-30 09:20:08'),(25,16,1,1,'2021-12-30 09:20:18','2021-12-30 09:20:18'),(26,16,3,1,'2021-12-30 09:20:18','2021-12-30 09:20:18');

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='token表';

/*Data for the table `token` */

insert  into `token`(`id`,`userid`,`username`,`tablename`,`role`,`token`,`addtime`,`expiratedtime`) values (1,6,'admin','users','管理员','7luw4j4mpqh7dve7u1sct4hvzw1o5ugt','2021-12-29 14:43:36','2021-12-30 10:21:22'),(2,1,'a1','yuangong','员工','am8bk12as3n5xyx9vbyfnlluokf84qy8','2021-12-29 16:05:36','2021-12-30 10:17:11'),(3,2,'a2','yuangong','员工','mkuaz7favum9c5p2203n9gedpwy02l0f','2021-12-29 16:06:46','2021-12-29 17:06:47');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员表';

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`role`,`addtime`) values (6,'admin','admin','管理员','2021-04-27 14:51:13');

/*Table structure for table `yuangong` */

DROP TABLE IF EXISTS `yuangong`;

CREATE TABLE `yuangong` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) DEFAULT NULL COMMENT '账户',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `yuangong_name` varchar(200) DEFAULT NULL COMMENT '员工姓名 Search111 ',
  `yuangong_phone` varchar(200) DEFAULT NULL COMMENT '员工手机号 Search111 ',
  `yuangong_id_number` varchar(200) DEFAULT NULL COMMENT '员工身份证号 Search111 ',
  `yuangong_photo` varchar(200) DEFAULT NULL COMMENT '员工头像',
  `sex_types` int(11) DEFAULT NULL COMMENT '性别',
  `yuangong_email` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='员工';

/*Data for the table `yuangong` */

insert  into `yuangong`(`id`,`username`,`password`,`yuangong_name`,`yuangong_phone`,`yuangong_id_number`,`yuangong_photo`,`sex_types`,`yuangong_email`,`create_time`) values (1,'a1','123456','张1','17703786901','410224199610232001','http://localhost:8080/bianlidian/upload/1640764108037.webp',1,'1@qq.com','2021-12-29 15:48:40'),(2,'a2','123456','张2','17703786902','410224199610232002','http://localhost:8080/bianlidian/upload/1640764146653.webp',2,'2@qq.com','2021-12-29 15:49:16'),(3,'a3','123456','张3','17703786903','410224199610232003','http://localhost:8080/bianlidian/upload/1640827000464.jpg',2,'33@qq.com','2021-12-30 09:17:03');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
