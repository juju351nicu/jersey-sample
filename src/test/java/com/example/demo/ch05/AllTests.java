package com.example.demo.ch05;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * リスト5.3 テストスイートクラス
 * @author shuji.w6e
 */
@Suite
@SelectClasses({ FooTest.class, BarTest.class })
public class AllTests {
}