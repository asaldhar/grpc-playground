package com.kalyan.test.sec06;

import com.kalyan.models.sec06.Money;
import com.kalyan.models.sec06.WithdrawRequest;
import com.kalyan.test.common.ResponseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec03ServerStreamingClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(Lec03ServerStreamingClientTest.class);

    @Test
    public void blockingClientWithWithdrawTest() {
        var request = WithdrawRequest.newBuilder()
                .setAccountNumber(2)
                .setAmount(50)
                .build();


       var iterator = bankBlockingStub.withdraw(request);

       int count = 0;
       while (iterator.hasNext()) {
           Money money = iterator.next();
           Assertions.assertEquals(10, money.getAmount());
           count++;
           log.info("received money {}", money);
       }

       Assertions.assertEquals(5, count);

    }
    @Test
    public void asyncClientWithWithdrawTest() {
        var request = WithdrawRequest.newBuilder()
                .setAccountNumber(2)
                .setAmount(50)
                .build();

       var observer = ResponseObserver.<Money>create();

        bankAsyncStub.withdraw(request, observer);
        observer.await();

        Assertions.assertNull(observer.getThrowable());
        Assertions.assertEquals(5, observer.getItems().size());
        Assertions.assertEquals(10, observer.getItems().get(0).getAmount());
    }

}
