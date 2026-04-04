package com.example.demo.ch12;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 12.2 H2 Databaseサーバを起動／停止するルール<br>
 * データベースを扱うクラスのユニットテストは、データベースサーバが起動していなければ実行できません。<br>
 * この為、ローカル環境やテスト環境でデータベースサーバをあらかじめ起動させておきます。<br>
 * もし、データベースの起動や停止をテストの一部として組み込むことができれば、テスト環境や開発環境にプロジェクトを展開するだけで、ユニットテストを完全に自動化できます。<br>
 * このようなデータベースサーバや軌道の自動化は、OracleやPostgreSQLなどの本格的なデータベースサーバで行うことは難しいでしょう。<br>
 * しかしながら、軽量で組み込み用途でも利用できるH2 Databaseを利用すれば、簡単に実現できます。<br>
 * 例えば、開発環境やテスト環境ではH2
 * Databaseを利用し、ステージング環境やプロダクション環境ではPostgreSQLを利用するといった開発もできます。<br>
 * DB接続ユーティリティクラス<br>
 * リスト12.1 H2DatabaseServerクラスはわかりづらい為、削除済み<br>
 *
 *
 * <p>
 * 本番・テストで異なるDB設定を利用できるように、 接続情報は外部から渡す設計にしている<br>
 * </p>
 */
public final class DBConnector {

	private DBConnector() {
		// インスタンス化防止
	}

	/**
	 * DB接続を取得する
	 *
	 * @param url  JDBC URL
	 * @param user ユーザー名
	 * @param pass パスワード
	 * @return Connection
	 */
	public static Connection getConnect(String url, String user, String pass) {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new RuntimeException("DB接続に失敗しました", e);
		}
	}
}