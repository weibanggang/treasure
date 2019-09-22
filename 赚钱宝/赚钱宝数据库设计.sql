
CREATE DATABASE money_treasure;
USE money_treasure;
CREATE TABLE website_information(
`uuid` INT(11) AUTO_INCREMENT COMMENT '主键',
page_address VARCHAR(500) COMMENT '网页地址',
page_name VARCHAR(500) COMMENT '网页名称',
page_status VARCHAR(500) COMMENT '网页状态',
page_time VARCHAR(500) COMMENT '添加时间',
page_remarks VARCHAR(500) COMMENT '备注',
PRIMARY KEY (`UUID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='网站信息';

CREATE TABLE get_content(
UUID INT(11) AUTO_INCREMENT COMMENT '主键',
page_uuid VARCHAR(500) COMMENT '网页id',
page_address VARCHAR(500) NULL COMMENT '网页地址',
page_connent LONGTEXT COMMENT '网页内容',
get_time VARCHAR(500) COMMENT '读取时间',
get_statis VARCHAR(500) COMMENT '读取状态',
key_connent VARCHAR(500) COMMENT '关键内容',
is_award VARCHAR(500) COMMENT '是否中奖内容',
number_of_periods VARCHAR(500) COMMENT '期数',
PRIMARY KEY (`UUID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='读取内容';

CREATE TABLE award_information(
UUID INT(11)  COMMENT '主键当前期数',
page_uuid VARCHAR(500) COMMENT '下一期数',
page_connent VARCHAR(500) COMMENT '特码',
get_time VARCHAR(500) COMMENT '读取时间',
all_content VARCHAR(500) COMMENT '全部内容',
get_statis VARCHAR(500) COMMENT '读取状态',
PRIMARY KEY (`UUID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='开奖信息';



CREATE TABLE `schedule`(
`UUID` INT AUTO_INCREMENT COMMENT '主键',
task_name VARCHAR(100) COMMENT '任务名称',
task_description VARCHAR(500) COMMENT '任务描述',
class_name VARCHAR(500) COMMENT '类名',
param VARCHAR(500) COMMENT '参数',
task_status VARCHAR(100) COMMENT '任务状态',
`second` VARCHAR(100) COMMENT '秒',
`minute` VARCHAR(100) COMMENT '分钟',
`hour` VARCHAR(100) COMMENT '小时',
`day` VARCHAR(100) COMMENT '天',
`month` VARCHAR(100) COMMENT '月',
`weekday` VARCHAR(100) COMMENT '周',
`year` VARCHAR(100) COMMENT '年',
`cron_expression` VARCHAR(100) COMMENT '表达式',
PRIMARY KEY (`uuid`)
)ENGINE=INNODB DEFAULT CHARSET = utf8 COMMENT '任务定时'

CREATE TABLE `schedule_log`(
`UUID` INT AUTO_INCREMENT COMMENT '主键',
class_name VARCHAR(100) COMMENT '路径',
start_time VARCHAR(500) COMMENT '开始时间',
end_time VARCHAR(500) COMMENT '结束时间',
datasource VARCHAR(500) COMMENT '数据源',
status_log VARCHAR(100) COMMENT '状态',
PRIMARY KEY (`UUID`)
);