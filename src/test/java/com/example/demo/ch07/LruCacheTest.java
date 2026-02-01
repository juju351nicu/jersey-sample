package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト7.4 ネストしたクラスごとに行われる暗黙的セットアップ </br>
 * ・暗黙的セットアップ</br>
 * Junitでは、テストクラスで共通した初期化処理を、BeforeEachアノテーションが付与されたメソッドに抽出されます。</br>
 * このように抽出されたメソッドはセットアップメソッドと呼ばれ、各テストメソッドを実行する前に暗黙的に実行されます。</br>
 * このようなセットアップメソッドでフィクスチャのセットアップを行うパターンを暗黙的セットアップ(implicit setup)と呼びます。</br>
 * 暗黙的セットアップは、フィクスチャのセットアップコードをセットアップメソッドに抽出するパターンです。</br>
 * 暗黙的なセットアップを使うことにより、テストメソッドのコードはテストの実行と検証が中心となり、何をテストしようとしているかが明確になります。</br>
 * 暗黙的なセットアップは、＠Nestedを使用する場合、共通の初期化処理を持つテストケースでグループ化するからです。</br>
 * 各ネストしたクラスで、フィクスチャのセットアップのコードがセットアップメソッドに抽出され、見通しの良いテストコードとなります。（LruCacheTestクラス）</br>
 * ただし、テストケースごとに入力値や期待値が異なる場合もあります。</br>
 * そのような場合は、テストの前提条件を満たすために必要なテスト対象オブジェクトの初期化や事前操作などに関してはセットアップメソッドで行いますが、それ以外のテストケースごとに固有のフィクスチャに関してはテストメソッド内でセットアップします。</br>
 * 
 * @author shuji.w6e
 */
class LruCacheTest {
	@Nested
	class AとBを追加している場合 {
		LruCache<String> sut;

		@BeforeEach
		public void setUp() throws Exception {
			sut = new LruCache<String>();
			sut.put("A", "valueA");
			sut.put("B", "valueB");
		}

		@Test
		void sizeは2() throws Exception {
			assertEquals(sut.size(), 2);
		}

		@Test
		void get_AでvalueAを返しkeysはBA() throws Exception {
			assertEquals(sut.get("A"), "valueA");
			assertEquals(sut.keys.size(), 2);
			assertEquals(sut.keys.get(0), "B");
			assertEquals(sut.keys.get(1), "A");
		}

		@Test
		void get_BでvalueBを返しkeysはAB() throws Exception {
			assertEquals(sut.get("B"), "valueB");
			assertEquals(sut.keys.size(), 2);
			assertEquals(sut.keys.get(0), "A");
			assertEquals(sut.keys.get(1), "B");
		}

		@Test
		void get_Cでnullを返しkeysはAB() throws Exception {
			assertNull(sut.get("C"));
			assertEquals(sut.keys.size(), 2);
			assertEquals(sut.keys.get(0), "A");
			assertEquals(sut.keys.get(1), "B");
		}

		@Test
		void put_Cでsizeは3_keysはABCとなる() throws Exception {
			// Set up
			String key = "C";
			String item = "valueC";
			// Exercise
			sut.put(key, item);
			// Verify
			assertEquals(sut.size(), 3);
			assertEquals(sut.keys.size(), 3);
			assertEquals(sut.keys.get(0), "A");
			assertEquals(sut.keys.get(1), "B");
			assertEquals(sut.keys.get(2), "C");
		}
	}
}
