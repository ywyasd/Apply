package org.example.web.account;

import org.example.account.executor.RechargeCmdExe;
import org.example.api.AccountServiceI;
import org.example.dto.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 账户 REST 控制器
 * 接收前端 HTTP 请求，调应用层服务
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountServiceI accountService;
    @Autowired
    private RechargeCmdExe rechargeCmdExe;

    /**
     * 转账
     * POST /api/accounts/transfer
     */
    @PostMapping("/transfer")
    public TransferResp transfer(@Valid @RequestBody TransferMoneyCmd cmd) {
        return accountService.transfer(cmd);
    }

    /**
     * 查余额
     * GET /api/accounts/{accountNo}/balance
     */
    @GetMapping("/{accountNo}/balance")
    public AccountBalanceResp getBalance(@PathVariable String accountNo) {
        return accountService.getBalance(accountNo);
    }

    /*
    * 充值
    * */
    @PostMapping("/recharge")
    public RechargeResp recharge(@Valid @RequestBody RechargeCmd cmd){
        return rechargeCmdExe.execute(cmd);
    }
}
