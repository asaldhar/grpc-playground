package com.kalyan.assignment;

import com.kalyan.models.assignment.GuessNumberGrpc;
import com.kalyan.models.assignment.GuessRequest;
import com.kalyan.models.assignment.GuessResponse;
import com.kalyan.models.assignment.Result;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class GuessNumberService extends GuessNumberGrpc.GuessNumberImplBase {

    private static final Logger log = LoggerFactory.getLogger(GuessNumberService.class);

    @Override
    public StreamObserver<GuessRequest> makeGuess(StreamObserver<GuessResponse> responseObserver) {
        return new GuessRequestHandler(responseObserver);
    }

    private static class GuessRequestHandler implements StreamObserver<GuessRequest> {

        private static final Logger log = LoggerFactory.getLogger(GuessRequestHandler.class);

        private final StreamObserver<GuessResponse> responseObserver;
        private int serverGuess;
        private int attempt;

        private GuessRequestHandler(StreamObserver<GuessResponse> responseObserver) {
            this.responseObserver = responseObserver;
            this.serverGuess = ThreadLocalRandom.current().nextInt(1, 101);
            log.info("Server Guess is {}", this.serverGuess);
            this.attempt = 0;
        }


        @Override
        public void onNext(GuessRequest guessRequest) {
            attempt++;
            Result result;
            if (guessRequest.getGuess() == serverGuess) {
                result = Result.CORRECT;
            } else if (guessRequest.getGuess() < serverGuess) {
                result = Result.TOO_LOW;
            } else {
                result = Result.TOO_HIGH;
            }

            responseObserver.onNext(GuessResponse.newBuilder()
                    .setAttempt(attempt)
                    .setResult(result)
                    .build());

            if(Result.CORRECT.equals(result)) {
                responseObserver.onCompleted();
            }
        }

        @Override
        public void onError(Throwable throwable) {
            log.info("error detected {}", throwable.getMessage());
        }

        @Override
        public void onCompleted() {
            log.info("completed");
        }
    }

}

