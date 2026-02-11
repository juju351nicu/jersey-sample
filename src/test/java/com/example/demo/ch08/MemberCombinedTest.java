package com.example.demo.ch08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

///**
// * リスト8.17 2つのパラメータの組み合わせテスト
// * @author shuji.w6e
// */
// class MemberCombinedTest {
//
//    @DataPoints
//    public static int[] AGES = { 15, 20, 25, 30 };
//    @DataPoints
//    public static Gender[] GENDERS = Gender.values();
//
//	@ParameterizedTest
//	@MethodSource("getParams")
//    public void canEntry(int age, Gender gender) throws Exception {
//        System.out.println("canEntry(" + age + ", " + gender + ")");
//    }
//}
