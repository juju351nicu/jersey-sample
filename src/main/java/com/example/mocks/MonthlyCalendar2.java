package com.example.mocks;

import java.util.Calendar;

/**
 * リスト20.3 Calendarオブジェクトの生成をメソッドに抽出
 * 
 * テストで制御できない処理をメソッドに抽出してオーバライドする事できる。<br>
 */
public class MonthlyCalendar2 {

	public int getRemainingDays() {
		Calendar cal = getCalendar();
		return cal.getActualMaximum(Calendar.DATE) - cal.get(Calendar.DATE);
	}

	Calendar getCalendar() {
		return Calendar.getInstance();
	}

}
