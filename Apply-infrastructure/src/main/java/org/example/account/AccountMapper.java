package org.example.account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;

/**
 * 账户 Mapper
 * MyBatis 接口，定义数据库操作
 */
@Mapper
public interface AccountMapper {

    /** 根据账号查询 */
    AccountDO findByAccountNo(@Param("accountNo") String accountNo);

    /** 更新余额 */
    int updateBalance(@Param("accountNo") String accountNo,
                      @Param("balance") BigDecimal balance);

    /** 插入交易记录 */
    void insertTransaction(TransactionDO transactionDO);

    AccountDO findByAccountNoForUpdate(@Param("accountNo") String accountNo);
}

