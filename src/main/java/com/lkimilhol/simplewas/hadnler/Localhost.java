package com.lkimilhol.simplewas.hadnler;

import com.lkimilhol.simplewas.HandlerBase;
import com.lkimilhol.simplewas.ServerConfig;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Localhost extends HandlerBase {
    private HttpExchange exchange;
    private ServerConfig config;
    public Localhost(HttpExchange exchange) throws Exception {
        this.exchange = exchange;
        this.config = ServerConfig.getInstance();
    }

    public void processRequest() {
        String method = exchange.getRequestMethod();
        String requestedUri = exchange.getRequestURI().getPath();
        String resp = "";
        String filePath = null;
        String responseFileName = "";
        int responseCode = -1;
        try {
            switch (method) {
                case GET -> {
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                    String fileName = getUrlPath(requestedUri);

                    if (fileName.equals("")) {
                        //request root page
                        filePath = config.rootDir.getPath();
                        responseFileName = config.FILE_NAME_ROOT;
                        responseCode = RESPONSE_SUCCESS;
                    } else if (fileName.equals(PARENT_DIR_DELIMITER)|| fileName.endsWith(config.FILE_FORBIDDEN_EXE)) {
                        //request forbidden
                        filePath = config.errorDir.getPath();
                        responseFileName = config.FILE_NAME_403;
                        responseCode = RESPONSE_PAGE_FORBIDDEN;
                    } else {
                        //request not found page
                        filePath = config.errorDir.getPath();
                        responseFileName = config.FILE_NAME_404;
                        responseCode = RESPONSE_PAGE_NOT_FOUND;
                    }
                }
                case POST -> {
                    //TODO Write POST Response
                    resp = getGetResponse(config.rootDir.getPath(), config.FILE_NAME_ROOT);
                    exchange.sendResponseHeaders(RESPONSE_SUCCESS, resp.length());
                }
                default -> {
                    //request method error
                    filePath = config.errorDir.getPath();
                    responseFileName = config.FILE_NAME_500;
                    responseCode = RESPONSE_PAGE_SERVER_ERROR;
                }
            }
            resp = getGetResponse(filePath, responseFileName);
            exchange.sendResponseHeaders(responseCode, resp.length());
            OutputStream os = exchange.getResponseBody();
            os.write(resp.getBytes());
            os.close();
            packetLog(responseCode, method, requestedUri, responseFileName);
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            LOGGER_ERROR_FILE_APPEND.error(stringWriter.toString());
            LOGGER_ERROR_STDOUT.error(stringWriter.toString());
        }
    }
}
