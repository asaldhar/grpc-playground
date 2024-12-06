package com.kalyan.sec04;

import com.kalyan.models.common.Address;
import com.kalyan.models.common.BodyStyle;
import com.kalyan.models.common.Car;
import com.kalyan.models.sec04.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01Import {

    private static final Logger log = LoggerFactory.getLogger(Lec01Import.class);

    public static void main(String[] args) {

        var address = Address.newBuilder().setCity("atlanta").build();
        var car = Car.newBuilder().setBodyStyle(BodyStyle.COUPE).build();
        var person = Person.newBuilder()
                .setName("sam")
                .setAddress(address)
                .setCar(car)
                .build();

        log.info("{}", person);
    }

}
