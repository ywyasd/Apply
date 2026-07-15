package org.example.test;

import org.example.domain.account.Account;
import org.example.domain.account.AccountGateway;
import org.example.dto.account.TransferMoneyCmd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransferConcurrencyTest {

    @Autowired
    private org.example.account.executor.TransferMoneyCmdExe transferMoneyCmdExe;

    @Autowired
    private AccountGateway accountGateway;

    @Test
    public void testConcurrentTransfer() throws InterruptedException {
        String from = "AC100003";
        String to = "AC100004";
        BigDecimal amount = new BigDecimal("500");
        int threadCount = 15;

        Account fromBefore = accountGateway.findByAccountNo(from);
        Account toBefore = accountGateway.findByAccountNo(to);
        BigDecimal fromInit = fromBefore.getBalance();
        BigDecimal toInit = toBefore.getBalance();

        System.out.println("初始: " + from + "=" + fromInit + ", " + to + "=" + toInit);

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    TransferMoneyCmd cmd = new TransferMoneyCmd();
                    cmd.setFromAccountNo(from);
                    cmd.setToAccountNo(to);
                    cmd.setAmount(amount);
                    cmd.setRemark("并发测试");

                    transferMoneyCmdExe.execute(cmd);
                    success.incrementAndGet();
                } catch (Exception e) {
                    fail.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        Account fromAfter = accountGateway.findByAccountNo(from);
        Account toAfter = accountGateway.findByAccountNo(to);
        BigDecimal fromFinal = fromAfter.getBalance();
        BigDecimal toFinal = toAfter.getBalance();

        BigDecimal expectedFrom = fromInit.subtract(
                amount.multiply(BigDecimal.valueOf(success.get())));

        System.out.println("\n--- 结果 ---");
        System.out.println("成功: " + success.get() + " 笔");
        System.out.println("失败: " + fail.get() + " 笔");
        System.out.println(from + ": " + fromInit + " → " + fromFinal + " (预期 " + expectedFrom + ")");
        System.out.println(to + ": " + toInit + " → " + toFinal);

        assertTrue(fromFinal.compareTo(BigDecimal.ZERO) >= 0, "付款方余额为负！锁没生效！");

        BigDecimal totalBefore = fromInit.add(toInit);
        BigDecimal totalAfter = fromFinal.add(toFinal);
        assertTrue(totalBefore.compareTo(totalAfter) == 0, "总资产不守恒！");
    }
}
