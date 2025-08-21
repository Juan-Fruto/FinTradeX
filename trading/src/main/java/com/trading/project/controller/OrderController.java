package com.trading.project.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @PostMapping()
    public String order(@RequestBody RequestBody requestBody){
        return "hello";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable String id) {
        return "hello";
    }

    @GetMapping("/user/{userId}")
    public String getOrdersByUser(@PathVariable String userId) {
        return "hello";
    }

}
