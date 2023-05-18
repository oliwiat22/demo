package com.example.demo.user;

public class NewUserRequest {
  private String username;
  private String password;

  public NewUserRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String setUsername() { return username; }

  public String setPassword() {return password;}
}
