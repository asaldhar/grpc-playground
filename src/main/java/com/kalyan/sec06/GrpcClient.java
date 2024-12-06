package com.kalyan.sec06;

import com.google.common.util.concurrent.ListenableFuture;
import com.kalyan.models.sec06.AccountBalance;
import com.kalyan.models.sec06.BalanceCheckRequest;
import com.kalyan.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

public class GrpcClient {

    private static final Logger log = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        var channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext().
                build();

        var stub = BankServiceGrpc.newFutureStub(channel);

        ListenableFuture<AccountBalance> accountBalance = stub.getAccountBalance(BalanceCheckRequest.newBuilder().setAccountNumber(2).build());

        if(!accountBalance.isDone()) {
            Thread.sleep(200);
        }
        log.info("Account balance is : {}", accountBalance.get());


        /*var stub = BankServiceGrpc.newStub(channel);

        stub.getAccountBalance(BalanceCheckRequest.newBuilder().setAccountNumber(2).build(), new StreamObserver<AccountBalance>() {
            @Override
            public void onNext(AccountBalance accountBalance) {
                log.info("Account Balance : {}", accountBalance);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Request has encountered an error : ", throwable.getCause());

            }

            @Override
            public void onCompleted() {
                log.info("Request has been processed successfully!!");
            }
        });

        log.info("Done");
        Thread.sleep(1000);*/
    }
}
