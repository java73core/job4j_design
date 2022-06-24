package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UsageLog4j {
        private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        String name = "Petr Arsentev";
        int age = 33;
        double price = 10.0;
        float voice = 22.0f;
        byte radius = 22;
        boolean flag = true;
        long l = 323232;
        short sh = 343;
        char ch = 'c';
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("Char: {}, double : {}", ch, price);
        LOG.debug("short: {}, byte : {}", sh, radius);
        LOG.debug("long: {}, boolean : {}", l, flag);
    }
}
