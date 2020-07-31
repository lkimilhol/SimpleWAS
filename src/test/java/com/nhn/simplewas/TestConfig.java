package com.nhn.simplewas;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestConfig {
    @Test
    public void TestPort(){
        Config config = ConfigFactory.load();
        int port = config.getInt("port");
        assertTrue(0 < port);
        assertTrue(port < Integer.MAX_VALUE);
    }

    @Test
    public void TestThreadNum(){
        Config config = ConfigFactory.load();
        int threadNum = config.getInt("thread_num");
        assertTrue(0 < threadNum);
        assertTrue(threadNum < Integer.MAX_VALUE);
    }

    @Test
    public void TestFileRoot(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("file_root");
        assertNotNull(fileName);
    }

    @Test
    public void TestFile404(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("file_404");
        assertNotNull(fileName);
    }

    @Test
    public void TestFile403(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("file_403");
        assertNotNull(fileName);
    }

    @Test
    public void TestFile500(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("file_500");
        assertNotNull(fileName);
    }

    @Test
    public void TestFileForbiddenExe(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("file_forbidden_exe");
        assertNotNull(fileName);
    }

    @Test
    public void TestHostLocalHost(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("host_localhost");
        assertNotNull(fileName);
    }

    @Test
    public void TestHostACom(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("host_a_com");
        assertNotNull(fileName);
    }

    @Test
    public void TestHostBCom(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("host_b_com");
        assertNotNull(fileName);
    }

    @Test
    public void TestServletName(){
        Config config = ConfigFactory.load();
        String fileName = config.getString("servlet_name");
        assertNotNull(fileName);
    }

    @Test
    public void TestServletMapping(){
        Config config = ConfigFactory.load();
        Object fileName = config.getObject("servlet_mapping");
        assertNotNull(fileName);
    }

    @Test
    public void TestRootDir() throws Exception {
        ServerConfig config = ServerConfig.getInstance();
        File dir = new File(new File("").getAbsolutePath() + config.ROOT_DIR);
        assertTrue(dir.isDirectory());
    }

    @Test
    public void TestErrorDir() throws Exception {
        ServerConfig config = ServerConfig.getInstance();
        File dir = new File(new File("").getAbsolutePath() + config.ERROR_DIR);
        assertTrue(dir.isDirectory());
    }
}
