package com.lkimilhol.simplewas;

import com.nhn.simplewas.hadnler.Localhost;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestHandlerFactory {
    @Test
    public void TestStringWriter() throws Exception{
        HandlerFactory.getInstance();
        ServerConfig config = ServerConfig.getInstance();
        final String host = config.HOST_LOCALHOST;
        Localhost instance = (Localhost) HandlerFactory.getHandler(host , null);
        assertNotNull(instance);
    }
}
