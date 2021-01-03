--秒杀商品表
USE myseckill;
drop table if exists seckill;
drop table if exists seckill_detail;

create table seckill(
    seckill_id BIGINT AUTO_INCREMENT COMMENT '商品id';  --建表语句内部使用,
    name VARCHAR(200) NOT NULL COMMENT '商品名称';
    number BIGINT NOT NULL COMMENT '商品数量';
    create_time timestamp NOT NULL COMMENT '创建时间';
    start_time timestamp NOT NULL COMMENT '开始时间';
    end_time timestamp NOT NULL COMMENT '结束时间';

    --设置主键和索引
    primary (seckill_id);
    key idx_create_time(create_time);
    key idx_start_time(start_time);
    key idx_end_time(end_time);
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = UTF8 COMMENT '秒杀库存表'  --COMMENT='xxx'


INSERT INTO seckill values
--1.create_time 使用default current_timestamp，不需要填写
--2. seckill(name, number, create_time, end_time)

('华为mate10',1000,2021-1-2 00:00:00, 2021-1-2 00:00:00, 2021-1-5 00:00:00),
('Mac',200,2021-1-2 00:00:00, 2021-1-2 00:00:00, 2021-1-5 00:00:00),
('SurfacePro7',1000,2021-1-2 00:00:00, 2021-1-3 00:00:00, 2021-1-5 00:00:00),
('小米11',1000,2021-1-2 00:00:00, 2021-1-1 00:00:00, 2021-1-2 00:00:00),
('爱疯Xmas',1000,2021-1-2 00:00:00, 2021-1-5 00:00:00, 2021-1-8 00:00:00);

--秒杀明细表
create table seckill_detail(
    seckillId BIGINT NOT NULL COMMENT '商品id';   --表内不能使用;，;表示结束
    user_phone BIGINT NOT NULL COMMENT '用户手机号';
    state TINYINT NOT NULL DEFAULT 0 COMMENT '秒杀状态: 默认0 成功， -1 无效 ，1 已付款';
    create_time timestamp NOT NULL COMMENT '创建时间';
    --联合主键
    primary (seckillId,user_phone);
    key idx_create_time(create_time); --此处没有;
)ENGINE = INNODB DEFAULT CHARSET = UTF8; -- DEFAULT CHARSET = UTF8, =不能少




---------------------------

USE myseckill;
DROP TABLE IF EXISTS seckill;
DROP TABLE IF EXISTS seckill_detail;

CREATE TABLE seckill(
    seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品id',
    NAME VARCHAR(120) NOT NULL COMMENT '商品名称',
    number BIGINT NOT NULL COMMENT '商品数量',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    start_time TIMESTAMP NOT NULL COMMENT '开始时间',
    end_time TIMESTAMP NOT NULL COMMENT '结束时间',


    PRIMARY KEY(seckill_id),
    KEY idx_create_time(create_time),
    KEY idx_start_time(start_time),
    KEY idx_end_time(end_time)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = UTF8 COMMENT = '秒杀库存表'



INSERT INTO seckill(NAME,number,start_time,end_time) VALUES
('华为mate10',1000, '2021-1-2 00:00:00', '2021-1-5 00:00:00'),
('Mac',200, '2021-1-2 00:00:00', '2021-1-5 00:00:00'),
('SurfacePro7',50, '2021-1-3 00:00:00', '2021-1-5 00:00:00'),
('小米11',100, '2021-1-1 00:00:00', '2021-1-2 00:00:00'),
('爱疯Xmas',300, '2021-1-5 00:00:00', '2021-1-8 00:00:00');


CREATE TABLE seckill_detail(
   seckillId BIGINT NOT NULL COMMENT '商品id',
   user_phone BIGINT NOT NULL COMMENT '用户手机号',
   state TINYINT NOT NULL DEFAULT 0 COMMENT '秒杀状态: 默认0 成功， -1 无效 ，1 已付款',
   create_time TIMESTAMP NOT NULL COMMENT '创建时间',

   PRIMARY KEY(seckillId,user_phone),
   KEY idx_create_time(create_time)
)ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT='秒杀明细表'



-----

USE myseckill;
DROP TABLE IF EXISTS seckill;
DROP TABLE IF EXISTS seckill_detail;


CREATE TABLE seckill(
    seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品id',
    NAME VARCHAR(120) NOT NULL COMMENT '商品名称',
    number BIGINT NOT NULL COMMENT '商品数量',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    start_time TIMESTAMP NOT NULL COMMENT '开始时间',
    end_time TIMESTAMP NOT NULL COMMENT '结束时间',

    PRIMARY KEY(seckill_id),
    KEY idx_create_time(create_time),
    KEY idx_start_time(start_time),
    KEY idx_end_time(end_time)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀商品表'

CREATE TABLE seckill_detail(
   seckill_id BIGINT NOT NULL COMMENT '商品id',
   user_phone BIGINT NOT NULL COMMENT '用户手机号',
   state TINYINT NOT NULL DEFAULT 0 COMMENT '秒杀状态: 默认0 成功， -1 无效 ，1 已付款',
   create_time TIMESTAMP NOT NULL COMMENT '创建时间',

   PRIMARY KEY(seckill_id,user_phone),
   KEY idx_create_time(create_time)
)ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT='秒杀明细表'



