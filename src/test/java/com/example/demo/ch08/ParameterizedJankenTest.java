package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.demo.ch08.Janken.Hand;
import com.example.demo.ch08.Janken.Result;

class ParameterizedJankenTest {

	@ParameterizedTest
	@MethodSource("listMethodSource")
	void judge(Fixture fx) throws Exception {
		Janken sut = new Janken();
		Result actual = sut.judge(fx.h1, fx.h2);
		assertEquals(actual, fx.expected, fx.toString());
	}

	static Fixture[] listMethodSource() {
		return new Fixture[] { new Fixture(Hand.GU, Hand.GU, Result.DRAW), new Fixture(Hand.GU, Hand.PA, Result.LOSE),
				new Fixture(Hand.GU, Hand.TYOKI, Result.WIN), new Fixture(Hand.PA, Hand.GU, Result.WIN),
				new Fixture(Hand.PA, Hand.PA, Result.DRAW), new Fixture(Hand.PA, Hand.TYOKI, Result.LOSE),
				new Fixture(Hand.TYOKI, Hand.GU, Result.LOSE), new Fixture(Hand.TYOKI, Hand.PA, Result.WIN),
				new Fixture(Hand.TYOKI, Hand.TYOKI, Result.DRAW), };
	}

	static class Fixture {
		final Hand h1;
		final Hand h2;
		final Result expected;

		public Fixture(Hand h1, Hand h2, Result expected) {
			this.h1 = h1;
			this.h2 = h2;
			this.expected = expected;
		}

		@Override
		public String toString() {
			return String.format("%s vs %s expected %s.", h1, h2, expected);
		}
	}
}
