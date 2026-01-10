package com.example.mocks;

import java.util.Calendar;

/**
 * リスト20.4 スタブで置き換え可能なSystemCalendar
 * 
 * 下記のように処理を別のクラスに委譲すれば、スタブを利用してテストすることもできる。</br>
 */
public class MonthlyCalendar3 {

	SystemCalendar sysCal = new SystemCalendar() {
		@Override
		public Calendar getInstance() {
			return Calendar.getInstance();
		}
	};

	public int getRemainingDays() {
		Calendar cal = sysCal.getInstance();
		return cal.getActualMaximum(Calendar.DATE) - cal.get(Calendar.DATE);
	}

	public static interface SystemCalendar {
		Calendar getInstance();
	}

}
