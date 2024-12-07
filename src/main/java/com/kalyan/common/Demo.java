package com.kalyan.common;

import com.kalyan.sec06.BankService;
import com.kalyan.sec06.TransferService;
import com.kalyan.sec07.FlowControlService;
import com.kalyan.assignment.GuessNumberService;

public class Demo {

    public static void main(String[] args) {

        GrpcServer.create(new BankService(), new TransferService(), new FlowControlService(), new GuessNumberService())
                .start()
                .await();

    }
}
