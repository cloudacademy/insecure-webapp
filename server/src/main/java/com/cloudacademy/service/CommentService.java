package com.cloudacademy.service;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import com.cloudacademy.BadRequest;
import com.cloudacademy.ServerError;
import com.cloudacademy.database.Postgres;
import com.cloudacademy.pojo.Comment;

public class CommentService {
  public static Comment createComment(String username, String body){
    var SQL = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";

    long time = new Date().getTime();
    Timestamp timestamp = new Timestamp(time);
    Comment comment = new Comment(UUID.randomUUID().toString(), username, body, timestamp);

    var insertOk = false;

    try (Connection conn = Postgres.connection(); PreparedStatement pStatement = conn.prepareStatement(SQL)) {
      pStatement.setString(1, UUID.randomUUID().toString());
      pStatement.setString(2, username);
      pStatement.setString(3, body);
      pStatement.setTimestamp(4, timestamp);
      
      insertOk = 1 == pStatement.executeUpdate();

      if(insertOk) {
        return comment;
      } else {
        throw new BadRequest("Unable to save comment");
      }
    } catch (SQLException e) {
      throw new ServerError(e.getMessage());
    }
  }

  public static List<Comment> getAllComments() {
    var SQL = "SELECT * FROM comments;";

    List<Comment> comments = new ArrayList<Comment>();

    try (Connection conn = Postgres.connection(); Statement stmt = conn.createStatement()) {
      try (ResultSet rs = stmt.executeQuery(SQL)) {
        while (rs.next()) {
          String id = rs.getString("id");
          String username = rs.getString("username");
          String body = rs.getString("body");
          Timestamp created_on = rs.getTimestamp("created_on");
          Comment c = new Comment(id, username, body, created_on);
          comments.add(c);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return comments;
  }

  public static Boolean deleteComment(String id) {
    var SQL = "DELETE FROM comments WHERE id = ?";

    var deleteOk = false;

    try (Connection conn = Postgres.connection(); PreparedStatement pStatement = conn.prepareStatement(SQL)) {
      pStatement.setString(1, id);
      deleteOk = 1 == pStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }

    return deleteOk;
  }
}
