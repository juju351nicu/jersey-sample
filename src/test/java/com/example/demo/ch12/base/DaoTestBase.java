package com.example.demo.ch12.base;

import org.junit.jupiter.api.BeforeEach;

import com.example.demo.ch12.DBConnector;

import java.sql.Connection;
import java.sql.Statement;

/**
 * DAOテストの共通基底クラス
 * <p>
 * ・H2メモリDBを使用<br>
 * ・DBConnectorで接続<br>
 * ・各DAOのテストで継承可能
 * </p>
 */
public abstract class DaoTestBase {

	protected static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	protected static final String USERNAME = "sa";
	protected static final String PASS = "";

	/**
	 * 各テスト前にDBを初期化する
	 * <p>
	 * ・テーブルをクリーン作成<br>
	 * ・初期データ投入（DAOごとにオーバーライド可能）
	 * </p>
	 */
	@BeforeEach
	void initDb() throws Exception {
		try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS); Statement stmt = con.createStatement()) {

			// DAOごとに初期化SQLを提供
			for (String sql : getInitSqls()) {
				stmt.execute(sql);
			}
		}
	}

	/**
	 * DAOごとの初期化SQLを返す
	 * <p>
	 * 例：CREATE TABLE, INSERT文
	 * </p>
	 *
	 * @return SQL文字列配列
	 */
	protected abstract String[] getInitSqls();
}
