package com.cloudacademy.pojo;

import java.sql.Timestamp;

public class Comment {
  public String id, username, body;
  public Timestamp created_on;

  public Comment(String id, String username, String body, Timestamp created_on) {
    this.id = id;
    this.username = username;
    this.body = body;
    this.created_on = created_on;
  }
}
