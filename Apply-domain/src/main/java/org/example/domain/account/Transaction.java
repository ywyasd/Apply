package org.example.domain.account;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易记录（每一次转账产生一条记录）
 */
public class Transaction {

    private Long id;
    private String transactionNo;   // 交易流水号
    private String fromAccountNo;   // 付款方
    private String toAccountNo;     // 收款方
    private BigDecimal amount;      // 金额
    private String status;          // SUCCESS / FAILED
    private String remark;          // 备注
    private Date gmtCreate;

    public Transaction(String transactionNo, String fromAccountNo, String toAccountNo,
                       BigDecimal amount, String status, String remark) {
        this.transactionNo = transactionNo;
        this.fromAccountNo = fromAccountNo;
        this.toAccountNo = toAccountNo;
        this.amount = amount;
        this.status = status;
        this.remark = remark;
        this.gmtCreate = new Date();
    }

    public String getTransactionNo() { return transactionNo; }
    public String getFromAccountNo() { return fromAccountNo; }
    public String getToAccountNo() { return toAccountNo; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getRemark() { return remark; }
    public Date getGmtCreate() { return gmtCreate; }
}
