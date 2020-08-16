package com.lkimilhol.simplewas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

public abstract class HandlerBase {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final Logger LOGGER_PACKET;
    public static final Logger LOGGER_ERROR_STDOUT;
    public static final Logger LOGGER_ERROR_FILE_APPEND;
    public static final int RESPONSE_SUCCESS = 200;
    public static final int RESPONSE_PAGE_NOT_FOUND = 404;
    public static final int RESPONSE_PAGE_FORBIDDEN = 403;
    public static final int RESPONSE_PAGE_SERVER_ERROR = 500;
    private static final String PACKET_LOG_DATE_PREFIX = "DATE: ";
    private static final String PACKET_LOG_RESPONSE_CODE_PREFIX = "RESPONSE_CODE: ";
    private static final String PACKET_LOG_METHOD_PREFIX = "METHOD: ";
    private static final String PACKET_LOG_URL_PREFIX = "REQUEST_URL: ";
    private static final String PACKET_LOG_RESPONSE_FILE_NAME_PREFIX = "RESPONSE_FILE_NAME: ";
    protected static final String PARENT_DIR_DELIMITER = ":";

    static {
        LOGGER_ERROR_STDOUT = LoggerFactory.getLogger("ERROR_STDOUT");
        LOGGER_ERROR_FILE_APPEND = LoggerFactory.getLogger("ERROR_FILE_APPEND");
        LOGGER_PACKET = LoggerFactory.getLogger("PACKET");
    }

    //TODO depth가 길어질때 처리 필요
    protected String getUrlPath(String line) {
        String[] urlPath = line.split("/");
        if (urlPath.length == 0){
            return "";
        }
        return urlPath[1];
    }

    protected String getGetResponse(String path, String FileName) throws Exception {
        String line;
        String resp = "";
        File file = new File(path+ "/" + FileName);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = bufferedReader.readLine()) != null) {
                resp += line;
            }
        }
        return resp;
    }

    protected void packetLog(int responseCode, String method, String url, String responseFileName) {
        Date time = new Date();
        if (responseCode == RESPONSE_SUCCESS) {
            LOGGER_PACKET.debug(PACKET_LOG_DATE_PREFIX + time.toString());
            LOGGER_PACKET.debug(PACKET_LOG_RESPONSE_CODE_PREFIX + responseCode);
            LOGGER_PACKET.debug(PACKET_LOG_METHOD_PREFIX + method);
            LOGGER_PACKET.debug(PACKET_LOG_URL_PREFIX + url);
            LOGGER_PACKET.debug(PACKET_LOG_RESPONSE_FILE_NAME_PREFIX + responseFileName);
        } else {
            LOGGER_PACKET.error(PACKET_LOG_DATE_PREFIX + time.toString());
            LOGGER_PACKET.error(PACKET_LOG_RESPONSE_CODE_PREFIX + responseCode);
            LOGGER_PACKET.error(PACKET_LOG_METHOD_PREFIX + method);
            LOGGER_PACKET.error(PACKET_LOG_URL_PREFIX + url);
            LOGGER_PACKET.error(PACKET_LOG_RESPONSE_FILE_NAME_PREFIX + responseFileName);
        }
    }

    public abstract void processRequest() throws Exception;
}
