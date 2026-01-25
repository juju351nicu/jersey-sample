package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト6.4 UserDaoクラスのテストクラス ・共通のデータに着目する。<br/>
 * 入力値や期待値などのテストで、使用するデータに着目し、共通のテストケースをグループ化するパターンです。<br/>
 * 最もわかりやすい例は、データベース接続を行うクラスのユニットテストです。<br/>
 * データベース接続を行うユニットテストでは、テストの実行前にデータベースを特定の状態に設定する必要があります。<br/>
 * 例えば「あるテーブルが空の場合」や「10件のデータがある場合」などです。<br/>
 * その際、テウとケースごとにデータベースを初期化するコードを記述するのでは、多くの重複となります。<br/>
 * また、テスト対象クラスには取得/更新/削除などいくつかのメソッドがある事が多いため、同じデータベースの状態に対し、各メソッドのテストを行う方がわかりやすくなります。<br/>
 * UserDaoは、ユーザ情報をデータベースから取得するためのUserDaoクラスです。<br/>
 * このクラスのテストクラスをデータベースの値に着目して構造化すると下記（UserDaoTestクラス）のようになります。<br/>
 * このテストクラスでは、usersテーブルのレコードが0件である場合と100件である場合の2つの状態について、それぞれgetListメソッドのテストを行っています。<br/>
 * 同じgetListメソッドであっても、それぞれの状態で返す値が異なるでしょう。<br/>
 * このことはgetListメソッド以外のメソッドについても同様です。<br/>
 * 従って、テストをデータベースの状態ごとに整理しておくことで、テストケースの追加も状態の追加お行いやすくなります。<br/>
 * 
 * @author shuji.w6e
 */
public class UserDaoTest {
	@Nested
	class テーブルが空の場合 {
		UserDao sut;

		@BeforeEach
		public void setUp() throws Exception {
			DbUtils.drop("users");
			sut = new UserDao();
		}

		@Disabled("未実装")
		@Test
		public void getListで0件取得できる() throws Exception {
			List<User> actual = sut.getList();
			assertNull(actual);
			assertEquals(actual.size(), 0);
		}
	}

	@Nested
	class テーブルにサンプルデータが100件含まれる場合 {
		UserDao sut;

		@BeforeEach
		public void setUp() throws Exception {
			DbUtils.drop("users");
			DbUtils.insert("users", getClass().getResource("users.yaml"));
			sut = new UserDao();
		}

		@Disabled("未実装")
		@Test
		public void getListで100件取得できる() throws Exception {
			List<User> actual = sut.getList();
			assertNull(actual);
			assertEquals(actual.size(), 100);
		}
	}

}
