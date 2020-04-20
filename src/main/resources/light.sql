/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.18 : Database - light
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`light` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `light`;

/*Table structure for table `dongtai` */

DROP TABLE IF EXISTS `dongtai`;

CREATE TABLE `dongtai` (
  `dtid` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `dtcontent` varchar(500) DEFAULT NULL COMMENT '动态文字',
  `dtpic` varchar(500) DEFAULT NULL COMMENT '图片集合 空格隔开',
  `dtcreatetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT '0',
  `is_locked` tinyint(4) DEFAULT '0',
  `deviceinfo` varchar(30) DEFAULT NULL COMMENT '设备信息',
  PRIMARY KEY (`dtid`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `dongtai` */

insert  into `dongtai`(`dtid`,`userid`,`dtcontent`,`dtpic`,`dtcreatetime`,`is_deleted`,`is_locked`,`deviceinfo`) values (52,1,'开森','/uploadfile/dongtai_ic/20200325_192037600.jpg /uploadfile/dongtai_ic/20200325_192037271.jpg','2020-03-25 19:20:37',0,0,'最帅的我'),(53,1,'生无可恋','/uploadfile/dongtai_ic/20200325_192140450.jpg','2020-03-25 19:21:40',0,0,'最帅的我'),(54,1,NULL,'/uploadfile/dongtai_ic/20200325_192226860.jpg /uploadfile/dongtai_ic/20200325_192226351.jpg','2020-03-25 19:22:26',0,0,'最帅的我'),(55,1,'我是一种态度',NULL,'2020-03-25 19:30:17',0,0,'最帅的我'),(56,1,'试一下9张图','/uploadfile/dongtai_ic/20200325_193120680.png /uploadfile/dongtai_ic/20200325_193120661.png /uploadfile/dongtai_ic/20200325_193120682.png /uploadfile/dongtai_ic/20200325_193120833.jpg /uploadfile/dongtai_ic/20200325_193120154.png /uploadfile/dongtai_ic/20200325_193121655.jpg /uploadfile/dongtai_ic/20200325_193121326.jpg /uploadfile/dongtai_ic/20200325_193121777.jpg /uploadfile/dongtai_ic/20200325_193121858.jpg','2020-03-25 19:31:21',0,0,'最帅的我'),(57,1,'北京','/uploadfile/dongtai_ic/20200326_161214310.jpg /uploadfile/dongtai_ic/20200326_161214241.jpg /uploadfile/dongtai_ic/20200326_161215622.jpg /uploadfile/dongtai_ic/20200326_161215993.jpg','2020-03-26 16:12:16',0,0,'最帅的我'),(58,1,'。。。','/uploadfile/dongtai_ic/20200326_161248930.jpg /uploadfile/dongtai_ic/20200326_161249291.jpg /uploadfile/dongtai_ic/20200326_161249462.jpg /uploadfile/dongtai_ic/20200326_161249573.jpg /uploadfile/dongtai_ic/20200326_161250104.jpg /uploadfile/dongtai_ic/20200326_161250165.jpg','2020-03-26 16:12:50',0,0,'最帅的我'),(59,1,'写信告诉我。','/uploadfile/dongtai_ic/20200326_16220930.jpg /uploadfile/dongtai_ic/20200326_162209511.jpg /uploadfile/dongtai_ic/20200326_162209712.jpg','2020-03-26 16:22:10',0,0,'最帅的我'),(60,1,NULL,'/uploadfile/dongtai_ic/20200326_162420920.jpg /uploadfile/dongtai_ic/20200326_162421541.jpg /uploadfile/dongtai_ic/20200326_162421782.jpg','2020-03-26 16:24:21',0,0,'最帅的我'),(61,1,'吃鸡','/uploadfile/dongtai_ic/20200326_162708930.jpg /uploadfile/dongtai_ic/20200326_16270921.jpg /uploadfile/dongtai_ic/20200326_162709792.jpg /uploadfile/dongtai_ic/20200326_162709163.jpg','2020-03-26 16:27:09',0,0,'最帅的我'),(62,1,'哎','/uploadfile/dongtai_ic/20200326_16322100.jpg /uploadfile/dongtai_ic/20200326_163221421.png /uploadfile/dongtai_ic/20200326_163221842.jpg','2020-03-26 16:32:21',0,0,'最帅的我'),(63,1,'One','/uploadfile/dongtai_ic/20200326_164449390.jpg /uploadfile/dongtai_ic/20200326_164449591.jpg /uploadfile/dongtai_ic/20200326_164449822.jpg','2020-03-26 16:44:49',0,0,'最帅的我'),(64,1,'Two','/uploadfile/dongtai_ic/20200326_164507760.png /uploadfile/dongtai_ic/20200326_164507581.png','2020-03-26 16:45:07',0,0,'最帅的我'),(65,1,'Trying','/uploadfile/dongtai_ic/20200326_164628850.jpeg /uploadfile/dongtai_ic/20200326_164628231.jpeg /uploadfile/dongtai_ic/20200326_164628682.jpeg /uploadfile/dongtai_ic/20200326_164629313.png /uploadfile/dongtai_ic/20200326_164629184.jpeg','2020-03-26 16:46:29',0,0,'最帅的我'),(66,1,'哈哈哈','/uploadfile/dongtai_ic/20200326_164702280.jpg /uploadfile/dongtai_ic/20200326_164702351.jpg /uploadfile/dongtai_ic/20200326_164702702.jpg','2020-03-26 16:47:02',0,0,'最帅的我'),(67,1,'嘿嘿','/uploadfile/dongtai_ic/20200326_164724850.jpg /uploadfile/dongtai_ic/20200326_164724151.jpg','2020-03-26 16:47:24',0,0,'最帅的我'),(68,1,'试一下','/uploadfile/dongtai_ic/20200326_164804230.jpg /uploadfile/dongtai_ic/20200326_164804631.png','2020-03-26 16:48:04',0,0,'最帅的我'),(69,1,NULL,'/uploadfile/dongtai_ic/20200326_164923630.jpg /uploadfile/dongtai_ic/20200326_164923521.jpg','2020-03-26 16:49:23',0,0,'最帅的我'),(70,1,'呜呜','/uploadfile/dongtai_ic/20200326_164959180.jpg /uploadfile/dongtai_ic/20200326_165000211.jpg /uploadfile/dongtai_ic/20200326_165000152.jpg','2020-03-26 16:50:00',0,0,'最帅的我'),(71,1,'最后一次测试','/uploadfile/dongtai_ic/20200326_185153770.jpg /uploadfile/dongtai_ic/20200326_185154771.jpg /uploadfile/dongtai_ic/20200326_185155172.jpg /uploadfile/dongtai_ic/20200326_185156153.jpg /uploadfile/dongtai_ic/20200326_185157624.jpg /uploadfile/dongtai_ic/20200326_185157265.jpg /uploadfile/dongtai_ic/20200326_185157586.jpg /uploadfile/dongtai_ic/20200326_185157997.jpg /uploadfile/dongtai_ic/20200326_185158848.jpg','2020-03-26 18:51:58',0,0,'YAL-AL00');

/*Table structure for table `friendmsg` */

DROP TABLE IF EXISTS `friendmsg`;

CREATE TABLE `friendmsg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL COMMENT '发送者',
  `touserid` bigint(20) DEFAULT NULL COMMENT '接收者',
  `msgtype` tinyint(4) DEFAULT NULL COMMENT '消息类型 1代表关注 2代表取消关注',
  `info` varchar(200) DEFAULT NULL COMMENT '附带消息',
  `readstate` tinyint(4) DEFAULT '0' COMMENT '0代表未读  1代表以读 2代表过期消息',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `friendmsg` */

insert  into `friendmsg`(`id`,`userid`,`touserid`,`msgtype`,`info`,`readstate`,`createtime`) values (27,1,4,2,NULL,2,'2020-03-30 12:40:30'),(28,1,4,1,NULL,2,'2020-03-30 12:54:34'),(29,1,4,2,NULL,2,'2020-03-30 12:54:47'),(30,1,4,1,NULL,2,'2020-03-30 13:25:33'),(31,1,4,1,NULL,2,'2020-03-30 13:28:17'),(32,1,4,2,NULL,0,'2020-03-30 13:28:30');

/*Table structure for table `guanzhu` */

DROP TABLE IF EXISTS `guanzhu`;

CREATE TABLE `guanzhu` (
  `gzid` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL COMMENT '发起者',
  `gzuid` bigint(20) DEFAULT NULL COMMENT '被关注者',
  `remarkname` varchar(20) DEFAULT NULL COMMENT '备注名字',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除  取消关注',
  `is_blacked` tinyint(4) DEFAULT '0' COMMENT '加入黑名单',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`gzid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `guanzhu` */

insert  into `guanzhu`(`gzid`,`userid`,`gzuid`,`remarkname`,`is_deleted`,`is_blacked`,`createtime`) values (5,2,1,'',0,0,'2020-03-26 20:34:39'),(6,1,2,'女朋友',0,0,'2020-03-26 20:43:06'),(9,4,1,'帅气',0,0,'2020-03-26 21:52:25'),(12,1,4,'狗蛋',1,0,'2020-03-29 18:43:36'),(15,3,1,NULL,0,0,'2020-03-29 19:07:28'),(16,1,3,'傻瓜',0,0,'2020-03-29 22:52:15');

/*Table structure for table `singlechatmsg` */

DROP TABLE IF EXISTS `singlechatmsg`;

CREATE TABLE `singlechatmsg` (
  `msgid` bigint(20) NOT NULL AUTO_INCREMENT,
  `sendUid` bigint(20) DEFAULT NULL,
  `receiveUid` bigint(20) DEFAULT NULL,
  `msgtype` tinyint(4) DEFAULT NULL COMMENT '2:文字  3:图片 4:语音',
  `textbody` varchar(300) DEFAULT NULL,
  `picbody` varchar(300) DEFAULT NULL,
  `voicebody` varchar(300) DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `readstate` tinyint(4) DEFAULT '0' COMMENT '读取状态',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`msgid`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

/*Data for the table `singlechatmsg` */

insert  into `singlechatmsg`(`msgid`,`sendUid`,`receiveUid`,`msgtype`,`textbody`,`picbody`,`voicebody`,`createtime`,`updatetime`,`readstate`,`is_deleted`) values (24,3,1,2,'你好啊',NULL,NULL,'2020-04-20 15:04:30','2020-04-20 15:04:30',1,0),(25,3,1,2,'在吗',NULL,NULL,'2020-04-20 15:04:17','2020-04-20 15:04:17',1,0),(26,3,1,2,'。。。',NULL,NULL,'2020-04-20 15:04:22','2020-04-20 15:04:22',1,0),(27,3,1,2,'。。',NULL,NULL,'2020-04-20 15:04:51','2020-04-20 15:04:51',1,0),(28,3,1,2,'在吗',NULL,NULL,'2020-04-20 17:04:32','2020-04-20 17:04:32',1,0),(29,3,1,2,'你好',NULL,NULL,'2020-04-20 17:04:27','2020-04-20 17:04:27',1,0),(30,3,1,2,'哈哈',NULL,NULL,'2020-04-20 17:04:37','2020-04-20 17:04:37',1,0),(31,3,1,2,'呵呵',NULL,NULL,'2020-04-20 17:04:45','2020-04-20 17:04:45',1,0),(32,3,1,2,'在吗',NULL,NULL,'2020-04-20 17:04:50','2020-04-20 17:04:50',1,0),(33,3,1,2,'你现在',NULL,NULL,'2020-04-20 17:04:33','2020-04-20 17:04:33',1,0),(34,3,1,2,'图',NULL,NULL,'2020-04-20 17:04:59','2020-04-20 17:04:59',1,0),(35,3,1,2,'噢噢',NULL,NULL,'2020-04-20 17:04:16','2020-04-20 17:04:16',1,0),(36,1,3,2,'你好',NULL,NULL,'2020-04-20 17:04:12','2020-04-20 17:04:12',1,0),(37,3,1,2,'你好哦',NULL,NULL,'2020-04-20 17:04:12','2020-04-20 17:04:12',1,0),(38,3,1,2,'你好',NULL,NULL,'2020-04-20 17:04:27','2020-04-20 17:04:27',1,0),(39,3,1,2,'距离',NULL,NULL,'2020-04-20 17:04:44','2020-04-20 17:04:44',1,0),(40,3,1,2,'啦啦',NULL,NULL,'2020-04-20 17:04:01','2020-04-20 17:04:01',1,0),(41,3,1,2,'图',NULL,NULL,'2020-04-20 17:04:33','2020-04-20 17:04:33',1,0),(42,3,1,2,'图片',NULL,NULL,'2020-04-20 17:04:40','2020-04-20 17:04:40',1,0),(43,3,1,2,'兔兔',NULL,NULL,'2020-04-20 17:04:05','2020-04-20 17:04:05',1,0),(44,3,1,2,'你好哦',NULL,NULL,'2020-04-20 18:04:45','2020-04-20 18:04:45',1,0),(45,3,1,2,'你好',NULL,NULL,'2020-04-20 18:04:53','2020-04-20 18:04:53',1,0),(46,3,1,2,'在干嘛呢',NULL,NULL,'2020-04-20 18:04:47','2020-04-20 18:04:47',1,0),(47,3,1,2,'。。。',NULL,NULL,'2020-04-20 18:04:01','2020-04-20 18:04:01',1,0),(48,3,1,2,'兔兔',NULL,NULL,'2020-04-20 18:04:30','2020-04-20 18:04:30',1,0),(49,3,1,2,'哈哈',NULL,NULL,'2020-04-20 18:04:35','2020-04-20 18:04:35',1,0),(50,3,1,2,'你好',NULL,NULL,'2020-04-20 18:04:39','2020-04-20 18:04:39',1,0),(51,3,1,2,'哈哈',NULL,NULL,'2020-04-20 18:04:51','2020-04-20 18:04:51',1,0),(52,3,1,2,'你好',NULL,NULL,'2020-04-20 18:04:26','2020-04-20 18:04:26',1,0),(53,3,1,2,'在吗',NULL,NULL,'2020-04-20 18:04:29','2020-04-20 18:04:29',1,0),(54,3,1,2,'我是哦呵呵墨迹看看啦啦啦图图兔兔图图图图图图图',NULL,NULL,'2020-04-20 18:04:40','2020-04-20 18:04:40',1,0),(55,3,1,2,'看看桌子上',NULL,NULL,'2020-04-20 18:04:38','2020-04-20 18:04:38',1,0),(56,3,1,2,'尤其是在入股归途呼呼呼呼来咯啦啦啦啦快快乐乐你',NULL,NULL,'2020-04-20 18:04:47','2020-04-20 18:04:47',1,0),(57,3,1,2,'哦哦哦我也一直在兔兔兔兔兔兔兔兔',NULL,NULL,'2020-04-20 18:04:28','2020-04-20 18:04:28',1,0),(58,3,1,2,'路虎体贴咕噜噜图图兔兔图吞吞吐吐图图图图图我现在营业厅图图图书馆',NULL,NULL,'2020-04-20 19:04:36','2020-04-20 19:04:36',1,0),(59,3,1,2,'我现在啧啧啧我现在我组图兔兔龙龙自作自受是7他又',NULL,NULL,'2020-04-20 19:04:49','2020-04-20 19:04:49',1,0),(60,3,1,2,'啦啦',NULL,NULL,'2020-04-20 19:04:51','2020-04-20 19:04:51',1,0),(61,3,1,2,'图谱哦哦哦啦啦啦路图图兔兔图图兔子我屋头图图',NULL,NULL,'2020-04-20 19:04:00','2020-04-20 19:04:00',1,0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号  登录用',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个人简介',
  `location` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在地',
  `hometown` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '故乡',
  `job` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职业',
  `is_deleted` tinyint(4) DEFAULT '0',
  `is_locked` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userid`,`phone`,`username`,`password`,`sex`,`icon`,`birthday`,`introduce`,`location`,`hometown`,`job`,`is_deleted`,`is_locked`) values (1,'15054190193','小蓝','202cb962ac59075b964b07152d234b70',1,'/uploadfile/headic/20200326_18233623.jpg','1997-11-13 00:00:00','我是一种态度','济南','','it民工',0,0),(2,'15054190194','菲菲','e10adc3949ba59abbe56e057f20f883e',0,'/uploadfile/headic/20200326_18233623.jpg',NULL,NULL,NULL,NULL,NULL,0,0),(3,'15054190195','莎士比亚','202cb962ac59075b964b07152d234b70',1,'/uploadfile/headic/20200326_18233623.jpg',NULL,'民运',NULL,NULL,NULL,0,0),(4,NULL,'苏俄','202cb962ac59075b964b07152d234b70',0,'/uploadfile/headic/20200326_18233623.jpg',NULL,'爱你',NULL,NULL,NULL,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
