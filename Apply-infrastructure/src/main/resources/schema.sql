-- 账户表
CREATE TABLE IF NOT EXISTS tb_account (
                                          account_id    BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                                          account_no    VARCHAR(32) NOT NULL COMMENT '账号',
    user_name     VARCHAR(50) NOT NULL COMMENT '账户名',
    balance       DECIMAL(18,2) NOT NULL DEFAULT 0.00 COMMENT '余额',
    status        TINYINT NOT NULL DEFAULT 1 COMMENT '状态 1=正常',
    gmt_create    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    gmt_modified  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_account_no (account_no)
    ) COMMENT='账户表';

-- 交易流水表
CREATE TABLE IF NOT EXISTS tb_transaction (
                                              transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                                              tx_no          VARCHAR(64) NOT NULL COMMENT '交易流水号',
    from_account_no VARCHAR(32) NOT NULL COMMENT '付款账号',
    to_account_no   VARCHAR(32) NOT NULL COMMENT '收款账号',
    amount         DECIMAL(18,2) NOT NULL COMMENT '转账金额',
    tx_type        VARCHAR(20) NOT NULL DEFAULT 'TRANSFER' COMMENT '交易类型',
    status         VARCHAR(10) NOT NULL DEFAULT 'SUCCESS' COMMENT '状态',
    remark         VARCHAR(200) DEFAULT NULL COMMENT '备注',
    gmt_create     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    gmt_modified   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_tx_no (tx_no),
    INDEX idx_from_account (from_account_no),
    INDEX idx_to_account (to_account_no)
    ) COMMENT='交易流水表';

-- 测试数据（只插入一次，余额为0开户）
INSERT IGNORE INTO tb_account (account_no, user_name, balance) VALUES ('AC100001', '张三', 0.00);
INSERT IGNORE INTO tb_account (account_no, user_name, balance) VALUES ('AC100002', '李四', 0.00);
INSERT IGNORE INTO tb_account (account_no, user_name, balance) VALUES ('AC100003', '王五', 5000.00);
INSERT IGNORE INTO tb_account (account_no, user_name, balance) VALUES ('AC100004', '赵六', 5000.00);
INSERT IGNORE INTO tb_account (account_no, user_name, balance) VALUES ('AC100005', '孙七', 5000.00);