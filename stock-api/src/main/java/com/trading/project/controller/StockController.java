package com.trading.project.controller;

import com.trading.project.entity.Stock;
import com.trading.project.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{id}")
    public Stock getStockById(@PathVariable String stockId) {
        return stockService.getStockById(stockId);
    }

    @GetMapping("/")
    public List<Stock> getAllStocks() {
        return stockService.getAll();
    }

}
