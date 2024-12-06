package com.kalyan.common;

import com.kalyan.sec06.BankService;
import com.kalyan.sec06.TransferService;

public class Demo {

    public static void main(String[] args) {

        GrpcServer.create(new BankService(), new TransferService())
                .start()
                .await();

    }
}
