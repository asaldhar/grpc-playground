package com.kalyan.sec03;

import com.kalyan.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lec02Serialization {

    private static final Logger log = LoggerFactory.getLogger(Lec02Serialization.class);

    private static final Path PATH = Path.of("person.out");

    public static void main(String[] args) throws IOException {

        Person person = Person.newBuilder()
//                .setLastName("sam")
//                .setAge(30)
//                .setEmail("sam@gmail.com")
                .setEmployed(true)
//                .setSalary(1000.1234)
//                .setBankAccountNumber(123456789012L)
//                .setBalance(-10000)
                .build();

        serialize(person);

        log.info("{}",deserialize());
        log.info("equals: {}",person.equals(deserialize()));
        log.info("bytes length {}", person.toByteArray().length);
    }

    private static void serialize(Person person) throws IOException {
        //person.writeTo(Files.newOutputStream(PATH));

        try(var stream = Files.newOutputStream(PATH)) {
            person.writeTo(stream);
        }
    }

    private static Person deserialize() throws IOException {
//        return Person.parseFrom(Files.newInputStream(PATH));

        try(var stream = Files.newInputStream(PATH)) {
            return Person.parseFrom(stream);
        }
    }
}
