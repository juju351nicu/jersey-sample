package com.example.demo.ch12;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import java.sql.Statement;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * リスト12.9 assertThatによるITableの比較検証<br>
 * リスト12.10 コンテキストごとに整理したUserDaoTest<br>
 * UserDaoのテスト（DbUnit不要、H2メモリDB + DBConnector使用）<br>
 * 
 * @author shuji.w6e
 */
class UserDaoMemoryTest {

	private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private static final String USERNAME = "sa";
	private static final String PASS = "";

	private UserDao sut;

	/**
	 * 各テスト前にUserDaoとDBを初期化
	 * <p>
	 * H2メモリDBにusersテーブルを作り、初期データを追加
	 * </p>
	 */
	@BeforeEach
	void setUp() throws Exception {
		sut = new UserDao();

		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS); Statement stmt = con.createStatement();) {

			// テーブルをクリーンに作り直す
			stmt.execute("DROP TABLE IF EXISTS users");
			stmt.execute("CREATE TABLE users(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");

			// 初期データ追加
			stmt.execute("INSERT INTO users(name) VALUES('Ichiro')");
			stmt.execute("INSERT INTO users(name) VALUES('Jiro')");
		}
	}

	@Test
	@DisplayName("getList: 2件取得できる")
	void testGetList() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
			List<String> actual = sut.getList(con);

			assertThat(actual).isNotNull().hasSize(2).containsExactly("Ichiro", "Jiro");
		}
	}

	@Test
	@DisplayName("insert: 1件追加できる")
	void testInsert() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
			sut.insert(con, "Saburou");

			List<String> users = sut.getList(con);

			assertThat(users).hasSize(3).containsExactly("Ichiro", "Jiro", "Saburou");
		}
	}
}
