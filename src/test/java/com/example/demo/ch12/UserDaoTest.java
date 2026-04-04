package com.example.demo.ch12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.ch12.base.DaoTestBase;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * * リスト12.9 assertThatによるITableの比較検証<br>
 * リスト12.10 コンテキストごとに整理したUserDaoTest UserDaoのテスト<br>
 */
class UserDaoTest extends DaoTestBase {

	private final UserDao sut = new UserDao();

	@Override
	protected String[] getInitSqls() {
		return new String[] { "DROP TABLE IF EXISTS users",
				"CREATE TABLE users(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))",
				"INSERT INTO users(name) VALUES('Ichiro')", "INSERT INTO users(name) VALUES('Jiro')" };
	}

	@Test
	@DisplayName("getList: 2件取得できる")
	void testGetList() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
			List<String> users = sut.getList(con);
			assertThat(users).containsExactly("Ichiro", "Jiro");
		}
	}

	@Test
	@DisplayName("insert: 1件追加できる")
	void testInsert() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
			sut.insert(con, "Saburou");

			List<String> users = sut.getList(con);
			assertThat(users).containsExactly("Ichiro", "Jiro", "Saburou");
		}
	}
}