package com.example.mocks;

import java.io.IOException;
import java.util.Map;

import com.example.demo.sample.model.LogLoader;

/**
 * ログファイルを読み込み、解析を行うLogAnalyzerクラスは、ログの読み込み部分をLogLoaderクラスに分離した設計となっている。</br>
 * LogLoaderクラスは、ログファイル名を文字列で引数にとり、読み込み結果をMapで返すloadメソッドを持つ。</br>
 * LogAnalyzerクラスは、LogLoaderを利用してログを読み込み、解析処理を行う。</br>
 * LogAnalyzerクラスのanalyzeメソッドの実行時、LogLoaderクラスのloadメソッドがIOExceptionを送出したのならばAnalyzeExceptionが再送出することをテストせよ。</br>
 * なお、原因となった例外にIOExceptionが含まれていること。</br>
 * ・LogAnalyzerクラス、LogLoaderクラスは問題で示したクラス設計とする。
 */
public class LogAnalyzer {

	LogLoader logLoader = new LogLoader();

	public Object analyze(String file) {
//	public void analyze(String file) throws IOException {
		try {
			Map<String, String> rawData = logLoader.load(file);
			System.out.println("ここを通りました。");
//			throw new IOException();
			return doAnalyze(rawData);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AnalyzeException(e);
		}
	}

	private Object doAnalyze(Map<String, String> rawData) {
		// これは仮実装です
		return new Object();
	}

	static class AnalyzeException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public AnalyzeException(Throwable cause) {
			super(cause);
		}
	}
}
