package com.ruben.utils.other.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSlf4j {

    private Logger logger;

    public LoggerSlf4j() {
    }

    public LoggerSlf4j(Logger logger) {
        this.logger = logger;
    }

    /**
     * @param Class
     * @return Logger
     */
    public static Logger getLogger(Class<?> object) {
        return LoggerFactory.getLogger(object);
    }

    /**
     * @param String
     * @return Logger
     */
    public static Logger getLogger(String loggerName) {
        return LoggerFactory.getLogger(loggerName);
    }

}
