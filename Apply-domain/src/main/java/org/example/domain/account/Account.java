package org.example.domain.account;

import java.math.BigDecimal;

/**
 * 账户（领域核心）
 * 一个账户就是一个聚合根，负责管理自己的余额
 */
public class Account {

    /** 主键 */
    private Long id;

    /** 账号（全局唯一，如 AC100001） */
    private String accountNo;

    /** 账户名 */
    private String name;

    /** 余额（单位元） */
    private BigDecimal balance;

    /** 状态 ACTIVE / FROZEN */
    private String status;

    public Account(Long id, String accountNo, String name, BigDecimal balance, String status) {
        this.id = id;
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    /**
     * 扣款
     * 在内存中检查余额够不够，够了就扣掉
     * @param amount 要扣的金额
     */
    public void debit(BigDecimal amount) {
        if (!"ACTIVE".equals(this.status)) {
            throw new RuntimeException("账户" + this.accountNo + "已冻结，无法扣款");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new RuntimeException("账户" + this.accountNo + "余额不足，当前余额:" + this.balance);
        }
        this.balance = this.balance.subtract(amount);
    }

    /**
     * 入账
     * 把钱加到余额里
     * @param amount 入账金额
     */
    public void credit(BigDecimal amount) {
        if (!"ACTIVE".equals(this.status)) {
            throw new RuntimeException("账户" + this.accountNo + "已冻结，无法入账");
        }
        this.balance = this.balance.add(amount);
    }

    // Getter（不需要 Setter，余额只能通过 debit/credit 修改）
    public Long getId() { return id; }
    public String getAccountNo() { return accountNo; }
    public String getName() { return name; }
    public BigDecimal getBalance() { return balance; }
    public String getStatus() { return status; }
}
