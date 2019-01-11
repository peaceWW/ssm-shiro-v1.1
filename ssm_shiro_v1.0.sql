/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : ssm_shiro_v1.0

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 13/12/2017 17:48:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `code` int(11) DEFAULT NULL COMMENT '部门code',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门name',
  `parantid` int(11) DEFAULT NULL COMMENT '父级部门id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 536 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, 1, '部门1', 0);
INSERT INTO `t_dept` VALUES (2, 2, '部门2', 0);
INSERT INTO `t_dept` VALUES (3, 3, '部门3', 0);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL COMMENT '资源 ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `menuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `permission` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源权限字符串',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源 url',
  `flag` int(11) DEFAULT NULL COMMENT '是否生成菜单,0:默认不生成菜单,1:生成菜单',
  `zindex` int(11) DEFAULT NULL COMMENT '菜单排序',
  `parantid` int(11) DEFAULT NULL COMMENT '父级菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 't_resource' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '系统管理', NULL, 'admin:*', '/admin/**', 0, NULL, NULL);
INSERT INTO `t_permission` VALUES (2, '系统资源', NULL, 'pagejump:*', '/admin/pagejump/**', NULL, NULL, NULL);
INSERT INTO `t_permission` VALUES (5000, '系统设置', '系统设置', 'permission', 'permission', 1, 0, 0);
INSERT INTO `t_permission` VALUES (5100, '个人中心', '个人中心', 'member:*', '/admin/member/**', 1, 0, 5000);
INSERT INTO `t_permission` VALUES (5110, '个人资料', '个人资料', 'member:index', '/admin/member/index', 1, 0, 5100);
INSERT INTO `t_permission` VALUES (5120, '密码修改', '密码修改', 'member:updatepwd', '/admin/member/updatepwd/', 1, 2, 5100);
INSERT INTO `t_permission` VALUES (5200, '用户中心', '用户中心', 'usercenter', '/admin/user/**', 1, 1, 5000);
INSERT INTO `t_permission` VALUES (5210, '用户列表', '用户管理', 'user:index', '/admin/user/index', 1, 0, 5200);
INSERT INTO `t_permission` VALUES (5300, '系统权限', '系统权限', 'permissioncenter', 'permissioncenter', 1, 2, 5000);
INSERT INTO `t_permission` VALUES (5310, '角色列表', '角色管理', 'role:index', '/admin/role/index', 1, 0, 5300);
INSERT INTO `t_permission` VALUES (5320, '权限列表', '权限管理', 'permission:index', '/admin/permission/index', 1, 1, 5300);
INSERT INTO `t_permission` VALUES (5330, '菜单列表', '菜单管理', 'menu:index', '/admin/menu/index', 1, 2, 5300);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表 ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色字符串',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 't_role' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', 'administrator', '超级管理员拥有至高无上的权限');
INSERT INTO `t_role` VALUES (2, '管理员', 'admin', '管理员');
INSERT INTO `t_role` VALUES (3, '用户', 'user', '用户');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `role_id` int(11) NOT NULL COMMENT '角色 id',
  `permission_id` int(11) NOT NULL COMMENT '资源 id',
  INDEX `FK_Reference_3`(`role_id`) USING BTREE,
  INDEX `FK_Reference_4`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 't_role_resource' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1);
INSERT INTO `t_role_permission` VALUES (2, 2);
INSERT INTO `t_role_permission` VALUES (2, 5000);
INSERT INTO `t_role_permission` VALUES (2, 5100);
INSERT INTO `t_role_permission` VALUES (2, 5110);
INSERT INTO `t_role_permission` VALUES (2, 5120);
INSERT INTO `t_role_permission` VALUES (2, 5200);
INSERT INTO `t_role_permission` VALUES (2, 5210);
INSERT INTO `t_role_permission` VALUES (3, 2);
INSERT INTO `t_role_permission` VALUES (3, 5000);
INSERT INTO `t_role_permission` VALUES (3, 5100);
INSERT INTO `t_role_permission` VALUES (3, 5110);
INSERT INTO `t_role_permission` VALUES (3, 5120);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `staffname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工工号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `status` int(11) NOT NULL COMMENT '状态:0 禁用,1 启用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_7`(`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户&推送人员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 1, 'admin', 'admin', '53f139e187e60f8376343990737b8fec', 1);
INSERT INTO `t_user` VALUES (2, 2, 'yutons', 'yutons', '650ce2fecc42ba4d47d9fa0ad0c475c8', 1);
INSERT INTO `t_user` VALUES (3, 3, 'user', 'user', '2f2adc2fe65335edc3f8a9d996899fd5', 1);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户 id',
  `role_id` int(11) NOT NULL COMMENT '角色 id',
  INDEX `FK_Reference_5`(`role_id`) USING BTREE,
  INDEX `FK_Reference_6`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 't_user_role' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (2, 2);
INSERT INTO `t_user_role` VALUES (3, 3);

-- ----------------------------
-- View structure for view_permission_user
-- ----------------------------
DROP VIEW IF EXISTS `view_permission_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`` SQL SECURITY DEFINER VIEW `view_permission_user` AS select `t_permission`.`id` AS `id`,`t_permission`.`name` AS `name`,`t_permission`.`menuname` AS `menuname`,`t_permission`.`permission` AS `permission`,`t_permission`.`url` AS `url`,`t_permission`.`flag` AS `flag`,`t_permission`.`zindex` AS `zindex`,`t_permission`.`parantid` AS `parantid`,`t_user_role`.`user_id` AS `userid` from ((`t_user_role` left join `t_role_permission` on((`t_user_role`.`role_id` = `t_role_permission`.`role_id`))) left join `t_permission` on((`t_role_permission`.`permission_id` = `t_permission`.`id`)));

-- ----------------------------
-- View structure for view_role_permission
-- ----------------------------
DROP VIEW IF EXISTS `view_role_permission`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`` SQL SECURITY DEFINER VIEW `view_role_permission` AS select `t_role`.`id` AS `id`,`t_role`.`name` AS `name`,`t_role`.`sn` AS `sn`,`t_permission`.`id` AS `permissionId`,`t_permission`.`name` AS `permissionname`,`t_permission`.`menuname` AS `menuname`,`t_permission`.`permission` AS `permission`,`t_permission`.`url` AS `url`,`t_permission`.`flag` AS `flag`,`t_permission`.`zindex` AS `zindex`,`t_permission`.`parantid` AS `parantid` from ((`t_role` left join `t_role_permission` on((`t_role`.`id` = `t_role_permission`.`role_id`))) left join `t_permission` on((`t_role_permission`.`permission_id` = `t_permission`.`id`)));

-- ----------------------------
-- View structure for view_role_user
-- ----------------------------
DROP VIEW IF EXISTS `view_role_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`` SQL SECURITY DEFINER VIEW `view_role_user` AS select distinct `t_role`.`id` AS `id`,`t_role`.`name` AS `name`,`t_role`.`sn` AS `sn`,`t_user_role`.`user_id` AS `user_id` from (`t_user_role` left join `t_role` on((`t_role`.`id` = `t_user_role`.`role_id`)));

-- ----------------------------
-- View structure for view_user_dept_role
-- ----------------------------
DROP VIEW IF EXISTS `view_user_dept_role`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`` SQL SECURITY DEFINER VIEW `view_user_dept_role` AS select `t_user`.`id` AS `id`,`t_user`.`dept_id` AS `dept_id`,`t_user`.`staffname` AS `staffname`,`t_user`.`username` AS `username`,`t_user`.`password` AS `password`,`t_user`.`status` AS `status`,`t_dept`.`name` AS `deptname`,`t_role`.`name` AS `rolename`,`t_role`.`id` AS `roleId`,`t_dept`.`id` AS `deptId` from (((`t_user` left join `t_dept` on((`t_user`.`dept_id` = `t_dept`.`id`))) left join `t_user_role` on((`t_user`.`id` = `t_user_role`.`user_id`))) left join `t_role` on((`t_user_role`.`role_id` = `t_role`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
