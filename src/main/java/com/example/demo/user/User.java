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

}





