package com.kms.task.utils;

import com.kms.task.service.MainDisplayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GlobalLoggerService {

    private static final Logger LOGGER = LogManager.getLogger(MainDisplayService.class.getName());

    public void printLog(String s) {
//        LOGGER.info("\033[3m" + s + "\033[0m");
        System.out.println("\033[3m" + s + "\033[0m");
    }
}