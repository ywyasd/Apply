package org.example.dto.account;

import java.math.BigDecimal;

/**
 * 账户余额查询结果
 */
public class AccountBalanceResp {

    /** 账号（唯一标识，如 AC100001） */
    private String accountNo;
    
    /** 账户名（如张三、李四） */
    private String name;
    
    /** 当前余额（单位：元） */
    private BigDecimal balance;

    public AccountBalanceResp(String accountNo, String name, BigDecimal balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNo() { return accountNo; }
    public String getName() { return name; }
    public BigDecimal getBalance() { return balance; }
}
