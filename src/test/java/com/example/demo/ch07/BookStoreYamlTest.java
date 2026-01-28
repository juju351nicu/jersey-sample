package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

/**
 * リスト7.8 YnakeYamlによるYAMLファイルの読み込み
 * 
 * @author shuji.w6e
 */
class BookStoreYamlTest {

	@Test
	void getTotalPrice() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = (Book) new Yaml().load(getClass().getResourceAsStream("book_fixtures.yaml"));
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.getTotalPrice(), 4500);
	}

	@Test
	void get_0() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = (Book) new Yaml().load(getClass().getResourceAsStream("book_fixtures.yaml"));
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.get(0), book);
	}
}
