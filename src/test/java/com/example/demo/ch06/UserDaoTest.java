package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト6.4 UserDaoクラスのテストクラス
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
