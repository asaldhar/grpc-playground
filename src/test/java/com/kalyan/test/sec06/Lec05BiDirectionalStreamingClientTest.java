package com.kalyan.test.sec06;

import com.kalyan.models.sec06.TransferRequest;
import com.kalyan.models.sec06.TransferResponse;
import com.kalyan.test.common.ResponseObserver;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class Lec05BiDirectionalStreamingClientTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(Lec05BiDirectionalStreamingClientTest.class);

    @Test
    public void transferTest() {
        var responseObserver = ResponseObserver.<TransferResponse>create();
        StreamObserver<TransferRequest> requestObserver = this.transferAsyncStub.transfer(responseObserver);

        IntStream.rangeClosed(1, 5)
                .mapToObj(i -> TransferRequest.newBuilder().setFromAccount(1).setToAccount(2).setAmount(10).build())
                .forEach(requestObserver::onNext);

        requestObserver.onCompleted();

        responseObserver.await();

        Assertions.assertNull(responseObserver.getThrowable());
        Assertions.assertEquals(5, responseObserver.getItems().size());
        Assertions.assertEquals(90, responseObserver.getItems().get(0).getFromAccount().getBalance());

    }

}
