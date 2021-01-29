/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.28 : Database - ermasmiddb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `ermasmiddb`;

/*Table structure for table `dh_case_label` */

DROP TABLE IF EXISTS `dh_case_label`;

CREATE TABLE `dh_case_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `acct_no` varchar(50) NOT NULL COMMENT '案件编号',
  `h_max_od_days` int(10) DEFAULT NULL COMMENT '历史最大逾期天数',
  `risk_rank` double(20,2) DEFAULT NULL COMMENT '风险评分',
  `is_jp` int(2) DEFAULT '0' COMMENT '是否特殊案件, 1：是；0：否',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_acct_no_index` (`acct_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dh_case_label` */

/*Table structure for table `ermas_all_ok` */

DROP TABLE IF EXISTS `ermas_all_ok`;

CREATE TABLE `ermas_all_ok` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IS_DONE` char(1) NOT NULL COMMENT '完成标识',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '@create',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4657 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ermas_all_ok` */

insert  into `ermas_all_ok`(`ID`,`IS_DONE`,`CREATE_TIME`) values (4656,'Y','2020-05-26 17:00:23');

/*Table structure for table `ermas_case_label` */

DROP TABLE IF EXISTS `ermas_case_label`;

CREATE TABLE `ermas_case_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acct_no` varchar(128) NOT NULL COMMENT '案件编号',
  `h_max_od_days` int(10) DEFAULT NULL COMMENT '逾期天数',
  `insert_time` varchar(32) DEFAULT NULL COMMENT '数据从hive提取到表时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '''更新时间',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `acc_no` (`acct_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ermas_case_label` */

/*Table structure for table `mid_account` */

DROP TABLE IF EXISTS `mid_account`;

CREATE TABLE `mid_account` (
  `LID` varchar(20) DEFAULT NULL COMMENT '标id',
  `APP_NO` varchar(20) DEFAULT NULL COMMENT '申请编号',
  `ID_NO` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `CONT_NO` varchar(100) NOT NULL COMMENT '合同编号',
  `SERVICE_CODE` varchar(20) DEFAULT NULL COMMENT '签约客服',
  `SIGN_SALES_DEP_ID` varchar(20) DEFAULT NULL COMMENT '签约门店',
  `LOAN_TYPE` varchar(30) DEFAULT NULL COMMENT '借款类型',
  `LOAN_STATE` varchar(30) DEFAULT NULL COMMENT '借款状态',
  `RATE` decimal(19,3) DEFAULT NULL COMMENT '利率',
  `TERMS` int(11) DEFAULT NULL COMMENT '期数',
  `PACT_MONEY` decimal(19,2) DEFAULT NULL COMMENT '合同金额',
  `GRANT_MONEY` decimal(19,2) DEFAULT NULL COMMENT '放款金额',
  `GRANT_TIME` varchar(50) DEFAULT NULL COMMENT '放款时间',
  `PURPOSE` varchar(100) DEFAULT NULL COMMENT '贷款目的',
  `COLLECT_FLAG` varchar(10) DEFAULT NULL COMMENT '案件类型',
  `SIGN_DATE` varchar(20) DEFAULT NULL COMMENT '格式：yyyymmdd 签约日期',
  `RETU_KIND` varchar(100) DEFAULT NULL COMMENT '还款方式',
  `FINISH_STATUS` varchar(10) DEFAULT NULL COMMENT '结清标识',
  `REMAIN_PRINCIPAL` decimal(19,2) DEFAULT NULL COMMENT '剩余本金',
  `REMAIN_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '剩余利息',
  `OVER_DUE_AMT` decimal(19,2) DEFAULT NULL COMMENT '逾期总额',
  `OVER_DUE_PRINCIPAL` decimal(19,2) DEFAULT NULL COMMENT '逾期本金',
  `OVER_DUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '逾期利息',
  `OVER_DUE_PENALTY` decimal(19,2) DEFAULT NULL COMMENT '逾期罚息',
  `OVER_DUE_LIQUI` decimal(19,2) DEFAULT NULL COMMENT '逾期违约金',
  `OVER_DUE_DAYS` int(11) DEFAULT NULL COMMENT '逾期天数',
  `OVER_DUE_DATE` varchar(50) DEFAULT NULL COMMENT '逾期起始日',
  `STORE_PROVINCE` varchar(30) DEFAULT NULL COMMENT '门店所在省',
  `STORE_CITY` varchar(30) DEFAULT NULL COMMENT '门店所在市',
  `STORE_COUNTY` varchar(30) DEFAULT NULL COMMENT '门店所在县',
  `MARK` varchar(10) DEFAULT NULL COMMENT '金信，财富，p2p',
  `IS_TELE_SALE` varchar(10) DEFAULT NULL COMMENT '是否电销',
  `REMAIN_PACT_MONEY` decimal(19,2) DEFAULT NULL COMMENT '剩余合同金额',
  `REPAID_TERMS` int(11) DEFAULT NULL COMMENT '已还期数',
  `REMAIN_TERMS` int(11) DEFAULT NULL COMMENT '剩余期数',
  `IS_OVERDUE` char(2) DEFAULT NULL COMMENT '是否逾期',
  `PAY_SUBJECT` varchar(40) DEFAULT NULL COMMENT '支付主体',
  `MGMT_FEE` decimal(19,2) DEFAULT NULL COMMENT '管理费',
  `OVERDUE_MGMT_FEE` decimal(19,2) DEFAULT NULL COMMENT '逾期管理费',
  `OTHER_FEE` decimal(19,2) DEFAULT NULL COMMENT '其他费用',
  `LOAN_TIMES` int(11) DEFAULT NULL COMMENT '贷款次数',
  `LOAN_PURPOSE` varchar(32) DEFAULT 'CASH_BORROW' COMMENT '借款用途（现金借款或信用卡代偿）',
  `PARTNER_USER_ID` varchar(255) DEFAULT NULL COMMENT '业务用户ID',
  `RANK_LABLE` varchar(20) DEFAULT NULL COMMENT '等级标签',
  `MOB_MIN` int(11) DEFAULT NULL COMMENT 'mob_min',
  `MOB` int(11) DEFAULT NULL COMMENT 'mob',
  `LOAN_LABEL` varchar(20) DEFAULT NULL COMMENT '首贷贷中标签',
  `T0_EARLY_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT 't0期初服务费',
  `T0_EARLY_SERVICE_FEE_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT 't0期初服务费逾期罚息',
  `OVER_DUE_T0_EARLY_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '逾期T0期初服务费',
  `GUARANTEE_DEPOSIT` decimal(19,2) DEFAULT NULL COMMENT '保障金',
  `GUARANTEE_DEPOSIT_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '保障金服务费',
  `OVER_DUE_GUARANTEE_DEPOSIT` decimal(19,2) DEFAULT NULL COMMENT '逾期保障金',
  `OVER_DUE_GUARANTEE_DEPOSIT_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '逾期保障金服务费',
  `division_monthly_over_due_level` varchar(20) DEFAULT NULL COMMENT '分案月末逾期阶段',
  `BILL_COLLECTION_MODE` varchar(64) DEFAULT NULL COMMENT '账单入催模式',
  `HAS_T0_OVERDUE` char(2) DEFAULT NULL COMMENT '是否有T0期逾期',
  `T0_FUNDER` varchar(32) DEFAULT NULL COMMENT 'T0期资金方',
  `FUNDER_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '资金方罚息',
  `IS_EXIST_EARLY_FEE` char(2) DEFAULT NULL COMMENT '是否存在趸缴费用',
  `FUNDER_CODE` varchar(32) DEFAULT NULL COMMENT '资金方',
  `CREDITOR_CODE` varchar(32) DEFAULT NULL COMMENT '债权人',
  `IS_EXIST_T0` varchar(10) DEFAULT NULL,
  `T0_OVERDUE_DAYS` int(11) DEFAULT NULL,
  `T0_DUE_DATE` varchar(32) DEFAULT NULL,
  `T0_OVERDUE_AMOUNT` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`CONT_NO`),
  KEY `idx_mid_account_id_no` (`ID_NO`),
  KEY `idx_loan_purpose` (`LOAN_PURPOSE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中间表账户表';

/*Data for the table `mid_account` */

/*Table structure for table `mid_city` */

DROP TABLE IF EXISTS `mid_city`;

CREATE TABLE `mid_city` (
  `ID` varchar(32) NOT NULL,
  `AREA_NAME` varchar(30) DEFAULT NULL,
  `SHORT_NAME` varchar(30) DEFAULT NULL,
  `AREA_CODE` varchar(30) DEFAULT NULL,
  `AREA_TYPE` varchar(30) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `CREATE_BY` varchar(50) DEFAULT NULL,
  `CREATE_TIME` varchar(50) DEFAULT NULL,
  `MODIFY_BY` varchar(30) DEFAULT NULL,
  `MODIFY_TIME` varchar(50) DEFAULT NULL,
  `CITY_CAR_CODE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `mid_city` */

/*Table structure for table `mid_contact` */

DROP TABLE IF EXISTS `mid_contact`;

CREATE TABLE `mid_contact` (
  `ID` varchar(64) NOT NULL,
  `ID_NO` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `CONTACT_NAME` varchar(200) DEFAULT NULL COMMENT '联系人姓名',
  `PHONE` varchar(100) DEFAULT NULL COMMENT '电话号码',
  `CONTACT_TYPE` varchar(10) DEFAULT NULL COMMENT '联系人类型',
  `RELATIONSHIP` varchar(50) DEFAULT NULL COMMENT '联系人关系',
  `CONTACT_DEPARTMENT` varchar(50) DEFAULT NULL COMMENT '联系人单位',
  `ADDRESS` varchar(400) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中间库联系人表';

/*Data for the table `mid_contact` */

/*Table structure for table `mid_customer` */

DROP TABLE IF EXISTS `mid_customer`;

CREATE TABLE `mid_customer` (
  `CUST_NAME` varchar(30) NOT NULL COMMENT '客户姓名',
  `ID_TYPE` varchar(5) DEFAULT NULL COMMENT '证件类型',
  `ID_NO` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `SEX` varchar(2) DEFAULT NULL COMMENT '性别',
  `MARITAL_STATUS` char(12) DEFAULT NULL COMMENT '婚姻状况',
  `EDUCATION` varchar(100) DEFAULT NULL COMMENT '最高学历',
  `HAS_CHILD` varchar(20) DEFAULT NULL COMMENT '有无子女',
  `HAS_HOUSE` varchar(20) DEFAULT NULL COMMENT '是否有房',
  `EMAIL` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `MOBILE_PHONE` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `REGISEAT` varchar(500) DEFAULT NULL COMMENT '户籍地址',
  `ADDRESS` varchar(500) DEFAULT NULL COMMENT '居住地址',
  `BANK_CARD_NO` varchar(20) DEFAULT NULL COMMENT '银行卡号',
  `BANK_NAME` varchar(100) DEFAULT NULL COMMENT '银行名称',
  `STATUS` varchar(100) DEFAULT NULL COMMENT '客户状态',
  `CUSTOMER_TYPE` varchar(100) DEFAULT NULL COMMENT '客户类型',
  `CA_COMPANY` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `CA_TYPE` varchar(30) DEFAULT NULL COMMENT '单位性质',
  `CA_INDUSTRY_TYPE` varchar(80) DEFAULT NULL COMMENT '所属行业',
  `CA_WORK_PHONE` varchar(50) DEFAULT NULL COMMENT '单位电话',
  `CA_DUTY` varchar(10) DEFAULT NULL COMMENT '职务',
  `CA_ENTER_TIME` varchar(50) DEFAULT NULL COMMENT '//当前单位入职时间，格式yyyymmdd',
  `CA_INCOME` decimal(19,2) DEFAULT NULL COMMENT '每月总收入',
  `CONT_NO` varchar(100) NOT NULL COMMENT '合同编号',
  `AGENCY` varchar(255) DEFAULT NULL COMMENT '所属机构',
  `CAMPUS` varchar(255) DEFAULT NULL COMMENT '所属校区',
  PRIMARY KEY (`CONT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中间库客户表';

/*Data for the table `mid_customer` */

/*Table structure for table `mid_guarantor` */

DROP TABLE IF EXISTS `mid_guarantor`;

CREATE TABLE `mid_guarantor` (
  `CUST_NAME` varchar(30) DEFAULT NULL COMMENT '客户姓名',
  `ID_TYPE` varchar(5) DEFAULT NULL COMMENT '证件类型',
  `ID_NO` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `SEX` varchar(2) DEFAULT NULL COMMENT '性别',
  `MARITAL_STATUS` char(12) DEFAULT NULL COMMENT '婚姻状况',
  `EDUCATION` varchar(100) DEFAULT NULL COMMENT '最高学历',
  `HAS_CHILD` varchar(20) DEFAULT NULL COMMENT '有无子女',
  `HAS_HOUSE` varchar(20) DEFAULT NULL COMMENT '是否有房',
  `EMAIL` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(100) DEFAULT NULL COMMENT '电话号码',
  `REGISEAT` varchar(500) DEFAULT NULL COMMENT '户籍地址',
  `ADDRESS` varchar(500) DEFAULT NULL COMMENT '居住地址',
  `STATUS` varchar(100) DEFAULT NULL COMMENT '客户状态',
  `CUSTOMER_TYPE` varchar(100) DEFAULT NULL COMMENT '客户类型',
  `CA_INCOME` decimal(19,2) DEFAULT NULL COMMENT '每月总收入',
  `CONT_NO` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中间库共借人表';

/*Data for the table `mid_guarantor` */

/*Table structure for table `mid_repay_plan` */

DROP TABLE IF EXISTS `mid_repay_plan`;

CREATE TABLE `mid_repay_plan` (
  `PLAN_ID` varchar(32) NOT NULL COMMENT '还款计划id',
  `CONT_NO` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `TERMS` int(11) DEFAULT NULL COMMENT '期数',
  `SHOULD_TIME` date DEFAULT NULL COMMENT '应还时间',
  `PMT_AMT` decimal(19,2) DEFAULT NULL COMMENT '还款金额',
  `PRINCIPAL` decimal(19,2) DEFAULT NULL COMMENT '本金',
  `INTEREST` decimal(19,2) DEFAULT NULL COMMENT '利息',
  `REMAIN_TOTAL_AMT` decimal(19,2) DEFAULT NULL COMMENT '剩余还款金额',
  `PENALTY` decimal(19,2) DEFAULT NULL COMMENT '罚息',
  `LIQUI` decimal(19,2) DEFAULT NULL COMMENT '违约金',
  `TERM_NEED_AMT` decimal(19,2) DEFAULT NULL COMMENT '本期应还款额',
  `ACTUAL_REPAY_DATE` varchar(50) DEFAULT NULL COMMENT '实际还款日',
  `IS_OVERDUE` char(2) DEFAULT NULL COMMENT '是否逾期',
  `IS_PAY_PRINCIPAL` char(2) DEFAULT NULL COMMENT '是否还清本金',
  `RELIEF_INFO` varchar(50) DEFAULT NULL COMMENT '减免信息',
  `REPAID_CURRENT_AMT` decimal(19,2) DEFAULT NULL COMMENT '当期期供已还',
  `REMAIN_CURRENT_AMT` decimal(19,2) DEFAULT NULL COMMENT '当期期供未还',
  `EARLY_CURRENT_AMT` decimal(19,2) DEFAULT NULL COMMENT '提前结清一次性还清金额',
  `SHOULD_PENALTY` decimal(19,2) DEFAULT NULL COMMENT '应还罚息',
  `SHOULD_LIQUI` decimal(19,2) DEFAULT NULL COMMENT '应还违约金',
  `SHOULD_LATEFEE` decimal(19,2) DEFAULT NULL COMMENT '应还滞纳金',
  `ALSO_PENALTY` decimal(19,2) DEFAULT NULL COMMENT '实还罚息',
  `ALSO_LIQUI` decimal(19,2) DEFAULT NULL COMMENT '实还违约金',
  `ALSO_LATEFEE` decimal(19,2) DEFAULT NULL COMMENT '实还滞纳金',
  `BREAKS_PENALTY` decimal(19,2) DEFAULT NULL COMMENT '减免罚息',
  `BREAKS_LIQUI` decimal(19,2) DEFAULT NULL COMMENT '减免违约金',
  `BREAKS_LATEFEE` decimal(19,2) DEFAULT NULL COMMENT '减免滞纳金',
  `REMAIN_PENALTY` decimal(19,2) DEFAULT NULL COMMENT '未还罚息',
  `REMAIN_LIQUI` decimal(19,2) DEFAULT NULL COMMENT '未还违约金',
  `REMAIN_LATEFEE` decimal(19,2) DEFAULT NULL COMMENT '未还滞纳金',
  `CURRENT_OVER_DUE_DAYS` int(11) DEFAULT NULL COMMENT '当期逾期天数',
  `TREM_STATUS` varchar(20) DEFAULT NULL COMMENT '期供状态',
  `TREM_SERVICES_CHARGE` decimal(19,2) DEFAULT NULL COMMENT '分期服务费',
  `PAYOFF_TIME` date DEFAULT NULL COMMENT '结清时间',
  `REPAY_TYPE` varchar(32) DEFAULT NULL COMMENT '结清方式',
  `MGMT_FEE` decimal(19,2) DEFAULT NULL COMMENT '服务费',
  `LEFT_PRINCIPAL` decimal(19,2) DEFAULT NULL COMMENT '剩余未还本金',
  `LEFT_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '剩余未还利息',
  `REPAID_PRINCIPAL` decimal(19,2) DEFAULT NULL COMMENT '实还本金',
  `REPAID_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '实还利息',
  `REPAID_MGMT_FEE` decimal(19,2) DEFAULT NULL COMMENT '实还管理费',
  `T0_EARLY_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT 't0期初服务费',
  `T0_EARLY_SERVICE_FEE_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT 't0期初服务费逾期罚息',
  `LEFT_T0_EARLY_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '剩余未还T0期初服务费',
  `LEFT_T0_EARLY_SERVICE_FEE_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '剩余未还T0期初服务费逾期罚息',
  `BREAKS_T0_EARLY_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '减免T0期初服务费',
  `BREAKS_T0_EARLY_SERVICE_FEE_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '减免T0期初服务费逾期罚息',
  `GUARANTEE_DEPOSIT` decimal(19,2) DEFAULT NULL COMMENT '保障金',
  `GUARANTEE_DEPOSIT_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '保障金服务费',
  `LEFT_GUARANTEE_DEPOSIT` decimal(19,2) DEFAULT NULL COMMENT '剩余未还保障金',
  `LEFT_GUARANTEE_DEPOSIT_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '剩余未还保障金服务费',
  `BREAKS_GUARANTEE_DEPOSIT` decimal(19,2) DEFAULT NULL COMMENT '减免保障金',
  `BREAKS_GUARANTEE_DEPOSIT_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '减免保障金服务费',
  `FUNDER_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '资金方罚息',
  `LEFT_FUNDER_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '剩余未还资金方罚息',
  `BREAKS_FUNDER_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '减免资金方罚息',
  PRIMARY KEY (`PLAN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中间库还款计划表';

/*Data for the table `mid_repay_plan` */

/*Table structure for table `mid_repayment` */

DROP TABLE IF EXISTS `mid_repayment`;

CREATE TABLE `mid_repayment` (
  `ID` varchar(128) NOT NULL COMMENT '交易流水ID',
  `PLAN_ID` varchar(128) DEFAULT NULL COMMENT '还款计划ID',
  `CONT_NO` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `ID_NO` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `REPAY_TIME` date DEFAULT NULL COMMENT '还款时间',
  `RETU_KIND` varchar(100) DEFAULT NULL COMMENT '还款方式',
  `REPAYMENT_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '还款金额',
  `REPAY_TERM` int(11) DEFAULT NULL COMMENT '还款期次',
  `REPAY_PRINCIPAL` decimal(19,2) DEFAULT NULL COMMENT '还款本金',
  `REPAY_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '还款利息',
  `PENALTY` decimal(19,2) DEFAULT NULL COMMENT '罚息',
  `LIQUI` decimal(19,2) DEFAULT NULL COMMENT '违约金',
  `REPAY_FEE` decimal(19,2) DEFAULT NULL COMMENT '还款服务费',
  `REPAY_OTHER` decimal(19,2) DEFAULT NULL COMMENT '还款其他费用',
  `REPAY_MANAGE` decimal(19,2) DEFAULT NULL COMMENT '还款逾期管理费',
  `T0_EARLY_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT 't0期初服务费',
  `T0_EARLY_SERVICE_FEE_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT 't0期初服务费逾期罚息',
  `GUARANTEE_DEPOSIT` decimal(19,2) DEFAULT NULL COMMENT '保障金',
  `GUARANTEE_DEPOSIT_SERVICE_FEE` decimal(19,2) DEFAULT NULL COMMENT '保障金服务费',
  `FUNDER_OVERDUE_INTEREST` decimal(19,2) DEFAULT NULL COMMENT '资金方罚息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中间库还款流水表';

/*Data for the table `mid_repayment` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `skill` varchar(32) DEFAULT NULL COMMENT '技能',
  `evaluate` varchar(64) DEFAULT NULL COMMENT '评价',
  `fraction` bigint(11) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`name`,`age`,`skill`,`evaluate`,`fraction`) values (1,'1',1,'1','1',1),(2,'3',3,'3','3',3),(23,'3',3,'3','3',3),(24,'小明',20,'画画','该学生在画画方面有一定天赋',89),(25,'小兰',19,'游戏','近期该学生由于游戏的原因导致分数降低了',64),(26,'张张',18,'英语','近期该学生参加英语比赛获得二等奖',90),(27,'大黄',20,'体育','该学生近期由于参加篮球比赛,导致脚伤',76),(28,'大白',17,'绘画','该学生参加美术大赛获得三等奖',77),(29,'小龙',18,'JAVA','该学生是一个在改BUG的码农',59),(30,'张乔木',18,'JAVA,C++','This is a IT man.',59),(31,'王彬彬',18,'JAVA,C++','This is a IT man.',59),(32,'小美丽',18,'JAVA,C++','This is a IT man.',59),(34,'贾薇娜',20,'画画，唱歌','得过1等奖',23),(35,'贾薇娜',20,'画画，唱歌','得过1等奖',23),(36,'1 2',20,'画画，唱歌','得过1等奖',23),(37,'1 2',20,'画画，唱歌','得过1等奖',23),(38,'  2',20,'画画，唱歌','得过1等奖',23);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
