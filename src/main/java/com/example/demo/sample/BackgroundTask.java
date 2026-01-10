package com.example.demo.sample;

import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;

/**
 * 処理をバックグラウンドのスレッドで非同期実行したい。</br>
 * バックグラウンドスレッドで処理を実行するBackgroundTaskクラスを作成し、タスクがバックグラウンドのスレッドで実行されていることを検証するテストを作成する。</br>
 * タスクはRunnableオブジェクトに実装する。</br>
 * ・BackgroundTaskはコンストラクタでRunnableオブジェクトを引数に持つ。</br>
 * ・BackgroundTaskクラスに、invokeメソッドを定義する。</br>
 * ・invokeメソッドは、引数を持たず戻り値をvoid型にする。</br>
 * ・invokeメソッドでは、バックグラウンドスレッドでタスクを実行し、すぐに制御を呼び出し元に返す。
 */
@AllArgsConstructor
public class BackgroundTask {
	private final Runnable task;

	public void invoke() {
		Executors.newSingleThreadExecutor().execute(task);
	}
}
