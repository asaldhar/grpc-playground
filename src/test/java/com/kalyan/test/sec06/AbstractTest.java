package com.kalyan.test.sec06;

import com.kalyan.common.GrpcServer;
import com.kalyan.models.sec06.BankServiceGrpc;
import com.kalyan.sec06.BankService;
import com.kalyan.test.common.AbstarctChannelTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AbstractTest extends AbstarctChannelTest {

    private final GrpcServer grpcServer = GrpcServer.create(new BankService());
    protected BankServiceGrpc.BankServiceStub stub;
    protected BankServiceGrpc.BankServiceBlockingStub blockingStub;
    protected BankServiceGrpc.BankServiceFutureStub futureStub;

    @BeforeAll
    public void setup() {
        this.grpcServer.start();
        this.blockingStub = BankServiceGrpc.newBlockingStub(channel);
        this.stub = BankServiceGrpc.newStub(channel);
        this.futureStub = BankServiceGrpc.newFutureStub(channel);
    }

    @AfterAll
    public void stop(){
        this.grpcServer.stop();
    }

}
