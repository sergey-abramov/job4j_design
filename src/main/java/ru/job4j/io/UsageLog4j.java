package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Sergey";
        int age = 24;
        boolean yng = true;
        long growthMM = 17500;
        double growthM = 1.75;
        float growtM = 1.75f;
        byte byt = 2;
        short s = 23;
        LOG.debug("User info name : {}, age : {}, growthMM : {}, growthM : {}, growtM {}.",
                name, age, growthMM, growthM, growtM);
        LOG.debug("Yng? - {}. Byt = {}, short = {}", yng, byt, s);
    }
}
