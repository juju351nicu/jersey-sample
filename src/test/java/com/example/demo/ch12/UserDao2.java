package com.example.demo.ch12;

import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import static org.junit.jupiter.api.Assertions.*;
class UserDao2 {

    @RegisterExtension
    static H2DatabaseServerExtension server =
            new H2DatabaseServerExtension("h2", "db", "ut", 9092);

    @BeforeEach
    void setUp() throws Exception {
        try (var conn = java.sql.DriverManager.getConnection(
                server.getJdbcUrl(), "sa", "")) {
            var stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS users");
            stmt.execute("CREATE TABLE users(id INT AUTO_INCREMENT, name VARCHAR(255))");
            stmt.execute("INSERT INTO users(name) VALUES ('Ichiro'), ('Jiro')");
            conn.commit();
        }
    }

    @Test
    void testGetList() throws Exception {
        UserDao sut = new UserDao();
        List<String> list = sut.getList();
        assertEquals(2, list.size());
    }
}
