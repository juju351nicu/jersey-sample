package com.example.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

	private final static ZoneId ASIA_TOKYO = ZoneId.of("Asia/Tokyo");

	private final static ZoneId UTC = ZoneId.of("UTC");
	/** 末尾を常にリテラル'Z'を固定してフォーマット */
	private final static DateTimeFormatter FMT_Z_LITERAL = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	/** TIMESTMAP型フォーマット */
	private final static DateTimeFormatter FMT_JST_TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * 日付変換処理</br>
	 * Timestamp型の日付を「yyyy-MM-ddTHH:mm:ss.SSSZ」型にする。</br>
	 * 
	 * @param target
	 * @return
	 */
	public static String parseToStrByUTCDateTime(Timestamp target) {
		try {
			// システム時刻に変換する。
			LocalDateTime ldt = target.toLocalDateTime();
			// JSTを明示的に付与
			ZonedDateTime jstZdt = ldt.atZone(ASIA_TOKYO);
			// UTCに変換
			ZonedDateTime utcZdt = jstZdt.withZoneSameInstant(UTC);
			String isoZ = utcZdt.format(FMT_Z_LITERAL);
			return isoZ;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * UTC型の文字列をDB用の日付文字列に変換する。</br>
	 * 「yyyy-MM-ddTHH:mm:ss.SSSZ」を「yyyy-MM-dd HH:mm:ss」型にする。</br>
	 * 
	 * @param target
	 * @return
	 */
	public static String parseToStrByStrUTC(String target) {
		try {
			// ISO-8601を自動認識
			OffsetDateTime odt = OffsetDateTime.parse(target);
			ZonedDateTime utcZdt = odt.atZoneSameInstant(ASIA_TOKYO);
			String jstFormattd = utcZdt.format(FMT_JST_TIMESTAMP);
			return jstFormattd;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
