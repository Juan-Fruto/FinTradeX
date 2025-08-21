package com.trading.project.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @PostMapping("/register")
    public String registerUser(@RequestBody RequestBody requestBody) {
        return "hello";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable String id){
        return "hello";
    }

}
