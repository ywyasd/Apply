package org.example.dto.account;

import lombok.Data;

import java.math.BigDecimal;

/*
* 充值结果
* */
public class RechargeResp {
    private String accountNo;//账户
    private String name;//账户名
    private BigDecimal balance;//充值后余额

    public RechargeResp(String accountNo, String name, BigDecimal balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
