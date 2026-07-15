package org.example.domain.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * 转账领域服务
 * 负责整个转账流程的编排：扣款 → 入账 → 保存流水
 */
@Service
public class TransferDomainService {

    private final AccountGateway accountGateway;

    public TransferDomainService(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Transactional(rollbackFor = Exception.class)
    public Transaction transfer(String fromAccountNo, String toAccountNo,
                                BigDecimal amount, String remark) {

        // 自己不能转给自己
        if (fromAccountNo.equals(toAccountNo)) {
            throw new RuntimeException("不能转账给自己");
        }

        // 1. 查出付款方和收款方
        Account fromAccount = accountGateway.findByAccountNoWithLock(fromAccountNo);
        Account toAccount = accountGateway.findByAccountNoWithLock(toAccountNo);

        // 2. 扣款（如果余额不够，debit() 里会抛异常）
        fromAccount.debit(amount);

        // 3. 入账
        toAccount.credit(amount);

        // 4. 保存双方新余额
        accountGateway.updateBalance(fromAccountNo, fromAccount.getBalance());
        accountGateway.updateBalance(toAccountNo, toAccount.getBalance());

        // 5. 生成交易流水号并保存记录
        String transactionNo = "TX" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
        Transaction transaction = new Transaction(transactionNo, fromAccountNo, toAccountNo,
                amount, "SUCCESS", remark);
        accountGateway.saveTransaction(transaction);

        return transaction;
    }
}
