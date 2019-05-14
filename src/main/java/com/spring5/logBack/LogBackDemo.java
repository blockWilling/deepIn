package com.spring5.logBack;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.spring5.entity.Person;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Component;

/**
 * Created by blockWilling on 2019/4/29.
 */
@Component
public class LogBackDemo {
    private final static Logger logger = LoggerFactory.getLogger(LogBackDemo.class);

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("zzzc");
        logger.debug("The new entry1 is {}.", person);
        logger.debug("The new entry2 is {}.", person);
//        MessageFormatter.arrayFormat("The new entry is {}.", new Object[]{person}).getMessage();
       LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);

    }
}
