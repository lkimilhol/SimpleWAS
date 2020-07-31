package com.nhn.simplewas;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.StringWriter;

public class SimpleException extends Exception{
    public SimpleException(int errorCode, String errorText, HttpExchange exchange) throws Exception {
        java.io.Writer writer = new StringWriter();
        writer.write(errorText);
        writer.write("\n");
        writer.write("ErrorCode: ");
        writer.write(Integer.toString(errorCode));
        OutputStream os = exchange.getResponseBody();
        exchange.sendResponseHeaders(errorCode, writer.toString().length());
        os.write(writer.toString().getBytes());
        os.close();
    }
}
