package com.example.demo.user;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
  UserRepository respository;

@Autowired
  public UserController(UserRepository respository ) {
    this.respository = respository;
  }

  @PostMapping("/create")
  ResponseEntity<?> createUser(@RequestBody NewUserRequest newUser) {
    User user = new User(newUser.getUsername(), newUser.getPassword());
    respository.save(user);
    return ResponseEntity.ok().build();

  }


}
