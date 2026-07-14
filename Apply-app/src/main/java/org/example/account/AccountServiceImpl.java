package org.example.account;

import org.example.account.executor.AccountBalanceQryExe;
import org.example.account.executor.TransferMoneyCmdExe;
import org.example.api.AccountServiceI;
import org.example.dto.account.AccountBalanceResp;
import org.example.dto.account.TransferMoneyCmd;
import org.example.dto.account.TransferResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户应用服务实现
 * 收到请求 → 分派给对应的命令/查询处理器 → 返回结果
 */
@Service
public class AccountServiceImpl implements AccountServiceI {

    @Autowired
    private TransferMoneyCmdExe transferMoneyCmdExe;

    @Autowired
    private AccountBalanceQryExe accountBalanceQryExe;

    @Override
    public TransferResp transfer(TransferMoneyCmd cmd) {
        return transferMoneyCmdExe.execute(cmd);
    }

    @Override
    public AccountBalanceResp getBalance(String accountNo) {
        return accountBalanceQryExe.execute(accountNo);
    }
}
