package com.lkimilhol.simplewas;

import com.lkimilhol.simplewas.hadnler.HostA;
import com.lkimilhol.simplewas.hadnler.HostB;
import com.lkimilhol.simplewas.hadnler.Localhost;
import com.sun.net.httpserver.HttpExchange;

public class HandlerFactory {
    private static HandlerFactory instance = null;
    public static synchronized HandlerFactory getInstance() {
        if (instance==null) {
            instance = new HandlerFactory();
        }
        return instance;
    }

    private HandlerFactory() {
    }

    public static HandlerBase getHandler(String type, HttpExchange exchange) throws Exception {
        ServerConfig config = ServerConfig.getInstance();
        //TODO 리플렉션 사용하여 클래스 미리 동적 로딩 후 return 되도록 수정 필요
        if (type.equals(config.HOST_A_COM)) {return new HostA();}
        else if (type.equals(config.HOST_B_COM)) {return new HostB();}
        else {return new Localhost(exchange);}
    }
}
