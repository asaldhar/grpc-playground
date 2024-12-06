package com.kalyan.sec06;

import com.kalyan.models.sec06.TransferRequest;
import com.kalyan.models.sec06.TransferResponse;
import com.kalyan.models.sec06.TransferServiceGrpc;
import com.kalyan.sec06.requesthandlers.TransferRequestHandler;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(TransferService.class);


    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return new TransferRequestHandler(responseObserver);
    }
}
