package com.example.demo.ch12.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.example.demo.ch12.DBConnector;

import java.sql.Connection;

/**
 * トランザクション付きDAOテスト基底クラス
 *
 * <p>
 * 各テストはトランザクション内で実行され、 テスト終了後に必ずROLLBACKされる。
 * </p>
 */
public abstract class TransactionalDaoTestBase {

	protected static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	protected static final String USERNAME = "sa";
	protected static final String PASS = "";

	protected Connection con;

	/**
	 * テスト前処理 ・Connection取得 ・AutoCommit OFF（トランザクション開始）
	 */
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnector.getConnect(URL, USERNAME, PASS);
		con.setAutoCommit(false);

		// 初期データ作成（初回のみ or 毎回でもOK）
		initData(con);
	}

	/**
	 * テスト後処理 ・必ずロールバック
	 */
	@AfterEach
	void tearDown() throws Exception {
		if (con != null) {
			con.rollback(); // ← 最重要
			con.close();
		}
	}

	/**
	 * 初期データ作成（DAOごとに実装）
	 */
	protected abstract void initData(Connection con) throws Exception;
}