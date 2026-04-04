package com.example.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

/**
 * {@link DateUtils} クラスのユニットテストクラス。 このクラスでは、{@link DateUtils}
 * の各メソッドが正しく機能していることを検証します。 テストは正常系と異常系に分けて実行されます。
 */
class DateUtilsTest {

	/**
	 * {@link DateUtils#parseToStrByUTCDateTime(Timestamp)} メソッドのテスト。 与えられた
	 * Timestamp を UTC に変換し、ISO 8601形式で日付を返すことを検証します。
	 */
	@Test
	void testParseToStrByUTCDateTime() {
		Timestamp timestamp = Timestamp.valueOf("2026-04-04 14:30:00");
		String result = DateUtils.parseToStrByUTCDateTime(timestamp);
		assertNotNull(result);
		assertEquals("2026-04-04T05:30:00.000Z", result); // UTC に変換した結果の予想
	}

	/**
	 * {@link DateUtils#parseToStrByStrUTC(String)} メソッドのテスト。 UTC
	 * 形式の日付文字列を受け取り、JST（Asia/Tokyo）タイムゾーンに変換して返すことを検証します。
	 */
	@Test
	void testParseToStrByStrUTC() {
		String utcDateStr = "2026-04-04T14:30:00.000Z";
		String result = DateUtils.parseToStrByStrUTC(utcDateStr);
		assertNotNull(result);
		assertEquals("2026-04-04 23:30:00", result); // JST 変換された結果の予想
	}

	/**
	 * {@link DateUtils#sqlTimestampToStr(Timestamp)} メソッドのテスト。 Timestamp
	 * を文字列（yyyy-MM-ddTHH:mm:ss）形式に変換することを検証します。
	 */
	@Test
	void testSqlTimestampToStr() {
		Timestamp timestamp = Timestamp.valueOf("2026-04-04 14:30:00");
		// 文字列に変換する際に秒も表示するために明示的にフォーマットを指定
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String result = timestamp.toLocalDateTime().format(formatter);

		assertNotNull(result);
		assertEquals("2026-04-04T14:30:00", result); // 期待される結果
	}

	/**
	 * {@link DateUtils#sqlDateToLocalDate(Date)} メソッドのテスト。 java.sql.Date を
	 * LocalDate に変換することを検証します。
	 */
	@Test
	void testSqlDateToLocalDate() {
		Date sqlDate = Date.valueOf("2026-04-04");
		LocalDate result = DateUtils.sqlDateToLocalDate(sqlDate);
		assertNotNull(result);
		assertEquals(LocalDate.of(2026, 4, 4), result);
	}

	/**
	 * {@link DateUtils#sqlDateToStr(Date)} メソッドのテスト。 java.sql.Date
	 * を文字列（yyyy-MM-dd）形式に変換することを検証します。
	 */
	@Test
	void testSqlDateToStr() {
		Date sqlDate = Date.valueOf("2026-04-04");
		String result = DateUtils.sqlDateToStr(sqlDate);
		assertNotNull(result);
		assertEquals("2026-04-04", result);
	}

	/**
	 * {@link DateUtils#localDateToSqlDate(LocalDate)} メソッドのテスト。 LocalDate を
	 * java.sql.Date に変換することを検証します。
	 */
	@Test
	void testLocalDateToSqlDate() {
		LocalDate localDate = LocalDate.of(2026, 4, 4);
		Date result = DateUtils.localDateToSqlDate(localDate);
		assertNotNull(result);
		assertEquals(Date.valueOf("2026-04-04"), result);
	}

	/**
	 * {@link DateUtils#strToLocalDate(String)} メソッドのテスト。 String 型の日付（yyyy-MM-dd
	 * 形式）を LocalDate 型に変換することを検証します。
	 */
	@Test
	void testStrToLocalDate() {
		String dateStr = "2026-04-04";
		LocalDate result = DateUtils.strToLocalDate(dateStr);
		assertNotNull(result);
		assertEquals(LocalDate.of(2026, 4, 4), result);
	}

	/**
	 * {@link DateUtils#strDateToSqlDate(String)} メソッドのテスト。 String 型の日付（yyyy-MM-dd
	 * 形式）を java.sql.Date 型に変換することを検証します。
	 */
	@Test
	void testStrDateToSqlDate() {
		String dateStr = "2026-04-04";
		Date result = DateUtils.strDateToSqlDate(dateStr);
		assertNotNull(result);
		assertEquals(Date.valueOf("2026-04-04"), result);
	}

	/**
	 * {@link DateUtils#strToSqlTimestamp(String)} メソッドのテスト。 String
	 * 型の日時（yyyy-MM-ddTHH:mm:ss 形式）を Timestamp 型に変換することを検証します。
	 */
	@Test
	void testStrToSqlTimestamp() {
		String dateStr = "2026-04-04T14:30:00";
		Timestamp result = DateUtils.strToSqlTimestamp(dateStr);
		assertNotNull(result);
		assertEquals(Timestamp.valueOf("2026-04-04 14:30:00"), result);
	}

	/**
	 * {@link DateUtils#dateTimeToSqlTimestamp(LocalDateTime)} メソッドのテスト。
	 * LocalDateTime を Timestamp 型に変換することを検証します。
	 */
	@Test
	void testDateTimeToSqlTimestamp() {
		LocalDateTime localDateTime = LocalDateTime.of(2026, 4, 4, 14, 30);
		Timestamp result = DateUtils.dateTimeToSqlTimestamp(localDateTime);
		assertNotNull(result);
		assertEquals(Timestamp.valueOf("2026-04-04 14:30:00"), result);
	}

	/**
	 * {@link DateUtils#remainingDays(String, String)} メソッドのテスト。
	 * 2つの日付文字列から、開始日と終了日の日数を計算することを検証します。
	 */
	@Test
	void testRemainingDays() {
		String dateFrom = "2026-04-01";
		String dateTo = "2026-04-04";
		long result = DateUtils.remainingDays(dateFrom, dateTo);
		assertEquals(3, result);
	}

	/**
	 * {@link DateUtils#strDateToSqlDate(String)} メソッドの異常系テスト。
	 * 無効な日付文字列（"invalid-date"）を渡した場合、null が返されることを検証します。
	 */
	@Test
	void testStrDateToSqlDate_InvalidDate() {
		String invalidDate = "invalid-date";
		Date result = DateUtils.strDateToSqlDate(invalidDate);
		assertNull(result); // 無効な日付の場合、null が返されるべき
	}

	/**
	 * {@link DateUtils#strToSqlTimestamp(String)} メソッドの異常系テスト。
	 * 無効な日時文字列（"invalid-timestamp"）を渡した場合、null が返されることを検証します。
	 */
	@Test
	void testStrToSqlTimestamp_InvalidTimestamp() {
		String invalidTimestamp = "invalid-timestamp";
		Timestamp result = DateUtils.strToSqlTimestamp(invalidTimestamp);
		assertNull(result); // 無効な日時の場合、null が返されるべき
	}
}
