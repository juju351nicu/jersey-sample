package com.example.demo.ch12;

import org.h2.tools.Server;
import org.h2.util.JdbcUtils;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Connection;
import java.util.Properties;

/**
 * リスト12.1 H2 Databaseサーバを起動／停止するルール
 * @author shuji.w6e
 */
public class H2DatabaseServer implements BeforeAllCallback, AfterAllCallback {

    private final String baseDir;
    private final String dbName;
    private final String schemaName;
    private Server server;

    public H2DatabaseServer(String baseDir, String dbName, String schemaName) {
        this.baseDir = baseDir;
        this.dbName = dbName;
        this.schemaName = schemaName;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // DBサーバ起動
        server = Server.createTcpServer("-baseDir", baseDir);
        server.start();

        // スキーマ作成
        Properties props = new Properties();
        props.setProperty("user", "sa");
        props.setProperty("password", "");

        String url = "jdbc:h2:" + server.getURL() + "/" + dbName;
        Connection conn = org.h2.Driver.load().connect(url, props);

        try {
            conn.createStatement()
                .execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);
        } finally {
            JdbcUtils.closeSilently(conn);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        if (server != null) {
            server.shutdown();
        }
    }
}