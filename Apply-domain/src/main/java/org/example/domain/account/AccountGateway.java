package org.example.domain.account;

import java.math.BigDecimal;

/**
 * 账户仓库接口
 * 告诉"数据库层"要提供什么能力，但不关心你怎么实现
 */
public interface AccountGateway {

    /** 根据账号查账户 */
    Account findByAccountNo(String accountNo);

    /** 更新账户余额（转账后保存双方新余额） */
    int updateBalance(String accountNo, BigDecimal newBalance);

    /** 保存交易记录 */
    void saveTransaction(Transaction transaction);
}
