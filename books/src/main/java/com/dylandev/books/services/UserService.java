package com.dylandev.books.services;

import com.dylandev.books.entities.Users;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Users saveUser(Users user);

    Users updateUser(Users user);

    List<Users> getUsers();

    Optional<Users> getUserById(Long id);

    void deleteUser(Users user) throws IOException;
}
