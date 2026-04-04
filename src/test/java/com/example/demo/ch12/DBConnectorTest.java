package com.example.demo.ch12;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DBConnectorの動作確認テスト
 *
 * <p>
 * ポイント:
 * <ul>
 * <li>テスト毎にDBを初期化 (@BeforeEach)</li>
 * <li>AssertJで可読性の高いassert</li>
 * <li>@DisplayNameでテストの目的を明示</li>
 * </ul>
 * </p>
 */
class DBConnectorTest {

	/** テスト用H2インメモリDBの接続URL */
	private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

	/** デフォルトユーザー名 */
	private static final String USERNAME = "sa";

	/** デフォルトパスワード（空） */
	private static final String PASS = "";

	/**
	 * 各テスト前にDBを初期化
	 */
	@BeforeEach
	void initDb() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS); Statement stmt = con.createStatement()) {

			// 既存テーブル削除（毎回クリーンな状態に）
			stmt.execute("DROP ALL OBJECTS");
		}
	}

	/**
	 * DB接続が正常に取得できることを確認するテスト。
	 *
	 * <p>
	 * 観点：
	 * <ul>
	 * <li>Connectionオブジェクトがnullでないこと</li>
	 * <li>接続がクローズされていないこと</li>
	 * </ul>
	 * </p>
	 *
	 * @throws Exception 接続取得時に例外が発生した場合
	 */
	@Test
	@DisplayName("接続成功: Connectionが取得でき、クローズされていないこと")
	void testGetConnection_success() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
			assertNotNull(con, "Connectionがnullです");
			assertFalse(con.isClosed(), "Connectionが既にクローズされています");
			System.out.println("接続成功: " + con);
		}
	}

	/**
	 * 取得したConnectionが有効であることを確認するテスト。
	 *
	 * <p>
	 * 観点：
	 * <ul>
	 * <li>Connection#isValid により接続の疎通確認ができること</li>
	 * </ul>
	 * </p>
	 *
	 * @throws Exception 接続確認時に例外が発生した場合
	 */
	@Test
	@DisplayName("Connectionが有効であることを確認")
	void testConnection_isValid() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
			assertTrue(con.isValid(1), "Connectionが無効です");
		}
	}

	/**
	 * SQLの実行が正常に行えることを確認するテスト。
	 *
	 * <p>
	 * 観点：
	 * <ul>
	 * <li>テーブル作成が成功すること</li>
	 * <li>データのINSERTが成功すること</li>
	 * <li>SELECTで正しい結果が取得できること</li>
	 * </ul>
	 * </p>
	 *
	 * @throws Exception SQL実行時に例外が発生した場合
	 */
	@Test
	@DisplayName("SQL実行: テーブル作成、データ登録、SELECTで確認")
	void testExecuteQuery() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {

			Statement stmt = con.createStatement();
			// テーブル作成
			stmt.execute("CREATE TABLE test(id INT PRIMARY KEY, name VARCHAR(50))");
			// データ登録
			stmt.execute("INSERT INTO test VALUES (1, 'Taro')");
			// データ取得
			ResultSet rs = stmt.executeQuery("SELECT name FROM test WHERE id = 1");
			assertTrue(rs.next(), "データが取得できません");
			assertEquals("Taro", rs.getString("name"), "取得した値が不正です");
		}
	}

	/**
	 * 不正な接続先を指定した場合に例外が発生することを確認するテスト。
	 *
	 * <p>
	 * 観点：
	 * <ul>
	 * <li>接続失敗時にRuntimeExceptionがスローされること</li>
	 * </ul>
	 * </p>
	 */
	@Test
	@DisplayName("異常系: 不正な接続先でRuntimeExceptionが発生すること")
	void testGetConnection_failure() {
		RuntimeException ex = assertThrows(RuntimeException.class, () -> {
			DBConnector.getConnect("jdbc:h2:tcp://invalidhost/~/test", "sa", "");
		});
		System.out.println("期待通り失敗: " + ex.getMessage());
	}
}