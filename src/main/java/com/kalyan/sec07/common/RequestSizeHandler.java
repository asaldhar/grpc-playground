package com.kalyan.sec07.common;

import com.kalyan.models.sec07.Output;
import com.kalyan.models.sec07.RequestSize;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class RequestSizeHandler implements StreamObserver<RequestSize> {
    private static final Logger log = LoggerFactory.getLogger(RequestSizeHandler.class);

    private final StreamObserver<Output> outputStreamObserver;
    private int emitted;

    public RequestSizeHandler(StreamObserver<Output> outputStreamObserver) {
        this.outputStreamObserver = outputStreamObserver;
        this.emitted = 0;
    }


    @Override
    public void onNext(RequestSize requestSize) {
        IntStream.rangeClosed((emitted + 1),100)
                .limit(requestSize.getSize())
                .forEach(i -> {
                    log.info("emitting {}", i);
                    outputStreamObserver.onNext(Output.newBuilder().setValue(i).build());
                });

        emitted = emitted + requestSize.getSize();

        if(emitted >= 100) {
            outputStreamObserver.onCompleted();
        }

    }

    @Override
    public void onError(Throwable throwable) {
        outputStreamObserver.onCompleted();
    }

    @Override
    public void onCompleted() {

    }
}
