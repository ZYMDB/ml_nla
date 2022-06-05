/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : ml_nla

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 05/06/2022 21:19:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action`  (
  `action_id` int NOT NULL AUTO_INCREMENT,
  `action_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `action_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `action_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `action_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`action_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for con
-- ----------------------------
DROP TABLE IF EXISTS `con`;
CREATE TABLE `con`  (
  `con_id` int NOT NULL AUTO_INCREMENT,
  `con_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `con_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `con_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`con_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for decon_group
-- ----------------------------
DROP TABLE IF EXISTS `decon_group`;
CREATE TABLE `decon_group`  (
  `dec_id` int NOT NULL AUTO_INCREMENT,
  `dec_group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dec_gropu_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dec_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dec_modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dec_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for env
-- ----------------------------
DROP TABLE IF EXISTS `env`;
CREATE TABLE `env`  (
  `env_id` int NOT NULL AUTO_INCREMENT,
  `env_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `env_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `env_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`env_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for giver
-- ----------------------------
DROP TABLE IF EXISTS `giver`;
CREATE TABLE `giver`  (
  `giv_id` int NOT NULL AUTO_INCREMENT,
  `giv_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `giv_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `giv_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`giv_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rec
-- ----------------------------
DROP TABLE IF EXISTS `rec`;
CREATE TABLE `rec`  (
  `rec_id` int NOT NULL AUTO_INCREMENT,
  `rec_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rec_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `rec_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`rec_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for res
-- ----------------------------
DROP TABLE IF EXISTS `res`;
CREATE TABLE `res`  (
  `res_id` int NOT NULL AUTO_INCREMENT,
  `res_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `res_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `res_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`res_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rp
-- ----------------------------
DROP TABLE IF EXISTS `rp`;
CREATE TABLE `rp`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `old_str` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `new_str` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rp_regex
-- ----------------------------
DROP TABLE IF EXISTS `rp_regex`;
CREATE TABLE `rp_regex`  (
  `mode_code` int NOT NULL AUTO_INCREMENT,
  `regex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regex_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mode_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for temp
-- ----------------------------
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp`  (
  `temp_id` int NOT NULL AUTO_INCREMENT,
  `temp_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `temp_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `tenp_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `temp_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`temp_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tool
-- ----------------------------
DROP TABLE IF EXISTS `tool`;
CREATE TABLE `tool`  (
  `tool_id` int NOT NULL AUTO_INCREMENT,
  `tool_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tool_des` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `tool_relation_objs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tool_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
