package com.nhn.simplewas;

import com.sun.net.httpserver.HttpHandler;

import java.io.Writer;

public interface SimpleServlet extends HttpHandler {
    Writer service(HttpRequest req, HttpResponse res) throws Exception;
}