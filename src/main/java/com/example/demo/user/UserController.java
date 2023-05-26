package com.example.demo.user;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.Optional;

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
    @GetMapping("/get/{userId}")
    ResponseEntity<User> getUser(@PathVariable Long userId) {
      Optional<User> byId = this.respository.findById(userId);
      if (byId.isPresent()) {
        User user = byId.get();
        return ResponseEntity.ok(user);
      }
      return ResponseEntity.notFound().build();
    }
    @PutMapping("/update/{userId}")
    ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updatedUser) {
      Optional<User> optionalUser = respository.findById(userId);
      if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        user.setUsernameAtrybut(updatedUser.getUsername());
        user.setPasswordAtrybut(updatedUser.getPassword());
        respository.save(user);
        return ResponseEntity.ok().build();
      }
      return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{userId}")
    ResponseEntity<?> deleteUser(@PathVariable Long userId) {
    Optional<User> optionalUser = respository.findById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      respository.delete(user);
      return ResponseEntity.ok().build();
    }
    return  ResponseEntity.notFound().build();
    }
}
