package com.example.demo.user;

public class LoggedUser {

  private Long id;
  private String username;

  public LoggedUser(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

}