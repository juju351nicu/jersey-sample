package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * リスト9.17 OSに依存したテストを行うカスタムルール<br>
 * リスト9.17のOSDependentクラスは、テストが実行される環境（OS）によって実行するテストを制御するカスタムルールです。<br>
 * このルールを宣言したテストクラスで、かつ、テストメソッドにRunOnアノテーション（リスト9.18）が付与されている場合、実行環境が指定されたOSであるかをチェックし、一致しなければテストを実行できません。<br>
 * 先ほどのPreConditionクラスと同様に、OSDependentクラスのapplyメソッドではStatementオブジェクトのプロキシオブジェクトを返します。(リスト9.19)<br>
 * プロキシオブジェクトのevaluateメソッドでは、Descriptionオブジェクトを利用し、テストメソッドに定義されたアノテーションを取得しています。<br>
 * もし、実行環境とアノテーションで指定されたOSが一致しない場合、テストは実行されません。<br>
 * 
 * @author shuji.w6e
 */
class OSDependentTest {

	/**
	 * Windowsでのみ実行
	 */
	@Test
	@EnabledOnOs(OS.WINDOWS)
	void onlyWindows() {
		System.out.println("test: onlyWindows");
		assertEquals(File.separator, "\\");
	}

	/**
	 * LinuxまたはMacで実行
	 */
	@Test
	@EnabledOnOs({ OS.MAC })
	void onlyMac() {
		System.out.println("test: onlyMac");
		assertEquals(File.separator, "/");
	}
}
