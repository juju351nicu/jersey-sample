package com.example.mocks;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * NetworkResourcesクラスは、ネットワーク上のリソースファイルを読み込み、文字列として返すloadメソッドを持つ。</br>
 * ただし、ネットワークに接続し、InputStreamオブジェクトを生成する処理はNetworkLoaderクラスに定義する。</br>
 * NetworkResourcesクラスはのloadメソッドのテストを作成せよ。</br>
 * ・NetworkResourcesクラス、NetworkLoaderクラスは問題で示したクラス設計とする。</br>
 */
public class NetworkResources {

	NetworkLoader loader = new NetworkLoader();

	public String load() throws IOException {
		try (InputStreamReader reader = new InputStreamReader(loader.getInput())) {
			StringBuilder str = new StringBuilder();
			char[] buf = new char[512];
			for (;;) {
				int len = reader.read(buf);
				if (len == -1)
					break;
				str.append(new String(buf, 0, len));
			}
			return str.toString();
		}
	}
}
