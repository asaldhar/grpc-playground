package com.kalyan.test.sec07;

import com.kalyan.common.GrpcServer;
import com.kalyan.models.sec07.FlowControlServiceGrpc;
import com.kalyan.sec07.FlowControlService;
import com.kalyan.test.common.AbstarctChannelTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Lec01FlowControlTest extends AbstarctChannelTest {

    private final GrpcServer server = GrpcServer.create(new FlowControlService());
    private FlowControlServiceGrpc.FlowControlServiceStub flowControlServiceStub;

    @BeforeAll
    public void setup() {
        this.flowControlServiceStub = FlowControlServiceGrpc.newStub(channel);
        this.server.start();
    }

    @Test
    void flowControlTest() {
        var responseObserver = new ResponseHandler();
        var requestObserver = this.flowControlServiceStub.getMessages(responseObserver);
        responseObserver.setRequestObserver(requestObserver);
        responseObserver.start();
        responseObserver.await();
    }

    @AfterAll
    public void stop() {
        this.server.stop();
    }
}
