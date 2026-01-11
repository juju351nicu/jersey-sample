package com.example.demo.ch05;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

class CalcTest {

	@Test
	void addに3と4を与えると7を返す() {
		Calc sut = new Calc();
		assertEquals(sut.add(3, 4), 7);
	}

	/**
	 * エントリーポイント<br />
	 * 
	 * JUnit 5では、org.junit.runner.JUnitCoreJUnit 4
	 * で使用されていたクラスは、プログラムによるテスト実行のためのより柔軟なJUnit Platform Launcher
	 * APIに置き換えられました。<br />
	 * mainメソッドからJUnit 5テストを実行する方法<br />
	 * メソッドからプログラム的にJUnit 5テストを実行するには、およびインターフェースmainを使用する必要があります。<br />
	 * このアプローチにより、テストの検出と実行をきめ細かく制御できます。<br />
	 * また、プロジェクトに必要な JUnit プラットフォーム依存関係 (Maven または Gradle) があることを確認する必要がある。<br />
	 */
	public static void main(String[] args) {
		// 特定のテストクラスを選択してリクエストを作成する。
		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(DiscoverySelectors.selectClass(CalcTest.class)).build();

		// Launcherインスタンスの作成する。
		Launcher launcher = LauncherFactory.create();

		// 結果と概要を収集するためにリスナーを登録する。
		SummaryGeneratingListener listener = new SummaryGeneratingListener();
		launcher.registerTestExecutionListeners(listener);

		// テストを実行する。
		launcher.execute(request);

		// テスト実行の概要をコンソールに出力する。
		TestExecutionSummary summary = listener.getSummary();
		summary.printTo(new PrintWriter(System.out));
	}

}
