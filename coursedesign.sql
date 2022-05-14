SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 教师
-- ----------------------------
DROP TABLE IF EXISTS `教师`;
CREATE TABLE `教师`  (
  `工资号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `姓名` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `密码` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`工资号`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 教师
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 题库
-- ----------------------------
DROP TABLE IF EXISTS `题库`;
CREATE TABLE `题库`  (
  `题号` int(0) NOT NULL,
  `题目` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`题号`,`题目`),
  INDEX `题号`(`题号`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 题库
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 学生
-- ----------------------------
DROP TABLE IF EXISTS `学生`;
CREATE TABLE `学生`  (
  `学号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `姓名` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `班级` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `题号` int(0) NULL DEFAULT NULL,
  `密码` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`学号`) USING BTREE,
  INDEX `姓名`(`姓名`) USING BTREE,
  INDEX `题号2`(`题号`) USING BTREE,
  CONSTRAINT `题号2` FOREIGN KEY (`题号`) REFERENCES `题库` (`题号`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 学生
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 课程学生用
-- ----------------------------
DROP TABLE IF EXISTS `课程学生用`;
CREATE TABLE `课程学生用`  (
  `题号` int(0) NOT NULL,
  `小组长` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `工作报告` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `进度情况` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `分数` double NULL DEFAULT NULL,
  PRIMARY KEY (`题号`) USING BTREE,
  INDEX `组长`(`小组长`) USING BTREE,
  CONSTRAINT `题号` FOREIGN KEY (`题号`) REFERENCES `题库` (`题号`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `组长` FOREIGN KEY (`小组长`) REFERENCES `学生` (`姓名`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 课程学生用
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 资料
-- ----------------------------
DROP TABLE IF EXISTS `资料`;
CREATE TABLE `资料`  (
  `题号` int(0) NOT NULL,
  `学习资料` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `下载地址` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 资料
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
