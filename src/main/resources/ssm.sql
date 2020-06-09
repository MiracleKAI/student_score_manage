/*
 Navicat Premium Data Transfer

 Source Server         : quiz
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : ssm

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 09/06/2020 15:47:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '网工4班');
INSERT INTO `class` VALUES (2, '网工3班');
INSERT INTO `class` VALUES (3, '网工2班');
INSERT INTO `class` VALUES (4, '网工1班');

-- ----------------------------
-- Table structure for class2teacher
-- ----------------------------
DROP TABLE IF EXISTS `class2teacher`;
CREATE TABLE `class2teacher`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` bigint(20) NULL DEFAULT NULL,
  `teacher_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class2teacher
-- ----------------------------
INSERT INTO `class2teacher` VALUES (1, 1, 1);
INSERT INTO `class2teacher` VALUES (2, 1, 2);
INSERT INTO `class2teacher` VALUES (3, 2, 1);
INSERT INTO `class2teacher` VALUES (4, 4, 1);
INSERT INTO `class2teacher` VALUES (5, 4, 3);
INSERT INTO `class2teacher` VALUES (6, 3, 3);
INSERT INTO `class2teacher` VALUES (7, 3, 2);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `teacher_id` bigint(20) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'daodejing', '道德经', 2, '很道德的一本书');
INSERT INTO `course` VALUES (2, 'lunyu', '论语', 1, '不是我写的');
INSERT INTO `course` VALUES (3, 'chunqiu', '春秋', 1, '也不是我写的');
INSERT INTO `course` VALUES (4, 'sunz', '孙子', 3, '我就是孙子');

-- ----------------------------
-- Table structure for course_student
-- ----------------------------
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) NULL DEFAULT NULL,
  `final_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `normal_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `student_id` bigint(20) NULL DEFAULT NULL,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `flag` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_student
-- ----------------------------
INSERT INTO `course_student` VALUES (1, 1, '9', '100', 1, '网工4班', NULL, 0);
INSERT INTO `course_student` VALUES (2, 1, '99', '10', 2, '网工4班', NULL, 0);
INSERT INTO `course_student` VALUES (4, 1, NULL, NULL, 4, '网工4班', NULL, NULL);
INSERT INTO `course_student` VALUES (5, 2, NULL, NULL, 5, '网工3班', NULL, NULL);
INSERT INTO `course_student` VALUES (6, 2, NULL, NULL, 6, '网工3班', NULL, NULL);
INSERT INTO `course_student` VALUES (7, 2, NULL, NULL, 7, '网工3班', NULL, NULL);
INSERT INTO `course_student` VALUES (8, 2, NULL, NULL, 1, '网工4班', NULL, NULL);
INSERT INTO `course_student` VALUES (9, 2, NULL, NULL, 2, '网工4班', NULL, NULL);
INSERT INTO `course_student` VALUES (10, 3, NULL, NULL, 1, '网工4班', NULL, NULL);
INSERT INTO `course_student` VALUES (11, 3, NULL, NULL, 8, '网工2班', NULL, NULL);
INSERT INTO `course_student` VALUES (12, 3, NULL, NULL, 9, '网工2班', NULL, NULL);
INSERT INTO `course_student` VALUES (13, 3, NULL, NULL, 5, '网工3班', NULL, NULL);
INSERT INTO `course_student` VALUES (14, 4, NULL, NULL, 1, '网工4班', NULL, NULL);
INSERT INTO `course_student` VALUES (15, 4, NULL, NULL, 5, '网工3班', NULL, NULL);
INSERT INTO `course_student` VALUES (16, 4, NULL, NULL, 8, '网工2班', NULL, NULL);
INSERT INTO `course_student` VALUES (17, 4, NULL, NULL, 10, '网工1班', NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `faculty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '网工4班', '邱凯', NULL, '12345678', 'B17070420', '物联网', '男');
INSERT INTO `student` VALUES (2, '网工4班', '李朋阳', NULL, '12345678', 'B17070417', '物联网', '男');
INSERT INTO `student` VALUES (3, '网工4班', '卢志强', NULL, '12345678', 'B17040415', '物联网', '男');
INSERT INTO `student` VALUES (4, '网工4班', '陈耿岳', NULL, '12345678', 'B17070423', '物联网', '男');
INSERT INTO `student` VALUES (5, '网工3班', '庄子', NULL, '12345678', 'B17070301', '物联网', '男');
INSERT INTO `student` VALUES (6, '网工3班', '墨子', NULL, '12345678', 'B17070302', '物联网', '男');
INSERT INTO `student` VALUES (7, '网工3班', '李白', NULL, '12345678', 'B17070303', '物联网', '男');
INSERT INTO `student` VALUES (8, '网工2班', '妲己', NULL, '12345678', 'B17070201', '物联网', '女');
INSERT INTO `student` VALUES (9, '网工2班', '小乔', NULL, '12345678', 'B17070202', '物联网', '女');
INSERT INTO `student` VALUES (10, '网工1班', '猴子', NULL, '12345678', 'B17070101', '物联网', '男');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `teacher_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `faculty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '孔子', NULL, '12345678', 'kongz123', '物联网学院', '男');
INSERT INTO `teacher` VALUES (2, '老子', NULL, '12345678', 'laoz123', '物联网学院', '男');
INSERT INTO `teacher` VALUES (3, '孙子', NULL, '12345678', 'sunz123', '物联网学院', '男');

SET FOREIGN_KEY_CHECKS = 1;
