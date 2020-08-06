package com.lkimilhol.simplewas;

import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    private static ServerConfig instance = null;
    public static final com.typesafe.config.Config CONFIG;
    public static final int PORT;
    public static final int THREAD_NUM;
    public static final String ROOT_DIR;
    public static final String ERROR_DIR;
    public static final String FILE_NAME_ROOT;
    public static final String FILE_NAME_404;
    public static final String FILE_NAME_403;
    public static final String FILE_NAME_500;
    public static final String FILE_FORBIDDEN_EXE;
    public static final String SERVLET_NAME;
    public static final String HOST_LOCALHOST;
    public static final String HOST_A_COM;
    public static final String HOST_B_COM;
    public File rootDir;
    public File errorDir;

    public static synchronized ServerConfig getInstance() throws Exception {
        if (instance==null) {
            instance = new ServerConfig();
        }
        return instance;
    }

    static {
        CONFIG = ConfigFactory.load();
        PORT = CONFIG.getInt("port");
        THREAD_NUM = CONFIG.getInt("thread_num");
        ROOT_DIR = CONFIG.getString("root_dir");
        ERROR_DIR = CONFIG.getString("error_dir");
        FILE_NAME_ROOT = CONFIG.getString("file_root");
        FILE_NAME_404 = CONFIG.getString("file_404");
        FILE_NAME_403 = CONFIG.getString("file_403");
        FILE_NAME_500 = CONFIG.getString("file_500");
        FILE_FORBIDDEN_EXE = CONFIG.getString("file_forbidden_exe");
        SERVLET_NAME = CONFIG.getString("servlet_name");
        HOST_LOCALHOST = CONFIG.getString("host_localhost");
        HOST_A_COM = CONFIG.getString("host_a_com");
        HOST_B_COM = CONFIG.getString("host_a_com");
    }

    private ServerConfig() throws Exception {
        this.rootDir = new File(new File("").getAbsolutePath() + ROOT_DIR);
        if (!rootDir.isDirectory()) {
            throw new IOException(rootDir + " does not exist as a directory");
        }
        this.errorDir = new File(new File("").getAbsolutePath() + ERROR_DIR);
        if (!errorDir.isDirectory()) {
            throw new IOException(errorDir + " does not exist as a directory");
        }
    }
}
