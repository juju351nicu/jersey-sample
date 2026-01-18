package com.example.demo.ch06.withoutrule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * リスト6.8 複雑な共通処理が記述されたテストクラス
 * 
 * @author shuji.w6e
 */
class UserDaoTest {
//	private UserDao sut;
	private InMemoryDB db;

	@BeforeEach
	void setUp() throws Exception {
		db = new InMemoryDB();
		db.start();
//		sut = new UserDao();
	}

	@AfterEach
	void tearDown() throws Exception {
		db.shutdownNow();
	}

	@Test
	void getListは0件を返す() throws Exception {
		// 省略
	}

}
