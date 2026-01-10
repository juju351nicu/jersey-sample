package com.example.mocks;

import java.util.Calendar;

/**
 * リスト20.2 MonthlyCalendarクラス
 * 
 * システム時間を基準とし、月末までの日数を取得するメソッドとテストを作成せよ。</br>
 * なお、月末である場合は0を返すことになる。</br>
 * 下記では、Calendarオブジェクトを引数に持つ、コンストラクタMonthlyCalendar(Calendar cal)を定義しています。</br>
 * このコンストラクタを利用する事で、任意の日付に関してテストが実行できます。</br>
 * なお、このコンストラクタはユニットテスト用である為、可視性をパッケージプライベートにしている。</br>
 * このように、任意の時間を指定して処理を実行できるようにする事で、てスタビリティが向上する。</br>
 * getRemainingDaysメソッドのようなユーティリティメソッドは、一般的にはstaticメソッドとする。</br>
 * しかしstaticメソッドはここで紹介されるテクニックを使用する事ができない。</br>
 * テスタビリティを高く設計するには、staticメソッドを避ける事も必要。</br>
 * ・MonthlyCalendarクラスにgetRemainingDaysメソッドを作成する。</br>
 * ・getRemainingDaysメソッドは、引数を持たず、int型の戻り値を持つ。</br>
 */
public class MonthlyCalendar {

	private final Calendar cal;

	public MonthlyCalendar() {
		this(Calendar.getInstance());
	}

	MonthlyCalendar(Calendar cal) {
		this.cal = cal;
	}

	public int getRemainingDays() {
		return cal.getActualMaximum(Calendar.DATE) - cal.get(Calendar.DATE);
	}

}
