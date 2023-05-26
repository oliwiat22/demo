package com.example.demo.user;


import jakarta.persistence.*;


@Entity(name = "users")

class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String usernameAtrybut;
  String passwordAtrybut;

  User() {
  }

  User(String username, String password) {
    usernameAtrybut = username;
    passwordAtrybut = password;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsernameAtrybut() {
    return usernameAtrybut;
  }

  public void setUsernameAtrybut(String usernameAtrybut) {
    this.usernameAtrybut = usernameAtrybut;
  }

  public String getPasswordAtrybut() {
    return passwordAtrybut;
  }

  public void setPasswordAtrybut(String passwordAtrybut) {
    this.passwordAtrybut = passwordAtrybut;
  }
}







