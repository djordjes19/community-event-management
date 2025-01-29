package com.example.usermanagement.service;

import com.example.usermanagement.exceptions.EntityDoesNotExistException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityDoesNotExistException("Entity not found."));


        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("User with id: " + id + " not found."));
    }

    public User updateUser(Integer id, User user) {
        // Fetch the existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("User with id: " + id + " not found."));

        if (user.getName() != null && !user.getName().isEmpty()) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            existingUser.setPhone(user.getPhone());
        }
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer id) {
        // Check if the user exists before attempting to delete
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("User with id: " + id + " not found."));

        // Delete the user
        userRepository.delete(existingUser);
    }
}
