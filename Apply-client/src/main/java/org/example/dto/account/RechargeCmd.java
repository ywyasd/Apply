package org.example.dto.account;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/*
* 充值命令
* 往用户账户充钱
* */
@Data
public class RechargeCmd {
    @NotBlank(message = "账号不能为空")
    private String accountNo;//要充值的账号

    @NotNull(message = "金额不能为为空")
    @DecimalMin(value = "0.01", message = "充值金额必须大于0")
    private BigDecimal amount;//充值金额

    private String remark;//备注（可选）
}
