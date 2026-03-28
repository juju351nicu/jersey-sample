package com.example.demo.ch12;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * JUnit5用 DbUnitTester Extension
 */
public abstract class DbUnitTesterExample implements BeforeEachCallback, AfterEachCallback {

    private final String driverClass;
    private final String url;
    private final String username;
    private final String password;
    private final String schema;

    protected IDatabaseConnection connection;

    public DbUnitTesterExample(String driverClass, String url, String username, String password, String schema) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
        this.schema = schema;

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    /** 各テスト前に呼ばれる */
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Connection conn;
        if (username == null && password == null) {
            conn = DriverManager.getConnection(url);
        } else {
            conn = DriverManager.getConnection(url, username, password);
        }
        connection = new DatabaseConnection(conn, schema);

        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());

        before();  // サブクラスでの事前処理
        IDataSet dataSet = createDataSet();
        if (dataSet != null) {
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        }
    }

    /** 各テスト後に呼ばれる */
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        after(); // サブクラスでの後処理
        if (connection != null) {
            connection.close();
        }
    }

    /** テスト前に実行するSQLなど */
    protected void before() throws Exception {}

    /** テスト後に追加処理があればオーバーライド */
    protected void after() throws Exception {}

    /** データセットを返す */
    protected abstract IDataSet createDataSet() throws Exception;

    /** 接続を取得 */
    public IDatabaseConnection getConnection() {
        return connection;
    }
}
