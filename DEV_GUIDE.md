# 账户转账系统 — 开发文档

## 1. 核心业务逻辑

```
请求校验 → 查询双方账户 → 校验状态(ACTIVE)
→ 校验余额(>=转账金额) → 扣款 → 入账
→ 更新余额 → 保存流水 → 返回结果
```

**事务保证：** 扣款 + 入账 + 存流水在同一个 `@Transactional` 中，任何一步失败则全部回滚。

## 2. 数据库

### 2.1 账户表

```sql
CREATE TABLE tb_account (
  account_no  VARCHAR(32) PRIMARY KEY,
  name        VARCHAR(50) NOT NULL,
  balance     DECIMAL(18,2) NOT NULL DEFAULT 0.00,
  status      VARCHAR(10) NOT NULL DEFAULT 'ACTIVE'
);
```

### 2.2 交易流水表

```sql
CREATE TABLE tb_transaction (
  transaction_no   VARCHAR(64) PRIMARY KEY,
  from_account_no  VARCHAR(32) NOT NULL,
  to_account_no    VARCHAR(32) NOT NULL,
  amount           DECIMAL(18,2) NOT NULL,
  status           VARCHAR(10) NOT NULL DEFAULT 'SUCCESS',
  remark           VARCHAR(200),
  gmt_create       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_from (from_account_no),
  INDEX idx_to (to_account_no)
);
```

### 2.3 测试数据

```sql
INSERT INTO tb_account VALUES ('AC100001', '张三', 10000.00, 'ACTIVE');
INSERT INTO tb_account VALUES ('AC100002', '李四', 5000.00, 'ACTIVE');
```

## 3. API 接口

### 3.1 查余额

```
GET  /api/accounts/{accountNo}/balance
```

**响应：**
```json
{"accountNo":"AC100001","name":"张三","balance":10000.00}
```

### 3.2 转账

```
POST /api/accounts/transfer
Content-Type: application/json
```

**请求：**
```json
{"fromAccountNo":"AC100001","toAccountNo":"AC100002","amount":2000,"remark":"饭钱"}
```

**响应：**
```json
{"transactionNo":"TX...","fromBalance":2000,"toBalance":2000}
```

## 4. 测试

```bash
# 查余额
curl http://localhost:8080/api/accounts/AC100001/balance

#充值
POST http://localhost:8080/api/accounts/recharge
{
  "accountNo": "AC100001",
  "amount": 5000,
  "remark": "充值"
}
响应
{
    "accountNo": "AC100001",
    "name": "张三",
    "balance": 5000.00
}
# 转账
curl -X POST http://localhost:8080/api/accounts/transfer \
  -H "Content-Type: application/json" \
  -d "{\"fromAccountNo\":\"AC100001\",\"toAccountNo\":\"AC100002\",\"amount\":2000,\"remark\":\"饭钱\"}"
  {
  "fromAccountNo": "AC100002",
  "toAccountNo": "AC100001",
  "amount": 9
  }
# 验证余额
curl http://localhost:8080/api/accounts/AC100001/balance  # 8000
curl http://localhost:8080/api/accounts/AC100002/balance  # 7000

# 异常测试（余额不足）
curl -X POST http://localhost:8080/api/accounts/transfer \
  -H "Content-Type: application/json" \
  -d "{\"fromAccountNo\":\"AC100002\",\"toAccountNo\":\"AC100001\",\"amount\":99999}"
  {
  "fromAccountNo": "AC100002",
  "toAccountNo": "AC100001",
  "amount": 99999
  }
```
