package com.cloudacademy.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Postgres {

    public static Connection connection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            
            var url = System.getenv("POSTGRES_CONNSTR");
            var userName = System.getenv("POSTGRES_USER");
            var password = System.getenv("POSTGRES_PASSWORD");

            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName());
            System.exit(1);
        }
        
        return connection;
    }
}
