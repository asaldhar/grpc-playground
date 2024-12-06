package com.kalyan.sec05;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kalyan.models.sec05.v1.Television;
import com.kalyan.sec05.parser.V1Parser;
import com.kalyan.sec05.parser.V2Parser;
import com.kalyan.sec05.parser.V3Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V1VersionCompatibility {

    private static final Logger log = LoggerFactory.getLogger(V1VersionCompatibility.class);

    public static void main(String[] args) throws InvalidProtocolBufferException {

        var tv = Television.newBuilder()
                .setBrand("samsung")
                .setYear(2019)
                .build();

        log.info("V1 Parser ============================");
        V1Parser.parse(tv.toByteArray());

        log.info("V2 Parser ============================");
        V2Parser.parse(tv.toByteArray());

        log.info("V3 Parser ============================");
        V3Parser.parse(tv.toByteArray());

    }
}
