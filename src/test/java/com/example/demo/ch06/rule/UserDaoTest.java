package com.example.demo.ch06.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.ch06.UserDao;

/**
 * リスト6.9 ルールを使った共通処理の抽出<br/>
 * ・テストクラスを横断する共通処理
 * Enclosedテストランナーによるテストクラスの構造化を行うことで、テストクラス内の共通処理は綺麗に整理できます。<br/>
 * しかしながら、異なるテストクラス間で共通した処理は整理できません。<br/>
 * このような時は、共通処理を定義した基底クラスを作成してテストクラスを継承させる事が多いでしょう。<br/>
 * 継承はなるべく避けるべきです。
 * 何故ならば安易な継承はメンテナンス性を避けるべきではなく、無意識に巨大な機能を持った基底クラスを生み出しやすくなります。<br/>
 * なるべくならば、共通処理をユーティリティクラスなどに抽出し、テストクラスからは、ユーティリティクラスを利用する方がよいでしょう。<br/>
 * 下記は、テスト用のインメモリデータベースの起動と停止など、やや複雑な共通処理を持つテストクラスです。<br/>
 * このコードでは、テストの本質とは関係のない多くの処理が混入しています。<br/>
 * これでは可読性を損なうだけでなく、InMemoryDBクラスの使い方などが変更された場合に、それを利用しているテストクラス全体に影響を与えてしまいます。<br/>
 * このように、テストクラスからテストとは直接関連しない共通処理を抽出する場合は、JUnitで共通処理を抽出する機能であるルールを使うと便利です。<br/>
 * ルールを利用すると下記（＠Rule）のように記述できます。<br/>
 * テストクラスでは、テスト対象クラスに関するコードを中心に記述し、それ以外のコードを減らす事ができます。<br/>
 * ルールについては、第9章で詳細に解説します。<br/>
 * 
 * @author shuji.w6e
 */
class UserDaoTest {
	private UserDao sut;
	// ＠Rule
	// public InMemoryDBRule db = new InMemoryDBRule();

	@BeforeEach
	void setUp() throws Exception {
		sut = new UserDao();
	}

	@Test
	void getListは0件を返す() throws Exception {
		// 省略
	}
}
