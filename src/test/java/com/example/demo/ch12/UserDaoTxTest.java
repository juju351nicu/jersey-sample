package com.example.demo.ch12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.ch12.base.TransactionalDaoTestBase;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserDaoのトランザクションテスト<br>
 * DDLはDBによってrollback効かない<br>
 * 
 * H2はOKだけど：MySQL → CREATE TABLEはcommit扱いになることあり<br>
 * 注意点（重要）commitしないこと<br>
 * con.commit(); // ← 書いたら終わり（rollback効かない）<br>
 */
class UserDaoTxTest extends TransactionalDaoTestBase {

	private final UserDao sut = new UserDao();

	@Override
	protected void initData(Connection con) throws Exception {
		try (Statement stmt = con.createStatement()) {
			stmt.execute("CREATE TABLE IF NOT EXISTS users(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");
			stmt.execute("DELETE FROM users");
			stmt.execute("INSERT INTO users(name) VALUES('Ichiro')");
			stmt.execute("INSERT INTO users(name) VALUES('Jiro')");
		}
	}

	@Test
	@DisplayName("getList: 2件取得できる")
	void testGetList() throws Exception {
		List<String> users = sut.getList(con);

		assertThat(users).hasSize(2).containsExactly("Ichiro", "Jiro");
	}

	@Test
	@DisplayName("insert: 追加してもrollbackされる")
	void testInsert() throws Exception {
		sut.insert(con, "Saburou");

		List<String> users = sut.getList(con);

		assertThat(users).hasSize(3).containsExactly("Ichiro", "Jiro", "Saburou");
	}
}
