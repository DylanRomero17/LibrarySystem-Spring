package com.dylandev.books.controllers;

import com.dylandev.books.entities.Users;
import com.dylandev.books.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200/")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        try{
            Users savedUser = userServiceImpl.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        try{
            Users savedUser = userServiceImpl.updateUser(user);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = userServiceImpl.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws IOException {
        Optional<Users> user = userServiceImpl.getUserById(id);
        if(user.isPresent()) {
            userServiceImpl.deleteUser(user.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
