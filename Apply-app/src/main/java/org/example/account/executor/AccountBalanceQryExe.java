package org.example.account.executor;

import org.example.domain.account.Account;
import org.example.domain.account.AccountGateway;
import org.example.dto.account.AccountBalanceResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 查余额查询处理器
 */
@Component
public class AccountBalanceQryExe {

    @Autowired
    private AccountGateway accountGateway;

    public AccountBalanceResp execute(String accountNo) {
        Account account = accountGateway.findByAccountNo(accountNo);
        if (account == null) {
            throw new RuntimeException("账户不存在");
        }
        return new AccountBalanceResp(
                account.getAccountNo(),
                account.getName(),
                account.getBalance()
        );
    }
}
