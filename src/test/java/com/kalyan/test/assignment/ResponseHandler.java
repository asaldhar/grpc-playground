package com.kalyan.test.assignment;

import com.kalyan.models.assignment.GuessRequest;
import com.kalyan.models.assignment.GuessResponse;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ResponseHandler implements StreamObserver<GuessResponse> {

    private static final Logger log = LoggerFactory.getLogger(ResponseHandler.class);
    private StreamObserver<GuessRequest> requestObserver;
    private int guess;
    private final CountDownLatch latch = new CountDownLatch(1);
    private int lower;
    private int higher;
    private int middle;

    @Override
    public void onNext(GuessResponse guessResponse) {
        log.info("Guess Response : {}", guessResponse);
        switch (guessResponse.getResult()) {
            case TOO_LOW -> this.sendGuess(this.middle, this.higher);
            case TOO_HIGH -> this.sendGuess(this.lower, this.middle);
        }
    }

    private void sendGuess(int low, int high) {
        this.lower = low;
        this.higher = high;
        this.middle = low + (high - low) / 2;
        log.info("client passed {}", this.middle);
        this.requestObserver.onNext(GuessRequest.newBuilder().setGuess(this.middle).build());
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("Error detected {}", throwable.getMessage());
        this.latch.countDown();
    }

    @Override
    public void onCompleted() {
        log.info("Completed");
        this.latch.countDown();
    }

    public void setRequestObserver(StreamObserver<GuessRequest> requestObserver) {
        this.requestObserver = requestObserver;
    }

    public void start() {
        this.sendGuess(0, 100);
    }

    public void await() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
