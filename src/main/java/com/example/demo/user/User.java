package com.example.demo.user;


import javax.persistence.*;

@Entity(name = "users")
class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String username;
  String password;

  User() {
  }

  User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  void checkPassword(String password) {
    if (password == null || !password.equals(this.password)) {
      throw new IllegalStateException("Incorrect password");
    }
  }

  LoggedUser dto() {
    return new LoggedUser(id, username);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}


