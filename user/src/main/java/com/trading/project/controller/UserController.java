package com.trading.project.controller;

import com.trading.project.entity.User;
import com.trading.project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RequestBody requestBody) {
        User user = (User) requestBody;
        return ResponseEntity
                .ok()
                .body(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        return ResponseEntity
                .ok()
                .body(userService.getUserById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.
                ok()
                .body(userService.getAllUsers());
    }

}
