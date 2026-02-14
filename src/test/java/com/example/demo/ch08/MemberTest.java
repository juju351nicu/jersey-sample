package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * ・Assumeによるパラメータのフィルタリング<br>
 * パラメータ化テストによる組み合わせテストは強力ですが、全ての組み合わせについて実行します。<br>
 * そうすると、テストによっては期待値が一定になりません。<br>
 * これは、条件を満たすパラメータの組み合わせ、または条件を満たさないパラメータの組み合わせでテストを実行しなければならないからです。<br>
 * そこで、Assumptionsクラスを利用して、パラメータをフィルタリングします。<br>
 * AssumptionsクラスはAssertionsクラスとよく似たクラスであり、値の検証メソッドを提供します。<br>
 * assumingThatメソッドはassertEqualsメソッドに対し、引数にbooleanを取り、値の比較検討を行います。<br>
 * しかし、こちらのメソッドは検証結果が偽である場合にテストは失敗になりません。<br>
 * そのテストを無視してテストの実行を継続させます。<br>
 * 8.18のAssmeTestクラスは簡単な例だが、こちらのテストは失敗にならない。<br>
 * 下記の（リスト8.19）では、それぞれのテストケースの条件（❶25歳以下の女性、❷25歳以下の女性でない）でテストで利用するパラメータを制限しています。<br>
 * これらの条件の記述は宣言的に行われているため、テストコードの可読性を損ないません。<br>
 * また、Assumptionsクラスを使うことで特定の環境に依存するテストの実行もフィルタリングできます。<br>
 * 8.4パラメータ化テストの問題<br>
 * パラメータ化テストはテストデータを独立して定義したり、組み合わせテストを可能にしたりと強力な機能ですが、いくつかの問題もある。<br>
 * ・データの網羅性<br>
 * 1つ目の問題は、データの取り方や網羅性に関して保証されている訳ではないということです。<br>
 * この章の初めに解説した会員サイトへのエントリ可能判定メソッドのテストをパラメータ化テストで行ったとしても、テストデータに25歳以下の女性が含まれていることは保証されません。<br>
 * また、assumingThatで全てのパラメータが無視されてしまっていてもテストは成功となってしまいます。<br>
 * この問題はフレームワークで検知できる類の問題ではありません。<br>
 * テストデータの妥当性やテストの網羅性については、テスト技法を学びレビューなどで検証する必要があります。<br>
 * ・パラメータに関する情報の欠落<br>
 * 2つ目の問題は、テストが失敗した場合に「どんなパラメータで失敗したか？」という情報が欠落してしまうことです。<br>
 * 通常のテストケースでは、テストメソッド名がテストケースの概要を示していますが、パラメータ化テストではテストメソッドにパラメータの情報を含めることが出来ません。<br>
 * つまり、テスト失敗時には「”canEntryは25歳以下の女性の場合にtrueを返す”が失敗した。」という情報しか報告されません。<br>
 * 具体的にどんな値を入力値とした時に、期待される結果とならなかったかがわからなければ、問題の分析に時間がかかってしまいます。<br>
 * この問題を解決するんは、assertEqaulsメソッドの第1引数に失敗時のメソッドを指定します。<br>
 * リスト8.21のように失敗時のメッセージにはその時受け取ったパラメータ情報を含めてください。<br>
 * ユニットテストでは、失敗した時にそのフィードバックを受け、素早く対処可能であることが重要です。<br>
 * 
 * @author shuji.w6e
 */
class MemberTest {

	/**
	 * リスト8.19 assumeによるパラメータのフィルタリング
	 * 
	 * @param gender
	 */
	@ParameterizedTest
	@EnumSource(value = Gender.class, names = { "MALE", "FEMALE" })
	void canEntryは25歳以下の女性の場合にtrueを返す(Gender gender) {
		int[] AGES = { 15, 20, 25, 30 };
		for (int age : AGES) {
			assumingThat(age <= 25 && gender == Gender.FEMALE, () -> {
				String msg = "test1 When age=" + age + ", gender=" + gender;
				System.out.println(msg);
				assertEquals(Member.canEntry(age, gender), true);
			});
		}
	}

	/**
	 * リスト8.21 パラメータの情報を指定するアサーション
	 * 
	 * @param gender
	 */
	@ParameterizedTest
	@EnumSource(value = Gender.class, names = { "MALE", "FEMALE" })
	void canEntryは25歳以下の女性でない場合にfalseを返す(Gender gender) {
		int[] AGES = { 15, 20, 25, 30 };
		for (int age : AGES) {
			assumingThat(25 < age || gender != Gender.FEMALE, () -> {
				String msg = "test2 When age=" + age + ", gender=" + gender;
				System.out.println(msg);
				assertEquals(Member.canEntry(age, gender), false);
			});
		}
	}
}
