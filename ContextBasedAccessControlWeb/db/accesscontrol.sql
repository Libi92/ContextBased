/*
SQLyog Ultimate v8.55 
MySQL - 5.0.15-nt : Database - accesscontrol
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

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `PERMISSIONID` bigint(20) NOT NULL auto_increment,
  `USERNAME` varchar(256) default NULL,
  `ROOMNAME` varchar(1024) default NULL,
  `MICROPHONE` varchar(256) default NULL,
  `CAMERA` varchar(256) default NULL,
  `BLUETOOTH` varchar(256) default NULL,
  `TIME` varchar(1024) default NULL,
  PRIMARY KEY  (`PERMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `permissions` */

insert  into `permissions`(`PERMISSIONID`,`USERNAME`,`ROOMNAME`,`MICROPHONE`,`CAMERA`,`BLUETOOTH`,`TIME`) values (1,'Santhosh','Conference Hall','silent','disabled','enabled','15:00-16:00');

/*Table structure for table `rooms` */

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `ROOMID` bigint(20) NOT NULL auto_increment,
  `ROOMNAME` varchar(1024) default NULL,
  `WIFINAME` varchar(1024) default NULL,
  PRIMARY KEY  (`ROOMID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rooms` */

insert  into `rooms`(`ROOMID`,`ROOMNAME`,`WIFINAME`) values (1,'Conference Hall','DLinkD');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `USERID` bigint(20) NOT NULL auto_increment,
  `USERNAME` varchar(256) default NULL,
  `PASSWORD` varchar(256) default NULL,
  `EMAILID` varchar(256) default NULL,
  `PHONE` varchar(256) default NULL,
  `ADDRESS` varchar(256) default NULL,
  `USERTYPE` varchar(256) default NULL,
  `APPROVAL` varchar(256) default NULL,
  PRIMARY KEY  (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`USERID`,`USERNAME`,`PASSWORD`,`EMAILID`,`PHONE`,`ADDRESS`,`USERTYPE`,`APPROVAL`) values (3,'admin','admin','santhosh1982.r@gmail.com','8281978227','kerala/cochin/India','admin','approved'),(5,'Santhosh','redhat123','santhosh1982.r@gmail.com','8281978227','test','manager','waiting');

/*Table structure for table `wifis` */

DROP TABLE IF EXISTS `wifis`;

CREATE TABLE `wifis` (
  `WIFIID` bigint(20) NOT NULL auto_increment,
  `WIFINAME` varchar(1024) default NULL,
  PRIMARY KEY  (`WIFIID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `wifis` */

insert  into `wifis`(`WIFIID`,`WIFINAME`) values (1,'DLinkD');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
