package com.example.demo.ch12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.ch12.base.DaoTestBase;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ProductDaoのテスト
 */
class ProductDaoTest extends DaoTestBase {

    private final ProductDao sut = new ProductDao();

    @Override
    protected String[] getInitSqls() {
        return new String[]{
                "DROP TABLE IF EXISTS products",
                "CREATE TABLE products(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))",
                "INSERT INTO products(name) VALUES('Pen')",
                "INSERT INTO products(name) VALUES('Notebook')"
        };
    }

    @Test
    @DisplayName("getList: 2件取得できる")
    void testGetList() throws Exception {
        try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
            List<String> products = sut.getList(con);
            assertThat(products).containsExactly("Pen", "Notebook");
        }
    }

    @Test
    @DisplayName("insert: 1件追加できる")
    void testInsert() throws Exception {
        try (Connection con = DBConnector.getConnect(URL, USERNAME, PASS)) {
            sut.insert(con, "Eraser");

            List<String> products = sut.getList(con);
            assertThat(products).containsExactly("Pen", "Notebook", "Eraser");
        }
    }
}