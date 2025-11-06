package com.trading.project.controller;

import com.trading.project.entity.User;
import com.trading.project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RequestBody requestBody) {
        User user = (User) requestBody;
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
