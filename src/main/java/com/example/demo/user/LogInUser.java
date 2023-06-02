package com.example.demo.user;

public class LogInUser {

  private final String username;
  private final String password;

  public LogInUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}