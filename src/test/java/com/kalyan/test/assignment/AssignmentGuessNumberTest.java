package com.kalyan.test.assignment;

import com.kalyan.assignment.GuessNumberService;
import com.kalyan.common.GrpcServer;
import com.kalyan.models.assignment.GuessNumberGrpc;
import com.kalyan.models.assignment.GuessResponse;
import com.kalyan.test.common.AbstarctChannelTest;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssignmentGuessNumberTest extends AbstarctChannelTest {

    private static final Logger log = LoggerFactory.getLogger(AssignmentGuessNumberTest.class);
    private GrpcServer server = GrpcServer.create(new GuessNumberService());
    private GuessNumberGrpc.GuessNumberStub stub;

    private StreamObserver<GuessResponse> responseObserver;

    @BeforeAll
    void setup() {
        this.server.start();
        stub = GuessNumberGrpc.newStub(channel);
    }

    @RepeatedTest(5)
    void guessGameTest() {
        var responseHandler = new ResponseHandler();
        var requestObserver = this.stub.makeGuess(responseHandler);
        responseHandler.setRequestObserver(requestObserver);
        responseHandler.start();
        responseHandler.await();
        log.info("------------------------------");
    }

    @AfterAll
    void stop() {
        this.server.stop();
    }
}
