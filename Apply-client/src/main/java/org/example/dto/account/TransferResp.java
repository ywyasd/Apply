package org.example.dto.account;

import java.math.BigDecimal;

/**
 * 转账结果
 */
public class TransferResp {

    /** 交易流水号（类似支付宝单号） */
    private String transactionNo;

    /** 转账后你的余额 */
    private BigDecimal fromBalance;

    /** 转账后对方余额 */
    private BigDecimal toBalance;

    public TransferResp(String transactionNo, BigDecimal fromBalance, BigDecimal toBalance) {
        this.transactionNo = transactionNo;
        this.fromBalance = fromBalance;
        this.toBalance = toBalance;
    }

    public String getTransactionNo() { return transactionNo; }
    public BigDecimal getFromBalance() { return fromBalance; }
    public BigDecimal getToBalance() { return toBalance; }
}
