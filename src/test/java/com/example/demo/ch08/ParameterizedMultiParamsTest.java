package com.example.demo.ch08;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParameterizedMultiParamsTest {

	@ParameterizedTest
	@MethodSource("provideStringsForIsBlank")
	public void テストメソッド(int intParam, String strParam) throws Exception {
		System.out.println("テストメソッド(" + intParam + ", " + strParam + ")");
	}

	private static Stream<Arguments> provideStringsForIsBlank() {
		return Stream.of(Arguments.of(3, "Hello"), Arguments.of(4, "World"));
	}
}
