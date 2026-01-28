package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * リスト7.2 複雑なフィクスチャのセットアップ
 * 
 * @author shuji.w6e
 */
class BookStoreComplexSetupTest {

	@Test
	void getTotalPrice() throws Exception {
		// SetUp
		Book book = new Book();
		book.setTitle("Refactoring");
		book.setPrice(4500);
		Author author = new Author();
		author.setFirstName("Martin");
		author.setLastName("Fowler");
		book.setAuthor(author);
		BookStore sut = new BookStore();
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.getTotalPrice(), 4500);
	}
}
