package com.kalyan.sec03;

import com.kalyan.models.sec03.Address;
import com.kalyan.models.sec03.Car;
import com.kalyan.models.sec03.Dealer;
import com.kalyan.models.sec03.Library;
import com.kalyan.models.sec03.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec07DefaultValues {

    private static final Logger log = LoggerFactory.getLogger(Lec07DefaultValues.class);

    public static void main(String[] args) {

        var school = School.newBuilder().build();

        log.info("{}", school.getId());
        log.info("{}", school.getName());
        log.info("{}", school.getAddress().getCity());

        log.info("Is default ? : {}", school.getAddress() == Address.getDefaultInstance());

        // collection

        var lib = Library.newBuilder().build();
        log.info("{}", lib.getBooksList());

        // map

        var dealer = Dealer.newBuilder().build();
        log.info("{}", dealer.getInventoryMap());

        // Enum
        var car = Car.newBuilder().build();
        log.info("{}", car.getBodyStyle());
    }

}
