package com.example.demo.ch05;

import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * リスト5.9 カテゴリ化テストを実行するテストスイートクラス<br/>
 * Categories----特定カテゴリのテストクラスをまとめて実行する。<br/>
 * Categoriesクラスは、テストケースをカテゴリ化し、実行するテストケースをフィルタリングする為のテストランナーです。<br/>
 * 可能であれば、ユニットテストは常に全てのテストケースを実行すべきです。<br/>
 * しかしながら、テストケースが増えてくると実行時間がかかりすぎてしまい現実的ではない。<br/>
 * このため、各テストケースに目印（カテゴリ）を設定し、テストランナーでどのテストケースを実行するかを制御します。<br/>
 * ・カテゴリクラスの作成<br/>
 * 目印となるカテゴリクラスは、どのようなクラス（またはインタフェース）でも構いません。SlowTestsインターフェースは、遅いテストに付与するためのもの。<br/>
 * ・カテゴリクラスの指定<br/>
 * このようにして作成したクラスを利用してSlowAndFastTestクラスのようにCategoryアノテーションを宣言します。これでテストケースがカテゴリ化されました。<br/>
 * なお、Categoryアノテーションはテストクラスに設定することもできます。その場合、テストクラスに定義された全てのメソッドがそのカテゴリに属します。<br/>
 * ・カテゴリ化テストの実行<br/>
 * カテゴリ化テストを実行するには、Suiteテストランナーを使う場合と同様に@SelectClassesに対象となるテストクラスを指定します。<br/>
 * CategoriesTestsクラスでは、@ExcludeClassNamePatternsアノテーションで実行から除外するカテゴリの宣言を行っています。<br/>
 * このテストスイートを実行すると、SlowAndFastTestクラスに定義されたテストケースからSlowTetsと宣言されていないテストだけが実行されます。<br/>
 * カテゴリ化テストの詳細に関しては第10章を参照してください。<br/>
 * なお、テストスイートと同様に、ビルドツールの機能を利用してカテゴリ化テストを実行できます。<br/>
 * その場合、Categoryアノテーションにようるカテゴリ化を行えば、実行するテストケースの収集はビルトツールによって行われます。<br/>
 * 従って、テストスイートクラスは作成する必要はありません。<br/>
 *
 * @author shuji.w6e
 */
@ExcludeClassNamePatterns("com.example.demo.ch05.SlowTests")
@Suite
@SelectClasses({ SlowAndFastTest.class })
public class CategoriesTests {
}
