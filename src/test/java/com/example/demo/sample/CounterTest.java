package com.example.demo.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * メソッドの実行によりオブジェクトの内部状態などが更新され、そのメソッド自身や他のメソッドの振舞いに影響を与えるメソッドは「副作用を持つメソッド」と呼ばれる。</br>
 * 一般的に、副作用を持つメソッドは副作用を持たないメソッドよりもテストが難しくなる。</br>
 * オブジェクト指向プログラミングでは、プログラムの基本単位であるクラスに属性（フィールド）と振舞い（メソッド）を持たせ、オブジェクトの相互作用でソフトウェアを構築する。</br>
 * 従って、オブジェクトが状態を持つことは不自然なことではありません。</br>
 * しかしながら、オブジェクトの状態によって期待する効果が異なるため、状態が複雑になる程ケーススタディは大きく低下する。</br>
 * オブジェクトの状態によって期待する結果が異なるため、状態が複雑であるほどテストケースは増え初期化処理も複雑になる。</br>
 * このため、テスタビリティの高い設計とするには、可能な限りオブジェクトに状態を持たせるべきではありません。</br>
 * ただし現実として状態をもたない事は困難。状態を持つクラスと持たないクラスを分けておく事が現実的な対策となる。</br>
 * 状態を持つクラスのテストを行う場合はEnclosedテストランナーを利用し、状態毎にコンテキストを分けると良い。</br>
 * 下記では、コンテキスト毎にテストケースが1つしか定義されていないが、各コンテキストに新しいテストメソッドを追加するならば、重複する初期化処理を共通化すると見通しの良いテストコードになる。</br>
 * もし複雑な場合は、状態遷移図などを併用して整理する。</br>
 * オブジェクトの状態やそれぞれの状態での振舞いを整理しておくとテストが作成しやすくなる。
 */
class CounterTest {

	@Nested
	class 初期状態の場合 {
		Counter sut;

		@BeforeEach
		public void setUp() {
			sut = new Counter();
		}

		@Test
		@DisplayName("初期状態で、incrementメソッドを実行すると１が取得できる")
		void normal() {
			assertThat(sut.increment(), is(1));
		}
	}

	@Nested
	class incrementが１回実行された場合 {
		Counter sut;

		@BeforeEach
		void setUp() {
			sut = new Counter();
			sut.increment();
		}

		@Test
		@DisplayName("incrementメソッドを1回実行した状態で、incrementメソッド実行すると2が取得できる。")
		void incrementで１が取得できる() {
			assertThat(sut.increment(), is(2));
		}
	}

	@Nested
	class incrementが50回実行された場合 {
		Counter sut;

		@BeforeEach
		void setUp() {
			sut = new Counter();
			for (int i = 0; i < 50; i++) {
				sut.increment();
			}
		}

		@Test
		@DisplayName("incrementメソッドを50回実行した状態で、incrementメソッド実行すると51が取得できる。")
		void incrementで51が取得できる() {
			assertThat(sut.increment(), is(51));
		}
	}
}
