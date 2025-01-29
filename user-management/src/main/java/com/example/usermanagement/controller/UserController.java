package com.example.usermanagement.controller;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    //register metoda
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @GetMapping("/users/{id}")
    public User getUser(@PathVariable @Min(1) Integer id) {
        return userService.getUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable @Min(1) Integer id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Min(1) Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
