package com.kalyan.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kalyan.models.sec03.Person;

public class Lec01Scalar {

    private static final Logger log = LoggerFactory.getLogger(Lec01Scalar.class);

    public static void main(String[] args) {

        Person person = Person.newBuilder()
                .setLastName("sam")
//                .setAge(30)
//                .setEmail("sam@gmail.com")
//                .setEmployed(true)
//                .setSalary(1000.1234)
//                .setBankAccountNumber(123456789012L)
//                .setBalance(-10000)
                .build();

        log.info("{}",person);
    }
}
