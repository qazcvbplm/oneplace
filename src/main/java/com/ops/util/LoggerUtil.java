package com.ops.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerUtil {

    public static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void log(String msg) {
        logger.debug(msg);
    }
}
