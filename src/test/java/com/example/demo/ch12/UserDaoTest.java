package com.example.demo.ch12;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Nested;
import org.dbunit.dataset.ITable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.InputStream;
import java.util.List;

/**
 * リスト12.9 assertThatによるITableの比較検証 リスト12.10 コンテキストごとに整理したUserDaoTest
 * 
 * @author shuji.w6e
 */
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * JUnit5用 UserDaoTest
 */
class UserDaoTest {

	// --- H2 Databaseサーバ起動 ---
	@RegisterExtension
	static H2DatabaseServerExtension server = new H2DatabaseServerExtension("./h2data", "db", "ut", 9092);

	// --- DbUnitテストデータ ---
	@RegisterExtension
	static DbUnitTesterExample tester = new DbUnitTesterExample("org.h2.Driver",
			"jdbc:h2:tcp://localhost:9092/./db;SCHEMA=ut", "sa", "", "ut") {
		@Override
		protected void before() throws Exception {
			execute("DROP TABLE IF EXISTS users");
			execute("CREATE TABLE users(id INT AUTO_INCREMENT, name VARCHAR(255))");
		}

		@Override
		protected IDataSet createDataSet() throws Exception {
			return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream("fixtures.xml"));
		}

		private void execute(String sql) throws Exception {
			try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/./db;SCHEMA=ut", "sa",
					"")) {
				conn.createStatement().execute(sql);
			}
		}
	};

	UserDao sut;

	@BeforeEach
	void setUp() {
		sut = new UserDao();
	}

	@Test
	void getListで2件取得できる事() throws Exception {
		List<String> actual = sut.getList();
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), is(2));
		assertThat(actual.get(0), is("Ichiro"));
		assertThat(actual.get(1), is("Jiro"));
	}

	@Test
	void insertで1件追加できる() throws Exception {
		sut.insert("Saburou");

		ITable actual = tester.getConnection().createDataSet().getTable("users");
		InputStream expectedIn = getClass().getResourceAsStream("expected.xml");
		ITable expected = new FlatXmlDataSetBuilder().build(expectedIn).getTable("users");

		assertThat(actual.getRowCount(), is(expected.getRowCount()));
		assertThat(actual.getValue(0, "name"), is("Ichiro")); // 例: 1行目
	}
}
