package com.nhn.simplewas;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;

public class TestHandlerBase {
    @Test
    public void TestLoggerFactory(){
        Logger a  = LoggerFactory.getLogger("ERROR_STDOUT");
        Logger b = LoggerFactory.getLogger("ERROR_FILE_APPEND");
        Logger c = LoggerFactory.getLogger("PACKET");
        assertNotNull(a);
        assertNotNull(b);
        assertNotNull(c);
    }

    @Test
    public void TestGetResponse() throws Exception{
        ServerConfig config = ServerConfig.getInstance();
        String line = "";
        String resp = "";
        File file = new File(config.rootDir.getPath(), config.FILE_NAME_ROOT);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        while ((line = bufferedReader.readLine()) != null) {
            resp += line;
        }
        assertNotNull(resp);
    }
}
