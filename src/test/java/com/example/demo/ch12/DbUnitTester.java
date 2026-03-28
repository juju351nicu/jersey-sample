package com.example.demo.ch12;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DbUnitTester extends AbstractDatabaseTester implements BeforeEachCallback, AfterEachCallback {

	private final String connectionUrl;
	private final String username;
	private final String password;

	public DbUnitTester(String driverClass, String connectionUrl, String username, String password, String schema) {
		super(schema);
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;

		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// ==============================
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		before();
		setDataSet(createDataSet());
		onSetup();
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		try {
			after();
		} finally {
			onTearDown();
		}
	}

	// ==============================
	@Override
	public IDatabaseConnection getConnection() throws Exception {
		Connection conn;
		if (username == null && password == null) {
			conn = DriverManager.getConnection(connectionUrl);
		} else {
			conn = DriverManager.getConnection(connectionUrl, username, password);
		}

		DatabaseConnection dbConnection = new DatabaseConnection(conn, getSchema());

		DatabaseConfig config = dbConnection.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());

		return dbConnection;
	}

	protected void executeQuery(String sql) throws Exception {
		Connection conn = getConnection().getConnection();
		conn.createStatement().execute(sql);
		conn.commit();
		conn.close();
	}

	// フックメソッド
	protected void before() throws Exception {
	}

	protected void after() throws Exception {
	}

	protected abstract IDataSet createDataSet() throws Exception;
}