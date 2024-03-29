package org.rizki.mufrizal.swagger.helper;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author rizki
 */
@Slf4j
public class LoggerHelper {

    public static void LogError(Exception e, String message) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        log.error(message, stringWriter);
    }
}
