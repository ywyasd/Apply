package org.example.account.executor;

import org.example.domain.account.Account;
import org.example.domain.account.AccountGateway;
import org.example.dto.account.RechargeCmd;
import org.example.dto.account.RechargeResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* 充值命令处理器
* */
@Component
public class RechargeCmdExe {
    @Autowired
    private AccountGateway accountGateway;

    public RechargeResp execute(RechargeCmd rechargeCmd){
        //查出账户
        Account account = accountGateway.findByAccountNo(rechargeCmd.getAccountNo());
        if(account == null){
            throw new RuntimeException("账户不存在");
        }
        //入账，校验在Account.credit方法里面
        account.credit(rechargeCmd.getAmount());
        //更新余额到数据库
        accountGateway.updateBalance(rechargeCmd.getAccountNo(), account.getBalance());
        //返回充值结果
        return new RechargeResp(account.getAccountNo(), account.getName(), account.getBalance());
    }
}
