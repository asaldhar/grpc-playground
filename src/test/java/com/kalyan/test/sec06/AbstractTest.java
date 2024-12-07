package com.kalyan.test.sec06;

import com.kalyan.common.GrpcServer;
import com.kalyan.models.sec06.BankServiceGrpc;
import com.kalyan.models.sec06.TransferServiceGrpc;
import com.kalyan.sec06.BankService;
import com.kalyan.sec06.TransferService;
import com.kalyan.test.common.AbstarctChannelTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AbstractTest extends AbstarctChannelTest {

    private final GrpcServer grpcServer = GrpcServer.create(new BankService(), new TransferService());
    protected BankServiceGrpc.BankServiceStub bankAsyncStub;
    protected BankServiceGrpc.BankServiceBlockingStub bankBlockingStub;
    protected BankServiceGrpc.BankServiceFutureStub bankFutureStub;

    protected TransferServiceGrpc.TransferServiceStub transferAsyncStub;

    @BeforeAll
    public void setup() {
        this.grpcServer.start();
        this.bankBlockingStub = BankServiceGrpc.newBlockingStub(channel);
        this.bankAsyncStub = BankServiceGrpc.newStub(channel);
        this.bankFutureStub = BankServiceGrpc.newFutureStub(channel);

        this.transferAsyncStub = TransferServiceGrpc.newStub(channel);
    }

    @AfterAll
    public void stop(){
        this.grpcServer.stop();
    }

}
