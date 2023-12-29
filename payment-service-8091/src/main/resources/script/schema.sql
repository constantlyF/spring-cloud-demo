CREATE TABLE `payment`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `serial`      varchar(50)       DEFAULT NULL COMMENT '流水号',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT '支付信息表';