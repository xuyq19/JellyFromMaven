/*
 Navicat MySQL Data Transfer

 Source Server         : bank
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 123.60.106.3:3306
 Source Schema         : bank

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 01/01/2022 11:27:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `bankAccountUserId` varchar(255) NOT NULL,
  `bankAccountName` varchar(255) NOT NULL,
  `bankAccountPassword` varchar(255) NOT NULL,
  `bankAccountRealId` varchar(255) NOT NULL,
  `bankAccountPhoneNumber` varchar(255) NOT NULL,
  `bankAccountSex` varchar(255) NOT NULL,
  `bankAccountBirthDate` varchar(255) NOT NULL,
  `bankAccountBalance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank
-- ----------------------------
BEGIN;
INSERT INTO `bank` (`bankAccountUserId`, `bankAccountName`, `bankAccountPassword`, `bankAccountRealId`, `bankAccountPhoneNumber`, `bankAccountSex`, `bankAccountBirthDate`, `bankAccountBalance`) VALUES ('0000000002', 'xuyuqi', 'xuyuqi', '111111111111', '18606123867', 'M', '2001-03-06', 19709.7);
INSERT INTO `bank` (`bankAccountUserId`, `bankAccountName`, `bankAccountPassword`, `bankAccountRealId`, `bankAccountPhoneNumber`, `bankAccountSex`, `bankAccountBirthDate`, `bankAccountBalance`) VALUES ('0000000010', 'admin', '123456', '222222222222', '00000000000', 'M', '0000-00-00', 333333433);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
