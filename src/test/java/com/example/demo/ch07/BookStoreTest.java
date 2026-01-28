package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * リスト7.5 生成メソッドによるフィクスチャのセットアップ
 * 
 * @author shuji.w6e
 */
class BookStoreTest {

	@Test
	void getTotalPrice() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = BookStoreDeclarativeTestHelper.Bookオブジェクトの作成_MartinFowlerのRefactoring();
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.getTotalPrice(), 4500);
	}

	@Test
	void get_0() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = BookStoreDeclarativeTestHelper.Bookオブジェクトの作成_MartinFowlerのRefactoring();
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.get(0), book);
	}
}
