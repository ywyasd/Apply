package org.example.account;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易流水数据对象
 * 对应数据库 tb_transaction 表的一行
 * transaction_id PK, tx_no, tx_type
 */
public class TransactionDO {

    private Long transactionId;
    private String txNo;
    private String fromAccountNo;
    private String toAccountNo;
    private BigDecimal amount;
    private String txType;
    private String status;
    private String remark;
    private Date gmtCreate;
    private Date gmtModified;

    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }
    public String getTxNo() { return txNo; }
    public void setTxNo(String txNo) { this.txNo = txNo; }
    public String getFromAccountNo() { return fromAccountNo; }
    public void setFromAccountNo(String fromAccountNo) { this.fromAccountNo = fromAccountNo; }
    public String getToAccountNo() { return toAccountNo; }
    public void setToAccountNo(String toAccountNo) { this.toAccountNo = toAccountNo; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getTxType() { return txType; }
    public void setTxType(String txType) { this.txType = txType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public Date getGmtCreate() { return gmtCreate; }
    public void setGmtCreate(Date gmtCreate) { this.gmtCreate = gmtCreate; }
    public Date getGmtModified() { return gmtModified; }
    public void setGmtModified(Date gmtModified) { this.gmtModified = gmtModified; }
}
