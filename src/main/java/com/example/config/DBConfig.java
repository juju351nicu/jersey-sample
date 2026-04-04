package com.example.config;

public class DBConfig {

    public static String getUrl() {
        String env = System.getProperty("env");

        if ("test".equals(env)) {
            return "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        }
        return "jdbc:mysql://localhost:3306/mydata";
    }
}
