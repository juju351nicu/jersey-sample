package com.example.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.*;
import java.sql.Date;
import java.time.temporal.ChronoUnit;

public final class DateUtils {

	// ISO 8601形式で日付を表示するフォーマット（Zを末尾に付ける）
	private static final DateTimeFormatter FMT_Z_LITERAL = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	// JSTフォーマット
	private static final DateTimeFormatter FMT_JST_TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// 日付を「yyyy-MM-dd」のフォーマットに変換するための定義
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final ZoneId ASIA_TOKYO = ZoneId.of("Asia/Tokyo");
	private static final ZoneId UTC = ZoneId.of("UTC");

	/** コンストラクタ */
	private DateUtils() {
		// Singleton パターンを防ぐためのプライベートコンストラクタ
	}

	/**
	 * Timestamp型の日付を「yyyy-MM-dd'T'HH:mm:ss.SSSZ」型に変換する。
	 * 
	 * @param target Timestamp 型の日付
	 * @return 変換された日付文字列（ISO 8601形式）
	 */
	public static String parseToStrByUTCDateTime(Timestamp target) {
		try {
			// Timestamp を LocalDateTime に変換
			LocalDateTime ldt = target.toLocalDateTime();
			// JST（Tokyo）タイムゾーンを付与
			ZonedDateTime jstZdt = ldt.atZone(ASIA_TOKYO);
			// UTC に変換
			ZonedDateTime utcZdt = jstZdt.withZoneSameInstant(UTC);
			// ISO 8601 フォーマットで文字列に変換
			return utcZdt.format(FMT_Z_LITERAL);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * UTC型の文字列をDB用の日付文字列（「yyyy-MM-dd HH:mm:ss」形式）に変換する。
	 * 
	 * @param target UTCのISO 8601形式文字列（例: "2026-04-04T14:30:00.000Z"）
	 * @return JSTタイムゾーンの日時文字列（例: "2026-04-04 23:30:00"）
	 */
	public static String parseToStrByStrUTC(String target) {
		return parseDate(target, ASIA_TOKYO, FMT_JST_TIMESTAMP);
	}

	/**
	 * 日付文字列を指定されたタイムゾーンでパースし、指定のフォーマットで文字列に変換する共通メソッド。
	 * 
	 * @param dateStr    日付文字列
	 * @param targetZone タイムゾーン（例: Asia/Tokyo）
	 * @param formatter  日付フォーマット
	 * @return フォーマットされた日付文字列
	 */
	private static String parseDate(String dateStr, ZoneId targetZone, DateTimeFormatter formatter) {
		try {
			OffsetDateTime odt = OffsetDateTime.parse(dateStr);
			ZonedDateTime zonedDateTime = odt.atZoneSameInstant(targetZone);
			return zonedDateTime.format(formatter);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 以下はフロント画面からの入力をDB用形式に変換するメソッド群

	/**
	 * Timestamp型からString型に変換する
	 * 
	 * @param sqlTimestamp Timestamp 型の日付
	 * @return String 型の日付（例: "2026-04-04T14:30:00"）
	 */
	public static String sqlTimestampToStr(Timestamp sqlTimestamp) {
		LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
		return String.valueOf(localDateTime);
	}

	/**
	 * java.sql.Date 型を LocalDate 型に変換
	 * 
	 * @param date SQLのDate型の日付
	 * @return LocalDate 型の日付
	 */
	public static LocalDate sqlDateToLocalDate(Date date) {
		return date.toLocalDate();
	}

	/**
	 * java.sql.Date 型を String 型に変換
	 * 
	 * @param date SQLのDate型の日付
	 * @return String 型の日付（yyyy-MM-dd 形式）
	 */
	public static String sqlDateToStr(Date date) {
		return date.toLocalDate().format(fmt);
	}

	/**
	 * LocalDate 型から java.sql.Date 型に変換する
	 * 
	 * @param date LocalDate 型の日付
	 * @return java.sql.Date 型の日付
	 */
	public static Date localDateToSqlDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}

	/**
	 * String 型の日付（yyyy-MM-dd 形式）を LocalDate 型に変換する
	 * 
	 * @param dateStr String 型の日付
	 * @return LocalDate 型の日付
	 */
	public static LocalDate strToLocalDate(String dateStr) {
		return LocalDate.parse(dateStr);
	}

	/**
	 * String 型の日付情報を java.sql.Date 型に変換する
	 * 
	 * @param strDate String 型の日付（yyyy-MM-dd 形式）
	 * @return java.sql.Date 型の日付
	 */
	public static Date strDateToSqlDate(String strDate) {
		if (strDate == null)
			throw new NullPointerException("strdate is null");
		try {
			return localDateToSqlDate(strToLocalDate(strDate));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * String 型の日付情報を Timestamp 型に変換する
	 * 
	 * @param strTimestamp String 型の日時情報（例: "2026-04-04T14:30:00"）
	 * @return Timestamp 型の日時情報
	 */
	public static Timestamp strToSqlTimestamp(String strTimestamp) {
		if (strTimestamp == null)
			throw new NullPointerException("strTimestamp is null");
		try {
			LocalDateTime dateTime = LocalDateTime.parse(strTimestamp);
			return Timestamp.valueOf(dateTime);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * LocalDateTime 型から java.sql.Timestamp 型に変換する
	 * 
	 * @param dateTime LocalDateTime 型の日時
	 * @return Timestamp 型の日時
	 */
	public static Timestamp dateTimeToSqlTimestamp(LocalDateTime dateTime) {
		return Timestamp.valueOf(dateTime);
	}

	/**
	 * 開始日から終了日までの日数を計算する
	 * 
	 * @param dateFrom 開始日（yyyy-MM-dd 形式）
	 * @param dateTo   終了日（yyyy-MM-dd 形式）
	 * @return 開始日から終了日までの日数
	 */
	public static long remainingDays(String dateFrom, String dateTo) {
		LocalDate startDate = strToLocalDate(dateFrom);
		LocalDate endDate = strToLocalDate(dateTo);
		return ChronoUnit.DAYS.between(startDate, endDate);
	}
}