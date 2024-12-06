package com.kalyan.sec02;

import com.kalyan.models.sec02.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoDemo {

    public static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);

    public static void main(String[] args) {

        var person1 = createPerson();

        var person2 = createPerson();

        log.info("person1 and 2 equals {}",person1.equals(person2));
        log.info("person1 and 2 == {}",person1 == person2);

        //mutable ? NO
        //person1.set methods not available

        //create another instace with diff values

        Person person3 = person1.toBuilder().setName("mike").build();

        log.info("person1 and 3 equals {}",person1.equals(person3));
        log.info("person1 and 3 == {}",person1 == person3);


        //Person person4 = person1.toBuilder().setName(null).build(); throws NullPointerException, use clear methods instead

        Person person4 = person1.toBuilder().clearName().build();
        log.info("person4 {}",person4); // Name won't be printed since its cleared out



    }

    private static Person createPerson() {

        return Person.newBuilder()
                .setName("Sam")
                .setAge(12)
                .build();
    }
}
