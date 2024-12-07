package com.kalyan.sec07;

import com.kalyan.models.sec07.FlowControlServiceGrpc;
import com.kalyan.models.sec07.Output;
import com.kalyan.models.sec07.RequestSize;
import com.kalyan.sec07.common.RequestSizeHandler;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowControlService extends FlowControlServiceGrpc.FlowControlServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(FlowControlService.class);

    @Override
    public StreamObserver<RequestSize> getMessages(StreamObserver<Output> responseObserver) {
        return new RequestSizeHandler(responseObserver);
    }
}
