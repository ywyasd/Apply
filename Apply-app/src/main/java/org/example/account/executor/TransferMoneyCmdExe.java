package org.example.account.executor;

import org.example.domain.account.Account;
import org.example.domain.account.AccountGateway;
import org.example.domain.account.Transaction;
import org.example.domain.account.TransferDomainService;
import org.example.dto.account.TransferMoneyCmd;
import org.example.dto.account.TransferResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 转账命令处理器
 * 收到转账请求 → 调领域服务执行转账 → 把结果包装成 DTO 返回
 */
@Component
public class TransferMoneyCmdExe {

    @Autowired
    private TransferDomainService transferDomainService;

    @Autowired
    private AccountGateway accountGateway;

    public TransferResp execute(TransferMoneyCmd cmd) {
        // 1. 调领域服务执行转账
        Transaction transaction = transferDomainService.transfer(
                cmd.getFromAccountNo(),
                cmd.getToAccountNo(),
                cmd.getAmount(),
                cmd.getRemark()
        );

        // 2. 查询转账后双方真实余额
        Account fromAccount = accountGateway.findByAccountNo(cmd.getFromAccountNo());
        Account toAccount = accountGateway.findByAccountNo(cmd.getToAccountNo());

        // 3. 包装结果返回
        return new TransferResp(
                transaction.getTransactionNo(),
                fromAccount.getBalance(),
                toAccount.getBalance()
        );
    }
}
