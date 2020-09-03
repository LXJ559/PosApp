/*
MySQL Backup
Database: pos
Backup Time: 2020-08-27 11:44:56
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `pos`.`goods`;
DROP TABLE IF EXISTS `pos`.`message`;
DROP TABLE IF EXISTS `pos`.`order_table`;
DROP TABLE IF EXISTS `pos`.`user`;
CREATE TABLE `goods` (
  `id` varchar(100) NOT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_count` int(11) DEFAULT NULL,
  `price` decimal(50,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `message` (
  `id` varchar(100) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `order_table` (
  `id` varchar(100) NOT NULL,
  `userId` varchar(100) DEFAULT NULL,
  `goodsId` varchar(100) DEFAULT NULL,
  `count` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `pos`.`goods` WRITE;
DELETE FROM `pos`.`goods`;
INSERT INTO `pos`.`goods` (`id`,`goods_id`,`goods_name`,`goods_count`,`price`) VALUES ('0dc7e3237a224b228f32e25a7a3cbeb7', 18, '水杯', 15000, 35),('0e05067fed8047468038766e810fc401', 12, '花生', 300, 21),('2182cfa38469467d9ed32cf055ca0f70', 20, '阿萨姆', 1000, 4),('422a278d34cb4a7f8f13c839354d7cf8', 16, '手机', 1234, 2999),('489f9e25c7984985b42cd308cf6f467a', 2, '田园鸡腿堡', 6000, 20),('48a4574178e8495f86eb603d33651c48', 4, '快乐全家桶', 2000, 80),('4c4a4f82b8754d7b826c6cc6773baf1d', 13, '玉米', 2090, 2),('5108082c0ec549e0ac8fb39930e6fa2c', 8, '雪顶咖啡', 1000, 18),('51805d203a604e6bb32ecfac8d370099', 17, '风扇', 10000, 299),('6b1e39f85a9e4ffda09695a4d7b75c0b', 15, '电脑', 1234, 3500),('7fc611de6c4e4ffd867906fb03293812', 1, '香辣鸡腿堡', 50002, 19),('8af58a8b966543bd823663e2832b2098', 5, '脆皮炸鸡腿', 2000, 10),('8be4a40c93af4bd3a4507ca1d900a5ba', 10, '香脆鸡柳', 1000, 17),('94553df307a6454caab5c94445ca677a', 21, '电饭煲', 15000, 99),('a1fb128fbabc41c8b9a2e8620590eff1', 19, '鼠标', 15000, 199),('b4424e533c434596a5595099ce4bcc3d', 14, '黄油', 1234, 23),('b6c607a0053c4b1fb1af8c5eebbcd2cc', 9, '大块鸡米花', 1000, 15),('cd828bf314a54f9b85f3e6d0a1616dfa', 3, '和风汉堡', 2000, 15),('e32d6d6968a940d3b687bcc5feb0944f', 7, '可乐大杯', 1000, 10),('f1c8b8284d5349438a367488354c4a76', 6, '魔法鸡块', 1000, 20),('f3fb63507747403091c51bb467eba633', 11, '冰淇淋', 42000, 12);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `pos`.`message` WRITE;
DELETE FROM `pos`.`message`;
INSERT INTO `pos`.`message` (`id`,`title`,`type`,`content`) VALUES ('13ec257e6d744ccbb979c5e8965143c9', '公告', '广播', '更新系统'),('8e7893d06a3848f08f0fac12bd816d47', '测试', '广播', '系统测试'),('a5ceaa4f48d449098dbdae87f348d341', '公告', '广播', '系统测试');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `pos`.`order_table` WRITE;
DELETE FROM `pos`.`order_table`;
INSERT INTO `pos`.`order_table` (`id`,`userId`,`goodsId`,`count`) VALUES ('02502155df544adaa69edf9ffa4f5d62', 'b4b6cf46c6d74bb9b474865860fcd6ea', 'b4424e533c434596a5595099ce4bcc3d', 2),('38fe44dedfb243c8a6f83f09b226c85d', 'b4b6cf46c6d74bb9b474865860fcd6ea', '7fc611de6c4e4ffd867906fb03293812', 5),('44d2d05e905347cba9ea42ff67befd8e', 'b4b6cf46c6d74bb9b474865860fcd6ea', '48a4574178e8495f86eb603d33651c48', 1),('5a6d809996294f19a295d1754d5be12e', 'b4b6cf46c6d74bb9b474865860fcd6ea', 'b6c607a0053c4b1fb1af8c5eebbcd2cc', 1),('8861330fd8684e37aa7456e81dcb96c0', 'b4b6cf46c6d74bb9b474865860fcd6ea', '0dc7e3237a224b228f32e25a7a3cbeb7', 3),('a231a586e5a54232a932a35389a6fbf5', 'b4b6cf46c6d74bb9b474865860fcd6ea', '4c4a4f82b8754d7b826c6cc6773baf1d', 2),('a9ab4713be784ddabc10ddbb515db368', 'b4b6cf46c6d74bb9b474865860fcd6ea', '2182cfa38469467d9ed32cf055ca0f70', 1),('ab10e475fc364e728106a9445a61968a', 'b4b6cf46c6d74bb9b474865860fcd6ea', '8af58a8b966543bd823663e2832b2098', 1),('f208336a1e0b463e9bf52e189b631073', 'b4b6cf46c6d74bb9b474865860fcd6ea', 'f3fb63507747403091c51bb467eba633', 1),('f67708cf29e149a0bd29b32bf363d19b', 'b4b6cf46c6d74bb9b474865860fcd6ea', '0e05067fed8047468038766e810fc401', 2),('f7b2e7e668c3421d92c39fe4626f416d', 'b4b6cf46c6d74bb9b474865860fcd6ea', '5108082c0ec549e0ac8fb39930e6fa2c', 2);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `pos`.`user` WRITE;
DELETE FROM `pos`.`user`;
INSERT INTO `pos`.`user` (`id`,`username`,`password`) VALUES ('b4b6cf46c6d74bb9b474865860fcd6ea', 'ming', '25d55ad283aa400af464c76d713c07ad');
UNLOCK TABLES;
COMMIT;
