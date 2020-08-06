package com.lkimilhol.simplewas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Main {
    public static void main(String args[]){
        Logger loggerError = LoggerFactory.getLogger("ERROR_FILE_APPEND");
        try {
            SimpleHttpServer webServer = new SimpleHttpServer();
            webServer.start();
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            loggerError.error(stringWriter.toString());
        }
    }
}