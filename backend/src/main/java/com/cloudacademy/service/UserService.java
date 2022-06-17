package com.cloudacademy.service;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cloudacademy.database.Postgres;
import com.cloudacademy.pojo.User;

public class UserService {
  public static User getUser(String username) {
    var SQL = "SELECT * FROM users WHERE username = '" + username + "' LIMIT 1";

    User user = null;
    try (Connection conn = Postgres.connection(); Statement stmt = conn.createStatement()) {
      try (ResultSet rs = stmt.executeQuery(SQL)) {
        if (rs.next()) {
          String user_id = rs.getString("user_id");
          String uname = rs.getString("username");
          String password = rs.getString("password");
          user = new User(user_id, uname, password);
        }
      }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

    return user;
  }
}
