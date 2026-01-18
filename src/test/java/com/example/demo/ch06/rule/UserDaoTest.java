package com.example.demo.ch06.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.ch06.UserDao;

/**
 * リスト6.9 ルールを使った共通処理の抽出
 * @author shuji.w6e
 */
 class UserDaoTest {
    private UserDao sut;

    @BeforeEach
    public void setUp() throws Exception {
        sut = new UserDao();
    }

    @Test
     void getListは0件を返す() throws Exception {
        // 省略 
    }
}
