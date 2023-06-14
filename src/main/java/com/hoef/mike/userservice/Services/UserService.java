package com.hoef.mike.userservice.Services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoef.mike.userservice.Entities.User;
import com.hoef.mike.userservice.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getId()).orElse(null);
        if (user == null) {
            return null;
        }
        BeanUtils.copyProperties(updatedUser, user); // update all properties
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}