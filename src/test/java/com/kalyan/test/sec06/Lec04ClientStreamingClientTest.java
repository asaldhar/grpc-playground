package com.kalyan.test.sec06;

import com.kalyan.models.sec06.AccountBalance;
import com.kalyan.models.sec06.DepositRequest;
import com.kalyan.models.sec06.Money;
import com.kalyan.test.common.ResponseObserver;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class Lec04ClientStreamingClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(Lec04ClientStreamingClientTest.class);


    @Test
    public void depositTest() {

        var responseObserver = ResponseObserver.<AccountBalance>create();

        StreamObserver<DepositRequest> requestObserver = this.stub.deposit(responseObserver);
        requestObserver.onNext(DepositRequest.newBuilder().setAccountNumber(1).build());
        IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Money.newBuilder().setAmount(10).build())
                .map(m -> DepositRequest.newBuilder().setMoney(m).build())
                .forEach(requestObserver::onNext);
        requestObserver.onCompleted();

        responseObserver.await();

        Assertions.assertEquals(1, responseObserver.getItems().size());
        Assertions.assertEquals(200, responseObserver.getItems().get(0).getBalance());
        Assertions.assertNull(responseObserver.getThrowable());

    }

}
