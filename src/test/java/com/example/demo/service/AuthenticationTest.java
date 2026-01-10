package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.example.demo.dao.AccountDao;
import com.example.demo.sample.model.Account;

/**
 * リスト20.7 インタフェースとスタブによるテスト
 * 
 * WEB三層構造アーキテクチャでは、DAOなどのインターフェースを利用してデータベースにアクセスするパーシステンス層（永続化層）を分離した設計を行います。<br/>
 * このような構造を採用した場合、サービス層のユニットテストでは、次の2つのユニットテストの戦略があります。<br/>
 * ・データベースを使ってパーシステンス層まで通してテストする戦略<br/>
 * ・依存するパーシステンス層をスタブで置き換えてサービス層を独立してテストする戦略<br/>
 * どちらの戦略を選択するかは、プロジェクトの規模や方針に依存します。<br/>
 * スローテスト問題や開発体制上の問題がないのであれば、データベースやリアルオブジェクトを利用すべきです。<br/>
 * なぜならば、よりプロダクション環境に近い環境でのユニットテストとなる為です。<br/>
 * 逆に、SQLExceptionの発生時に正しく例外処理を行っているかなど、外部システムに依存する検証はテストダブルを用いる方が便利です。<br/>
 * 下記では、AccountDaoオブジェクトをスタブオブジェクトで置き換えてテストしています。<br/>
 * テストケースとしては、３パターン行えば十分に網羅できるため、それぞれについてのテストのコンテキストを定義しました。<br/>
 */
class AuthenticationTest {

	@Nested
	class アカウントが存在しない場合 {
		Authentication sut;

		@BeforeEach
		void setUp() throws Exception {
			sut = new Authentication();
			sut.dao = mock(AccountDao.class);
			when(sut.dao.findOrNull("user001")).thenReturn(null);
		}

		@Test
		@DisplayName("AccountDaoがnullを返す場合、nullを返す。")
		void authenticateはnullを返す() throws Exception {
			assertNull(sut.authenticate("user001", "pw123"));
		}
	}

	@Nested
	class アカウントが存在しパスワードが一致する場合 {
		Authentication sut;
		Account account;

		@BeforeEach
		void setUp() throws Exception {
			sut = new Authentication();
			sut.dao = mock(AccountDao.class);
			account = new Account("user001", "pw123");
			when(sut.dao.findOrNull("user001")).thenReturn(account);
		}

		@Test
		@DisplayName("AccountDaoがAccountオブジェクトを返し、パスワードが一致する場合、authenticateメソッドはAccountオブジェクトを返す。")
		void authenticateはaccountを返す() throws Exception {
			// 2つの参照が同じインスタンスであることを検証
			assertSame(sut.authenticate("user001", "pw123"), account, "object1とobject2は同じインスタンスであるべきです");
		}
	}

	@Nested
	class アカウントが存在するがパスワードが一致しない場合 {
		Authentication sut;
		Account account;

		@BeforeEach
		void setUp() throws Exception {
			sut = new Authentication();
			sut.dao = mock(AccountDao.class);
			account = new Account("user001", "PW999");
			when(sut.dao.findOrNull("user001")).thenReturn(account);
		}

		@Test
		@DisplayName("AccountDaoがAccountオブジェクトを返し、パスワードが一致しない場合、authenticateメソッドはnullを返す。")
		void authenticateはnullを返す() throws Exception {
			assertNull(sut.authenticate("user001", "pw123"));
		}
	}
}
