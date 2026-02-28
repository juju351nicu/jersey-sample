package com.example.demo.ch09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * リスト9.21 ClassRuleアノテーションによるサーバの接続と切断
 * 
 * @author shuji.w6e
 */
class ClassRuleExampleTest implements BeforeEachCallback, AfterEachCallback {

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		// before() の処理
		System.out.println("connect");
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		// after() の処理
		System.out.println("disconnect");
	}

	@Test
	void test01() throws Exception {
		System.out.println("test01");
	}

	@Test
	void test02() throws Exception {
		System.out.println("test02");
	}
}
