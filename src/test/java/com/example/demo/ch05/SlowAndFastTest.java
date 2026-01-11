package com.example.demo.ch05;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * リスト5.7 テストメソッドのカテゴリ化
 * 
 * @author shuji.w6e
 */
@Disabled("EclipseからJUnitテストを実行した場合、このテストクラスに含まれるテストが実行され、テストが失敗となります")
public class SlowAndFastTest {
	@Test
	void fastTest_01() throws Exception {
	}

	@Test
	@Tag("slow") // "slow" タグを付与
	public void slowTest_01() throws Exception {
		fail();
	}

	@Test
	@Tag("slow") // "slow" タグを付与
	void slowTest_02() throws Exception {
		fail();
	}
}
