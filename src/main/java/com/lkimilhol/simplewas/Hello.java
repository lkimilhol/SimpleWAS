package com.lkimilhol.simplewas;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Hello implements SimpleServlet {
    @Override
    public Writer service(HttpRequest req, HttpResponse res) throws Exception {
        java.io.Writer writer = res.getWriter();
        writer.write("Hello, ");
        writer.write(req.getParameter("name"));
        return writer;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            HttpRequest req = new HttpRequest(exchange);
            HttpResponse res = new HttpResponse();
            Writer writer = service(req, res);
            OutputStream os = exchange.getResponseBody();
            exchange.sendResponseHeaders(HandlerBase.RESPONSE_SUCCESS, writer.toString().length());
            os.write(writer.toString().getBytes());
            os.close();
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            HandlerBase.LOGGER_ERROR_FILE_APPEND.error(stringWriter.toString());
            HandlerBase.LOGGER_ERROR_STDOUT.error(stringWriter.toString());
        }
    }
}
