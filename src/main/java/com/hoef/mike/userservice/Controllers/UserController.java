package com.hoef.mike.userservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.hoef.mike.userservice.Entities.User;
import com.hoef.mike.userservice.Services.UserService;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable String id) throws Exception {
        User user = userService.getUserById(id);
        if (user == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found");

        return user;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}