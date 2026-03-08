package com.example.demo.ch10;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * リスト10.5 テストスイートクラスの作成
 * @author shuji.w6e
 */
@IncludeTags("FastTests")
@ExcludeTags("SlowTests")
@Suite
@SelectClasses({ FooTest.class, BarTest.class })
class CalcTheoriesTest {
}
