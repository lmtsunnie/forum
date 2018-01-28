/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : df

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-11-12 21:46:42
*/

drop database if exists df;
create database df;
use df;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `rid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `comment_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `rid` (`rid`),
  KEY `uid` (`uid`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `reply` (`rid`) ON DELETE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=1314 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

INSERT INTO `image` VALUES ('1', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3794862538,3454923183&fm=27&gp=0.jpg');
INSERT INTO `image` VALUES ('2', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2390205471,3993334695&fm=27&gp=0.jpg');
INSERT INTO `image` VALUES ('3', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4250899614,2225768721&fm=27&gp=0.jpg');
INSERT INTO `image` VALUES ('4', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=431996122,3492298550&fm=27&gp=0.jpg');
INSERT INTO `image` VALUES ('5', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2541759953,4097407455&fm=27&gp=0.jpg');
INSERT INTO `image` VALUES ('6', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1333245617,4026824155&fm=27&gp=0.jpg');

-- ----------------------------
-- Table structure for `info`
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `request_url` varchar(300) DEFAULT NULL,
  `context_path` varchar(30) DEFAULT NULL,
  `remote_addr` varchar(30) DEFAULT NULL,
  `access_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-24 20:45:45');
INSERT INTO `info` VALUES ('2', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-24 20:47:37');
INSERT INTO `info` VALUES ('3', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-24 21:26:04');
INSERT INTO `info` VALUES ('4', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-25 09:54:04');
INSERT INTO `info` VALUES ('5', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-25 09:54:52');
INSERT INTO `info` VALUES ('6', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-25 10:03:23');
INSERT INTO `info` VALUES ('7', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-25 10:18:38');
INSERT INTO `info` VALUES ('8', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-10-25 10:18:49');
INSERT INTO `info` VALUES ('9', 'http://localhost:8080/df/toIndex.do', '/df', '0:0:0:0:0:0:0:1', '2016-11-11 23:22:21');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `other_id` int(11) NOT NULL,
  `other_username` varchar(20) NOT NULL,
  `post_id` int(11) NOT NULL,
  `operation` varchar(20) NOT NULL,
  `displayed_content` varchar(100) NOT NULL,
  `msg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`),
  KEY `uid` (`uid`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `content` text,
  `publish_time` varchar(30) NOT NULL,
  `reply_time` varchar(30) NOT NULL,
  `reply_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `scan_count` int(11) DEFAULT '0',
  `uid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `uid` (`uid`),
  KEY `tid` (`tid`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `topic` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', 'test1', 'test content1', '2016-10-24 07:10:11', '2016-10-24 07:10:11', '0', '0', '0', '8', '10');
INSERT INTO `post` VALUES ('2', 'test2', 'test content2', '2016-10-24 07:10:13', '2016-10-24 07:10:13', '0', '0', '0', '4', '12');
INSERT INTO `post` VALUES ('3', 'test3', 'test content3', '2016-10-24 07:10:14', '2016-10-24 07:10:14', '0', '0', '0', '3', '4');
INSERT INTO `post` VALUES ('4', 'test4', 'test content4', '2016-10-24 07:10:15', '2016-10-24 07:10:15', '0', '0', '0', '4', '7');
INSERT INTO `post` VALUES ('5', 'test5', 'test content5', '2016-10-24 07:10:16', '2016-10-24 07:10:16', '0', '0', '1', '1', '10');
INSERT INTO `post` VALUES ('6', 'test6', 'test content6', '2016-10-24 07:10:17', '2016-10-24 07:10:17', '0', '0', '0', '9', '6');
INSERT INTO `post` VALUES ('7', 'test7', 'test content7', '2016-10-24 07:10:18', '2016-10-24 07:10:18', '0', '0', '0', '1', '6');
INSERT INTO `post` VALUES ('8', 'test8', 'test content8', '2016-10-24 07:10:19', '2016-10-24 07:10:19', '0', '0', '0', '9', '9');
INSERT INTO `post` VALUES ('9', 'test9', 'test content9', '2016-10-24 07:10:20', '2016-10-24 07:10:20', '0', '0', '0', '3', '10');
INSERT INTO `post` VALUES ('10', 'test10', 'test content10', '2016-10-24 07:10:21', '2016-10-24 07:10:21', '0', '0', '0', '1', '8');
INSERT INTO `post` VALUES ('11', 'test11', 'test content11', '2016-10-24 07:10:22', '2016-10-24 07:10:22', '0', '0', '0', '8', '7');
INSERT INTO `post` VALUES ('12', 'test12', 'test content12', '2016-10-24 07:10:23', '2016-10-24 07:10:23', '0', '0', '0', '9', '8');
INSERT INTO `post` VALUES ('13', 'test13', 'test content13', '2016-10-24 07:10:24', '2016-10-24 07:10:24', '0', '0', '0', '4', '4');
INSERT INTO `post` VALUES ('14', 'test14', 'test content14', '2016-10-24 07:10:25', '2016-10-24 07:10:25', '0', '0', '0', '8', '5');
INSERT INTO `post` VALUES ('15', 'test15', 'test content15', '2016-10-24 07:10:26', '2016-10-24 07:10:26', '0', '0', '0', '4', '3');
INSERT INTO `post` VALUES ('16', 'test16', 'test content16', '2016-10-24 07:10:28', '2016-10-24 07:10:28', '0', '0', '0', '4', '12');
INSERT INTO `post` VALUES ('17', 'test17', 'test content17', '2016-10-24 07:10:29', '2016-10-24 07:10:29', '0', '0', '0', '3', '8');
INSERT INTO `post` VALUES ('18', 'test18', 'test content18', '2016-10-24 07:10:30', '2016-10-24 07:10:30', '0', '0', '0', '7', '11');
INSERT INTO `post` VALUES ('19', 'test19', 'test content19', '2016-10-24 07:10:31', '2016-10-24 07:10:31', '0', '0', '0', '5', '6');
INSERT INTO `post` VALUES ('20', 'test20', 'test content20', '2016-10-24 07:10:32', '2016-10-24 07:10:32', '0', '0', '1', '2', '6');
INSERT INTO `post` VALUES ('21', 'a天气', '<p><img src=\"http://od6v5lenq.bkt.clouddn.com/22f3b086-01f5-4e3a-8f8b-77cc9d4cfa2d.jpg\" alt=\"a\" style=\"max-width:100%;\"><br></p><p><br></p>', '2016-10-25 10:10:17', '2016-10-25 10:10:17', '0', '0', '1', '1', '6');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `pid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `reply_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `pid` (`pid`),
  KEY `uid` (`uid`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `post` (`pid`) ON DELETE CASCADE,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `content` varchar(50) DEFAULT '',
  `image` varchar(100) DEFAULT '',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', 'Java', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('2', 'Python', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('3', '数据结构', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('4', '算法', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('5', '操作系统', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('6', '计算机网络', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('7', '数据库', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('8', '编译原理', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('9', '软件工程', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('10', 'JavaWeb', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('11', 'C', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');
INSERT INTO `topic` VALUES ('12', 'C++', '暂无描述', 'http://www.doublefuck.top/image/b.jpg');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `actived` int(11) NOT NULL,
  `activate_code` varchar(60) NOT NULL,
  `join_time` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `description` varchar(30) DEFAULT '',
  `head_url` varchar(100) NOT NULL,
  `position` varchar(20) DEFAULT '',
  `school` varchar(20) DEFAULT '',
  `job` varchar(20) DEFAULT '',
  `like_count` int(11) DEFAULT '0',
  `post_count` int(11) DEFAULT '0',
  `scan_count` int(11) DEFAULT '0',
  `follow_count` int(11) DEFAULT '0',
  `follower_count` int(11) DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1@1.com', '000000', '1', '14773075675632ad6199d5f3e4d908299ac20fc05c72c', '2016-10-24 07:10:47', 'DF2034号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '4', '0', '0', '0');
INSERT INTO `user` VALUES ('2', '2@2.com', '000000', '1', '14773075685817d1efc76a9384392997c07fba1081c45', '2016-10-24 07:10:48', 'DF7339号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '1', '0', '0', '0');
INSERT INTO `user` VALUES ('3', '3@3.com', '000000', '1', '14773075698454b2d54a9d4cc49369350ddb6b4dfdeda', '2016-10-24 07:10:49', 'DF3888号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '3', '2', '0', '0');
INSERT INTO `user` VALUES ('4', '4@4.com', '000000', '1', '1477307570872b3e42b049ca14c928b58b12d9875c21f', '2016-10-24 07:10:50', 'DF4957号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '5', '0', '0', '0');
INSERT INTO `user` VALUES ('5', '5@5.com', '000000', '1', '1477307571881b64840b082704c349af1d991997e5195', '2016-10-24 07:10:51', 'DF2276号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '1', '0', '0', '0');
INSERT INTO `user` VALUES ('6', '6@6.com', '000000', '1', '147730757289032677af172084dd3ab87899467e2d65b', '2016-10-24 07:10:52', 'DF7566号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '0', '0', '0', '0');
INSERT INTO `user` VALUES ('7', '7@7.com', '000000', '1', '14773075739073f418583d3784030ad92af942a6e5a9f', '2016-10-24 07:10:53', 'DF7150号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '1', '0', '0', '0');
INSERT INTO `user` VALUES ('8', '8@8.com', '000000', '1', '1477307574999c7b474240b414be2a56409a931ddf83e', '2016-10-24 07:10:55', 'DF8956号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '3', '0', '0', '0');
INSERT INTO `user` VALUES ('9', '9@9.com', '000000', '1', '147730757601867cab426b96943968352ea1e3ff76317', '2016-10-24 07:10:56', 'DF7084号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '3', '0', '0', '0');
INSERT INTO `user` VALUES ('10', '10@10.com', '000000', '1', '14773075770730fb573de1e4d4538945c7cad9d52300f', '2016-10-24 07:10:57', 'DF3524号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '0', '0', '0', '0');
INSERT INTO `user` VALUES ('12', '1104641801@qq.com', '111111', '0', '14773610739371293df921d6d46edb4459472a4c851c7', '2016-10-25 10:10:33', 'DF8405号', '', 'http://od6v5lenq.bkt.clouddn.com/head.jpg', '', '', '', '0', '0', '0', '0', '0');
