package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.yaml.snakeyaml.Yaml;

/**
 * リスト8.15 外部リソースを使ったパラメータ化テスト
 * 
 * @author shuji.w6e
 */
class CalculatorDataPointsYamlTest {

	@ParameterizedTest
	@MethodSource("getParams")
	public void add(Fixture p) throws Exception {
		Calculator sut = new Calculator();
		assertEquals(sut.add(p.x, p.y), p.expected);
	}

	@SuppressWarnings("unchecked")
	static Fixture[] getParams() {
		InputStream in = CalculatorDataPointsYamlTest.class.getResourceAsStream("params.yaml");
		return ((List<Fixture>) new Yaml().load(in)).toArray(new Fixture[0]);
	}

	public static class Fixture {
		public int x;
		public int y;
		public int expected;
	}
}
