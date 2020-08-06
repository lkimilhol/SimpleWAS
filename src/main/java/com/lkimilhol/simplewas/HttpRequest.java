package com.lkimilhol.simplewas;

import com.sun.net.httpserver.HttpExchange;

import java.io.InputStream;

import static com.nhn.simplewas.HandlerBase.GET;
import static com.nhn.simplewas.HandlerBase.POST;

public class HttpRequest {
    static final String POST_STRING_DELIMITER = "\r\n";
    static final String POST_STRING_KEY = "name";
    HttpExchange exchange;

    //TODO 에러코드 따로 class 정의 필요
    static final int NOT_FOUND_KEY = -99;
    static final String NOT_FOUND_KEY_TEXT = "Not Found Key ";
    static final int INVALID_METHOD = -100;
    static final String INVALID_METHOD_TEXT = "Invalid Method ";

    public HttpRequest(HttpExchange exchange) throws Exception {
        this.exchange = exchange;
    }

    public String getParameter(String key) throws Exception {
        String method = exchange.getRequestMethod();
        String v = "";
        v = switch (method) {
            case GET -> {
                String ret = "";
                String query = exchange.getRequestURI().getQuery();
                if (query != null ) {
                    String[] param = query.split("&");
                    for (String p : param) {
                        String[] kv = p.split("=");
                        if (key.equals(kv[0])) {
                            ret = kv[1];
                        }
                    }
                }
                if (ret == "") {throw new SimpleException(NOT_FOUND_KEY, NOT_FOUND_KEY_TEXT, exchange);}
                yield ret;
            }
            case POST -> {
                StringBuilder sb = new StringBuilder();
                InputStream ios = exchange.getRequestBody();
                int i;
                while ((i = ios.read()) != -1) {
                    sb.append((char) i);
                }
                String ret = "";
                String query = sb.toString().replaceAll(";", POST_STRING_DELIMITER);
                query = query.replaceAll(" ", "");
                query = query.replaceAll("\"", "");
                boolean valueFind = false;
                if (ret != null) {
                    String[] param = query.split(POST_STRING_DELIMITER);
                    for (String p : param) {
                        if (p.equals("")) continue;
                        if (valueFind == true) {
                            ret = p;
                            valueFind = false;
                            break;
                        }
                        if (p.startsWith(POST_STRING_KEY)) {
                            String[] kv = p.split("=");
                            if (key.equals(kv[1])) {
                                valueFind = true;
                            }
                        }
                    }
                }
                if (valueFind == false) {throw new SimpleException(NOT_FOUND_KEY, NOT_FOUND_KEY_TEXT, exchange);}
                yield ret;
            }
            default -> {
                throw new SimpleException(INVALID_METHOD, INVALID_METHOD_TEXT, exchange);
            }
        };
        return v;
    }
}
