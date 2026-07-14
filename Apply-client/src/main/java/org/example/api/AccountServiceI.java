package org.example.api;

import org.example.dto.account.AccountBalanceResp;
import org.example.dto.account.TransferMoneyCmd;
import org.example.dto.account.TransferResp;

/**
 * 账户服务API
 * 定义转账和查余额的接口契约
 */
public interface AccountServiceI {

    /**
     * 转账
     * @param cmd 转账命令（谁付、谁收、多少钱、备注）
     * @return 转账结果（流水号、双方余额）
     */
    TransferResp transfer(TransferMoneyCmd cmd);

    /**
     * 查余额
     * @param accountNo 账号
     * @return 账户名 + 余额
     */
    AccountBalanceResp getBalance(String accountNo);
}
