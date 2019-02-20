/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : njujlc_libseat

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2014-11-02 17:01:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for reserve_rule
-- ----------------------------
DROP TABLE IF EXISTS `reserve_rule`;
CREATE TABLE `reserve_rule` (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则编号',
  `reserve_start_free_time` bigint(20) NOT NULL COMMENT '预约开始无人入座保留时间',
  `reserve_end_free_time` bigint(20) NOT NULL COMMENT '预约结束未续订保留时间',
  `reserve_max_days` int(11) NOT NULL COMMENT '最大可预约天数，0.今天，1.明天',
  `leave_max_time` int(11) NOT NULL COMMENT '最大暂离时间',
  `leave_refill_max_count` int(11) NOT NULL COMMENT '暂离可延续次数',
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reserve_rule
-- ----------------------------

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '阅览室编号',
  `room_name` varchar(255) NOT NULL COMMENT '阅览室名称',
  `room_floor` int(11) NOT NULL COMMENT '阅览室楼层，-1，1，2，3',
  `room_intro` varchar(255) DEFAULT NULL COMMENT '阅览室简介',
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '座位编号',
  `seat_room_id` bigint(20) NOT NULL COMMENT '座位所在阅览室编号',
  `seat_locate_x` int(11) NOT NULL COMMENT '按上北下南左西右东布局，二维矩阵的x轴，从1，1开始',
  `seat_locate_y` int(11) NOT NULL,
  `seat_alias` varchar(255) NOT NULL COMMENT '座位的代号（AXX）',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seat
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_fromusername` varchar(255) DEFAULT NULL COMMENT '微信传值来的用户唯一标识',
  `user_stu_id` int(11) DEFAULT NULL COMMENT '学号',
  `user_reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `user_nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `user_pwd` varchar(255) DEFAULT 
  NULL COMMENT '用户密码，应该是记住图书馆的密码',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_reserve
-- ----------------------------
DROP TABLE IF EXISTS `user_reserve`;
CREATE TABLE `user_reserve` (
  `reserve_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预定编号，只有取消预定会删除记录',
  `uid` bigint(20) NOT NULL COMMENT '用户编号',
  `sid` bigint(20) NOT NULL COMMENT '座位编号',
  `start_time` datetime DEFAULT NULL COMMENT '预定开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '预定结束时间',
  `return_time` datetime DEFAULT NULL COMMENT '暂离时长',
  `refill_count_left` int(11) NOT NULL COMMENT '暂离续约可用次数',
  `reserve_flag` int(11) NOT NULL COMMENT '预约状态：-1.预约结束，0.已预约，1.已入座，2.暂离中，注：取消预定只用于状态为0',
  PRIMARY KEY (`reserve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_reserve
-- ----------------------------






CREATE TABLE upload(
	id INT UNSIGNED AUTO_INCREMENT COMMENT '上传关系表id',
	upload_user_id VARCHAR(255) DEFAULT NULL COMMENT '上传用户id',
	upload_city_id VARCHAR(255)
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
