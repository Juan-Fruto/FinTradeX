package com.trading.project.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/portfolio")
public class PortfolioController {

    @GetMapping("/{userId}")
    public String getPortfolioByUser(@PathVariable String userId) {
        return "hello";
    }

    @PatchMapping("/wallet/{userId}")
    public String updateWalletAmount(@PathVariable String userId, @RequestBody RequestBody requestBody) {
        return "hello";
    }

}
