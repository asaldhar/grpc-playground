package com.kalyan.test.sec06;

import com.google.protobuf.Empty;
import com.kalyan.models.sec06.AccountBalance;
import com.kalyan.models.sec06.AllAccountsResponse;
import com.kalyan.models.sec06.BalanceCheckRequest;
import com.kalyan.test.common.ResponseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec02UnaryAsyncClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(Lec02UnaryAsyncClientTest.class);

    @Test
    public void getAccountBalanceTest() {
        var request = BalanceCheckRequest.newBuilder().setAccountNumber(0).build();
        var observer = ResponseObserver.<AccountBalance>create();
        this.bankAsyncStub.getAccountBalance(request, observer);
        observer.await();
        Assertions.assertEquals(1, observer.getItems().size());
        Assertions.assertEquals(100, observer.getItems().get(0).getBalance());
        Assertions.assertNull(observer.getThrowable());

    }
    @Test
    public void getAllAccountsTest() {
        var observer = ResponseObserver.<AllAccountsResponse>create();
        this.bankAsyncStub.getAllAccounts(Empty.getDefaultInstance(), observer);
        observer.await();
        Assertions.assertEquals(1, observer.getItems().size());
        Assertions.assertEquals(11, observer.getItems().get(0).getAccountsCount());
        Assertions.assertNull(observer.getThrowable());
    }
}
