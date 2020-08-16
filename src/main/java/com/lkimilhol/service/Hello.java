package com.lkimilhol.service;

import com.lkimilhol.simplewas.HttpRequest;
import com.lkimilhol.simplewas.HttpResponse;
import com.lkimilhol.simplewas.SimpleServlet;
import com.lkimilhol.simplewas.HandlerBase;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

public class Hello implements SimpleServlet {
    @Override
    public Writer service(HttpRequest req, HttpResponse res) throws Exception {
        java.io.Writer writer = res.getWriter();
        Date date = new Date();
        writer.write(date.toString());
        return writer;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try (OutputStream os = exchange.getResponseBody()) {
            HttpRequest req = new HttpRequest(exchange);
            HttpResponse res = new HttpResponse();
            Writer writer = service(req, res);

            exchange.sendResponseHeaders(HandlerBase.RESPONSE_SUCCESS, writer.toString().length());
            os.write(writer.toString().getBytes());
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            HandlerBase.LOGGER_ERROR_FILE_APPEND.error(stringWriter.toString());
            HandlerBase.LOGGER_ERROR_STDOUT.error(stringWriter.toString());
        }
    }
}
