package org.example.account;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户数据对象
 * 对应数据库 tb_account 表的一行记录
 * account_id PK, user_name, status tinyint(1=正常)
 */
public class AccountDO {

    private Long accountId;
    private String accountNo;
    private String userName;
    private BigDecimal balance;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public String getAccountNo() { return accountNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Date getGmtCreate() { return gmtCreate; }
    public void setGmtCreate(Date gmtCreate) { this.gmtCreate = gmtCreate; }
    public Date getGmtModified() { return gmtModified; }
    public void setGmtModified(Date gmtModified) { this.gmtModified = gmtModified; }
}
