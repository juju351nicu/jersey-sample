package com.example.model;

import lombok.Data;

@Data
public class ErrorResponse {

	/** エラーコード */
	private String errorCode;

	/** エラーメッセージ */
	private String errorMessage;
}
