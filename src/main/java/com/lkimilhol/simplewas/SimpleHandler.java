package com.lkimilhol.simplewas;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SimpleHandler implements HttpHandler {
    protected static final String PORT_DELIMITER = ":";
    protected static final String PARENT_DIR_DELIMITER = ":";
    protected static final String LOCALHOST = "localhost";
    protected static final String HOST_A = "a.com";
    protected static final String HOST_B = "b.com";

    @Override
    public void handle(HttpExchange exchange) {
        Headers headers = exchange.getRequestHeaders();
        final String host = headers.get("Host").get(0).split(PORT_DELIMITER)[0];
        try {
            HandlerFactory.getInstance().getHandler(host, exchange).processRequest();
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            HandlerBase.LOGGER_ERROR_FILE_APPEND.error(stringWriter.toString());
            HandlerBase.LOGGER_ERROR_STDOUT.error(stringWriter.toString());
        }
    }

}
