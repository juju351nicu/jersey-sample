package com.example.demo.ch12;

import org.h2.tools.Server;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * H2 DatabaseサーバをJUnit5 Extensionで起動／停止する
 */
public class H2DatabaseServerExtension implements BeforeAllCallback, AfterAllCallback {

    private final String baseDir;
    private final String dbName;
    private final String schemaName;
    private final int tcpPort;

    private Server server;

    public H2DatabaseServerExtension(String baseDir, String dbName, String schemaName, int tcpPort) {
        this.baseDir = baseDir;       // ./h2data のように必ず相対か絶対
        this.dbName = dbName;
        this.schemaName = schemaName;
        this.tcpPort = tcpPort;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        server = Server.createTcpServer(
                "-tcp", "-tcpAllowOthers",
                "-tcpPort", String.valueOf(tcpPort),
                "-baseDir", baseDir   // ← ここが必須
        ).start();

        if (!server.isRunning(true)) {
            throw new IllegalStateException("H2 server failed to start!");
        }

        System.out.println("H2 server running at: " + server.getURL());

        Properties props = new Properties();
        props.setProperty("user", "sa");
        props.setProperty("password", "");

        // 明示的に相対パスをつける
        String url = "jdbc:h2:tcp://localhost:" + tcpPort + "/./" + dbName;

        try (Connection conn = DriverManager.getConnection(url, props)) {
            conn.createStatement().execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (server != null) {
            server.stop();
            System.out.println("H2 server stopped");
        }
    }

    public String getJdbcUrl() {
        return "jdbc:h2:tcp://localhost:" + tcpPort + "/./" + dbName;
    }
}
