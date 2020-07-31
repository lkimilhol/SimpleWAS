package com.nhn.simplewas;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class SimpleHttpServer {
    public void start() throws Exception {
        ServerConfig config = ServerConfig.getInstance();
        InetSocketAddress addr = new InetSocketAddress(config.PORT);
        HttpServer serverTest = HttpServer.create(addr, 0);
        HandlerFactory.getInstance();

        serverTest.createContext("/", new SimpleHandler());
        serverTest.createContext("/Hello", new Hello());
        serverTest.createContext("/service.Hello", new com.nhn.service.Hello());
        serverTest.setExecutor(Executors.newCachedThreadPool());
        serverTest.start();
    }
}