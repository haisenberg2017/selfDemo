/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : self

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-10-19 16:06:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `link_url` varchar(255) DEFAULT '' COMMENT '菜单请求',
  `icon_cls` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `t_level` int(4) unsigned NOT NULL DEFAULT '0' COMMENT '菜单层级',
  `seq_id` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '菜单顺序',
  `menu_pid` int(11) NOT NULL COMMENT '父级菜单主键',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '主页', '', 'fa fa-home', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('2', '主页示例一', 'index_v1.html', '', '2', '1', '1');
INSERT INTO `sys_menu` VALUES ('3', '主页示例二', 'index_v2.html', '', '2', '2', '1');
INSERT INTO `sys_menu` VALUES ('4', '主页示例三', 'index_v3.html', '', '2', '3', '1');
INSERT INTO `sys_menu` VALUES ('5', '主页示例四', 'index_v3.html', '', '2', '4', '1');
INSERT INTO `sys_menu` VALUES ('6', '主页示例五', 'index_v3.html', '', '2', '5', '1');
INSERT INTO `sys_menu` VALUES ('10', '表单', '', 'fa fa-edit', '1', '2', '0');
INSERT INTO `sys_menu` VALUES ('11', '基本表单', 'form_basic.html', '', '2', '1', '10');
INSERT INTO `sys_menu` VALUES ('12', '表单验证', 'form_validate.html', '', '2', '2', '10');
INSERT INTO `sys_menu` VALUES ('13', '高级插件', 'form_advanced.html', '', '2', '3', '10');
INSERT INTO `sys_menu` VALUES ('14', '文件上传', '', '', '2', '4', '10');
INSERT INTO `sys_menu` VALUES ('15', '百度WebUploader', 'form_webuploader.html', '', '3', '1', '14');
INSERT INTO `sys_menu` VALUES ('16', '头像裁剪上传', '', '', '3', '2', '14');
INSERT INTO `sys_menu` VALUES ('20', '系统管理', '', 'fa fa-wrench', '1', '3', '0');
INSERT INTO `sys_menu` VALUES ('21', '菜单管理', 'menu/menuJson.do', '', '2', '1', '20');
INSERT INTO `sys_menu` VALUES ('22', '修改密码', '', '', '2', '99', '20');
INSERT INTO `sys_menu` VALUES ('23', '系统参数', 'sys/param/list', '', '2', '2', '20');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `menu_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单角色主键',
  `menu_id` int(11) NOT NULL COMMENT '菜单表主键',
  `role_id` int(11) NOT NULL COMMENT '角色表主键',
  PRIMARY KEY (`menu_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单角色表';

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `param_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(255) NOT NULL,
  `param_type` varchar(255) NOT NULL,
  `param_value` varchar(255) NOT NULL DEFAULT '',
  `description` varchar(255) DEFAULT '',
  `seq_id` int(6) unsigned NOT NULL DEFAULT '0',
  `del_flag` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '0未删除、1删除',
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_param
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表主键',
  `role_id` int(11) NOT NULL COMMENT '角色表主键',
  `permission_name` varchar(40) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `role_name` varchar(40) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(40) NOT NULL COMMENT '用户账号名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `salt` varchar(255) NOT NULL COMMENT '盐值',
  `age` int(4) NOT NULL COMMENT '用户年龄',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'test1', 'pwd1', '', '21');
INSERT INTO `sys_user` VALUES ('2', 'test2', 'pwd2', '', '22');
INSERT INTO `sys_user` VALUES ('3', '测试3', 'pwd2', '', '23');
INSERT INTO `sys_user` VALUES ('4', 'admin', '91f545df7b48aefbe4a6e16d066bbf21', '5dd226bee075e73d0800ddbd646fa664', '20');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色表主键',
  `user_id` int(11) NOT NULL COMMENT '用户表对应主键',
  `role_id` int(11) NOT NULL COMMENT '角色表对应主键',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
