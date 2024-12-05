package com.dylandev.books.services;

import com.dylandev.books.entities.Users;
import com.dylandev.books.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Users user) throws IOException {
        userRepository.deleteById(user.getId());
    }
}
