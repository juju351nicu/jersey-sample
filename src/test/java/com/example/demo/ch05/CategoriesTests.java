package com.example.demo.ch05;

import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * リスト5.9 カテゴリ化テストを実行するテストスイートクラス
 * 
 * @author shuji.w6e
 */
@ExcludeClassNamePatterns("com.example.demo.ch05.SlowTests")
@Suite
@SelectClasses({ SlowAndFastTest.class })
public class CategoriesTests {
}
