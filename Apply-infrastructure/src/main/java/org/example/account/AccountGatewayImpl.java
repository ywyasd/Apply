package org.example.account;

import org.example.domain.account.Account;
import org.example.domain.account.AccountGateway;
import org.example.domain.account.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 账户仓库实现
 * 把 domain 层定义的接口（AccountGateway）和数据库操作连接起来
 */
@Component
public class AccountGatewayImpl implements AccountGateway {
    @Autowired
    private  AccountMapper accountMapper;
    @Override
    public Account findByAccountNo(String accountNo) {
        // 1. 从数据库查出来 AccountDO
        AccountDO accountDO = accountMapper.findByAccountNo(accountNo);
        if (accountDO == null) {
            return null;
        }
        // 2. 把 AccountDO 转换成领域层的 Account
        // status: 数据库存 tinyint (1=ACTIVE, 0=FROZEN)
        String statusStr = (accountDO.getStatus() != null && accountDO.getStatus() == 1) ? "ACTIVE" : "FROZEN";
        return new Account(
                accountDO.getAccountId(),
                accountDO.getAccountNo(),
                accountDO.getUserName(),
                accountDO.getBalance(),
                statusStr
        );
    }

    @Override
    public int updateBalance(String accountNo, BigDecimal newBalance) {
        return accountMapper.updateBalance(accountNo, newBalance);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        // 把领域层的 Transaction 转成数据库的 TransactionDO
        TransactionDO transactionDO = new TransactionDO();
        transactionDO.setTxNo(transaction.getTransactionNo());
        transactionDO.setFromAccountNo(transaction.getFromAccountNo());
        transactionDO.setToAccountNo(transaction.getToAccountNo());
        transactionDO.setAmount(transaction.getAmount());
        transactionDO.setTxType("TRANSFER");
        transactionDO.setStatus(transaction.getStatus());
        transactionDO.setRemark(transaction.getRemark());
        accountMapper.insertTransaction(transactionDO);
    }

    @Override
    public Account findByAccountNoWithLock(String accountNo) {
        AccountDO accountDO = accountMapper.findByAccountNoForUpdate(accountNo);
        if(accountDO == null) return null;
        String statusStr = Integer.valueOf(1).equals(accountDO.getStatus()) ? "ACTIVE" : "FROZEN";
        return new Account(
                accountDO.getAccountId(),
                accountDO.getAccountNo(),
                accountDO.getUserName(),
                accountDO.getBalance(),
                statusStr
        );
    }
}
