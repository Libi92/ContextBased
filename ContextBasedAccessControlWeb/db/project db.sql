/*
SQLyog Ultimate v8.55 
MySQL - 5.1.57-community : Database - accesscontrol
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`accesscontrol` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `accesscontrol`;

/*Table structure for table `events` */

DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
  `eventid` int(200) NOT NULL AUTO_INCREMENT,
  `event` varchar(256) DEFAULT NULL,
  `details` varchar(1024) DEFAULT NULL,
  `date` varchar(256) DEFAULT NULL,
  `time` varchar(256) DEFAULT NULL,
  `location` varchar(256) DEFAULT NULL,
  `latitude` varchar(256) DEFAULT NULL,
  `longitude` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`eventid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `events` */

insert  into `events`(`eventid`,`event`,`details`,`date`,`time`,`location`,`latitude`,`longitude`) values (1,'asdasd','asdas','2016-02-12','12:59','arakulam','9.802576013550228','76.84237003326416');

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `PERMISSIONID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(256) DEFAULT NULL,
  `ROOMNAME` varchar(1024) DEFAULT NULL,
  `MICROPHONE` varchar(256) DEFAULT NULL,
  `CAMERA` varchar(256) DEFAULT NULL,
  `BLUETOOTH` varchar(256) DEFAULT NULL,
  `TIME` varchar(1024) DEFAULT NULL,
  `LATITUDE` varchar(256) DEFAULT NULL,
  `LONGITUDE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`PERMISSIONID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `permissions` */

insert  into `permissions`(`PERMISSIONID`,`USERNAME`,`ROOMNAME`,`MICROPHONE`,`CAMERA`,`BLUETOOTH`,`TIME`,`LATITUDE`,`LONGITUDE`) values (1,'Santhosh','Conference Hall','silent','disabled','enabled','00:00-23:59',NULL,NULL),(2,'elby','Conference Hall','silent','disabled','enabled','00:00-23:59','9.996135','76.290576'),(3,'Abhishek','Room 113','silent','disabled','enabled','00:00-23:59',NULL,NULL),(4,'elby','Room 113','general','disabled','enabled','00:00-23:59',NULL,NULL),(5,'elby','room','silent','disabled','enabled','00:00-23:59',NULL,NULL),(6,'nikhil','room','silent','disabled','enabled','00:00-23:59',NULL,NULL);

/*Table structure for table `rooms` */

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `ROOMID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROOMNAME` varchar(1024) DEFAULT NULL,
  `WIFINAME` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ROOMID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `rooms` */

insert  into `rooms`(`ROOMID`,`ROOMNAME`,`WIFINAME`) values (1,'Conference Hall','DLinkD'),(2,'Room 113','MB9'),(3,'room','MB6');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `USERID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(256) DEFAULT NULL,
  `PASSWORD` varchar(256) DEFAULT NULL,
  `EMAILID` varchar(256) DEFAULT NULL,
  `PHONE` varchar(256) DEFAULT NULL,
  `ADDRESS` varchar(256) DEFAULT NULL,
  `USERTYPE` varchar(256) DEFAULT NULL,
  `APPROVAL` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`USERID`,`USERNAME`,`PASSWORD`,`EMAILID`,`PHONE`,`ADDRESS`,`USERTYPE`,`APPROVAL`) values (3,'admin','admin','santhosh1982.r@gmail.com','8281978227','kerala/cochin/India','admin','approved'),(5,'Santhosh','redhat123','santhosh1982.r@gmail.com','8281978227','test','manager','waiting'),(6,'elby','elby','elby123@gmail.com','2345678','sdfghj','employee','waiting'),(7,'Abhishek','12345','asfd','987456321','qwertyuiop','employee','waiting'),(8,'rahul','rahul','rahul@gmail.com','77777777777777','asdasd','employee','waiting'),(9,'nikhil','nikhil','nikA@gmail.com','55454545454','asdasd\r\n','employee','waiting');

/*Table structure for table `wifis` */

DROP TABLE IF EXISTS `wifis`;

CREATE TABLE `wifis` (
  `WIFIID` bigint(20) NOT NULL AUTO_INCREMENT,
  `WIFINAME` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`WIFIID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `wifis` */

insert  into `wifis`(`WIFIID`,`WIFINAME`) values (1,'DLinkD'),(2,'MB9'),(3,'MB6');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
