package org.example.dto.account;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 转账命令
 * 相当于你在支付宝输入：转给谁 + 转多少 + 备注
 */
public class TransferMoneyCmd {

    /** 付款方账号（谁付钱，如 AC100001） */
    @NotBlank(message = "付款账号不能为空")
    private String fromAccountNo;

    /** 收款方账号（谁收钱，如 AC100002） */
    @NotBlank(message = "收款账号不能为空")
    private String toAccountNo;

    /** 转账金额（单位：元，最少0.01） */
    @DecimalMin(value = "0.01", message = "转账金额必须大于0")
    private BigDecimal amount;

    /** 转账备注（如"饭钱"、"房租"，可选） */
    private String remark;

    public String getFromAccountNo() { return fromAccountNo; }
    public void setFromAccountNo(String fromAccountNo) { this.fromAccountNo = fromAccountNo; }
    public String getToAccountNo() { return toAccountNo; }
    public void setToAccountNo(String toAccountNo) { this.toAccountNo = toAccountNo; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
